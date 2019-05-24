/**
 * 
 */
package com.excel.base.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excel.base.beans.BeanCellExcel;
import com.excel.base.services.UploadExcelService;
import com.excel.base.utils.UploadExcelUtil;

/**
 * @author vin00
 *
 */
@Service
public class UploadExcelServiceImpl implements UploadExcelService {

	private static final String CADENA_VACIA = "";

	private static final boolean PARALLEL_OPTION = false;

	@Autowired
	private UploadExcelUtil uploadExcelUtil;

	@Override
	public List<List<Map<String, BeanCellExcel>>> uploadExcelFile(MultipartFile excelFile) throws IOException {
		Path temporaryDirectory = Files.createTempDirectory(CADENA_VACIA);
		File excelTemporalFile = temporaryDirectory.resolve(excelFile.getOriginalFilename()).toFile();
		excelFile.transferTo(excelTemporalFile);
		Workbook workBook = WorkbookFactory.create(excelTemporalFile);
		Stream<Sheet> sheetStream = StreamSupport.stream(workBook.spliterator(), PARALLEL_OPTION);

		return sheetStream.map(sheet -> {
			Supplier<Stream<Row>> rowStreamSupplier = uploadExcelUtil.getRowStreamSupplier(sheet, PARALLEL_OPTION);

			List<String> headerList = titlesValidation(rowStreamSupplier.get().findFirst());

			return rowStreamSupplier.get().skip(1).map(row -> {
				List<BeanCellExcel> cellList = StreamSupport.stream(row.spliterator(), PARALLEL_OPTION).map(cell -> {
					return getBeanCellExcel(cell);
				}).collect(Collectors.toList());

				return uploadExcelUtil.cellIteratorSupplier(headerList.size()).get()
						.collect(Collectors.toMap(headerList::get, cellList::get));
			}).collect(Collectors.toList());

		}).collect(Collectors.toList());
	}

	@Override
	public ByteArrayInputStream generateExcelReport(String identification, int employNumber) throws IOException {
		String[] COLUMNs = { "Id", "Name", "Address", "Age" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper createHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("Customers");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			// CellStyle for Age
			CellStyle ageCellStyle = workbook.createCellStyle();
			ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

			int rowIdx = 1;
			for (int i = 0; i < 10; i++) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(i);
				row.createCell(1).setCellValue("Name " + i);
				row.createCell(2).setCellValue("Address " + i);

				Cell ageCell = row.createCell(3);
				ageCell.setCellValue(i);
				ageCell.setCellStyle(ageCellStyle);
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	/**
	 * @param cell
	 * @return
	 */
	private BeanCellExcel getBeanCellExcel(Cell cell) {
		CellType cellType = cell.getCellType();
		BeanCellExcel beanCellExcel = new BeanCellExcel();
		String cellValue = null;
		String cellNameType = cellType.name();
		switch (cellType) {
		case NUMERIC: {
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		}
		case STRING: {
			cellValue = cell.getStringCellValue();
			break;
		}
		case FORMULA: {
			cellValue = cell.getCellFormula();
			break;
		}
		case BLANK: {
			break;
		}
		case _NONE: {
			cellValue = cell.getStringCellValue();
			break;
		}
		case BOOLEAN: {
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		}
		case ERROR: {
			break;
		}
		default:
			break;
		}
		beanCellExcel.setCellType(cellNameType);
		beanCellExcel.setCellValue(cellValue);
		return beanCellExcel;
	}

	/**
	 * @param optional
	 * @return
	 */
	private List<String> titlesValidation(Optional<Row> optional) {
		return uploadExcelUtil.getStream(optional.get(), PARALLEL_OPTION).map(Cell::getStringCellValue)
				.map(String::valueOf).collect(Collectors.toList());
	}

}

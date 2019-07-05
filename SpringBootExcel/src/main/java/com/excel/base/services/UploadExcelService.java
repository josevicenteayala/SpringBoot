package com.excel.base.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.excel.base.beans.BeanCellExcel;

public interface UploadExcelService {

	/**
	 * @param excelFile
	 * @return
	 * @throws IOException
	 */
	public List<List<Map<String, BeanCellExcel>>> uploadExcelFile(MultipartFile excelFile) throws IOException;

	/**
	 * @param identification
	 * @param employNumber
	 * @return
	 * @throws IOException 
	 */
	public ByteArrayInputStream generateExcelReport(String identification, int employNumber) throws IOException;
}

package com.excel.base.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.excel.base.beans.BeanCellExcel;

public interface UploadExcelService {

	public List<List<Map<String, BeanCellExcel>>> uploadExcelFile(MultipartFile excelFile) throws IOException;
}

/**
 * 
 */
package com.excel.base;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.excel.base.beans.BeanCellExcel;
import com.excel.base.services.impl.UploadExcelServiceImpl;
import com.excel.base.utils.UploadExcelUtil;

/**
 * @author vin00
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UploadExcelServiceImplTest {

	@InjectMocks
	private UploadExcelServiceImpl uploadExcelServiceImpl = new UploadExcelServiceImpl();
	
	@Mock
	private UploadExcelUtil uploadExcelUtil;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(uploadExcelServiceImpl, "uploadExcelUtil", uploadExcelUtil);
	}
	
	/**
	 * Test method for {@link com.excel.base.services.impl.UploadExcelServiceImpl#uploadExcelFile(org.springframework.web.multipart.MultipartFile)}.
	 */
	@Test
	public void testUploadExcelFile() {
		MultipartFile excelFile = Mockito.mock(MultipartFile.class);
		Mockito.when(excelFile.getOriginalFilename()).thenReturn("ArchivoExcel.xlst");
		try {
			List<List<Map<String, BeanCellExcel>>> uploadExcelFile = uploadExcelServiceImpl.uploadExcelFile(excelFile);
			assertNotNull(uploadExcelFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

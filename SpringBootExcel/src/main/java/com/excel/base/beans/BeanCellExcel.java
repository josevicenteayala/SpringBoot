/**
 * 
 */
package com.excel.base.beans;

/**
 * @author vin00
 *
 */
public class BeanCellExcel {

	private String cellValue;
	private String cellType;
	
	public String getCellValue() {
		return cellValue;
	}
	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}
	public String getCellType() {
		return cellType;
	}
	public void setCellType(String cellType) {
		this.cellType = cellType;
	}
	
	@Override
	public String toString() {
		return "BeanCellExcel [cellValue=" + cellValue + ", cellType=" + cellType + "]";
	}
}

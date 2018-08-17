package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelReader {
	static HashMap<String, Integer> string2indexmap = new HashMap<String, Integer>();
	
	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	String xlFilePath;
	
	
	public void setExcelFile(String xlFilePath){
		this.xlFilePath = xlFilePath;
		System.out.println(xlFilePath);
		try {
			fis = new FileInputStream(xlFilePath);
			workbook = new XSSFWorkbook(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}
	
	public void filstring2indexmap(String sheetName) {
		
		try {
			sheet = workbook.getSheet(sheetName);
			for(int i = 1; i<=sheet.getLastRowNum();i++) {
				row = sheet.getRow(i);
				string2indexmap.put(row.getCell(0).getStringCellValue().trim(), i);
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	public String getCellData(String sheetName, String colName, String rowName){

		try {
			int colNum = -1;
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			
			for (int i=0; i<row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
					break;
			}
			row = sheet.getRow(string2indexmap.get(rowName.trim()));
			cell = row.getCell(colNum);
			
			return cell.getStringCellValue();

		} catch (Exception e) {

			e.printStackTrace();
			return "Data Not Found";

		}

	}
	// This method is to write in the Excel cell, Row num and Col num are the
	// parameters

	public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {

		try {

			Row = ExcelWSheet.getRow(RowNum);

			Cell = Row.getCell(ColNum);

			if (Cell == null) {

				Cell = Row.createCell(ColNum);

				Cell.setCellValue(Result);

			} else {

				Cell.setCellValue(Result);

			}

			// Constant variables Test Data path and Test Data file name

			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);

			ExcelWBook.write(fileOut);

			fileOut.flush();

			fileOut.close();

		} catch (Exception e) {

			throw (e);

		}

	}

}

package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	
	Workbook wb;
	
	//Constructor for excel path
	public ExcelFileUtil(String excelpath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelpath);
		wb= WorkbookFactory.create(fi);
	}
	
	//For counting number of rows
	public int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
	}
	
	//for counting no opf coulmns
	public int cellCount(String SheetName)
	{
		return wb.getSheet(SheetName).getRow(0).getLastCellNum();
	}
	
	public String getCellData(String sheetName,int row, int column)
	{
		String data="";
		if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int)wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(celldata);
		}
		else
		{
			data= wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		}
		
		return data;
			
	}
	
	public void setCelldata(String SheetName, int row, int column,String status, String writeExcel) throws Throwable
	{
		//get Sheet from wb
		Sheet ws= wb.getSheet(SheetName);
		
		//get Row from Sheet
		Row rowNum=ws.getRow(row);
		
		//Create column
		Cell cell=rowNum.createCell(column);
		
		//writing status
		cell.setCellValue(status);
		
		if(status.equalsIgnoreCase("pass"))
		{
			CellStyle style =wb.createCellStyle();
			Font font =wb.createFont();
			//colouring text into green
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("Fail"))
		{
			CellStyle style =wb.createCellStyle();
			Font font = wb.createFont();
			//color to font
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if(status.equalsIgnoreCase("blocked"))
		{
			CellStyle style = wb.createCellStyle();
			Font font =wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		
		FileOutputStream fo = new FileOutputStream(writeExcel);
		wb.write(fo);
		
		
	}
	

}

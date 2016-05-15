package com.yabushan.test.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {
	// 设置cell编码解决中文高位字节截断
	// private static short XLS_ENCODING = HSSFWorkbook.ENCODING_UTF_16;
	// 定制日期格式
	private static String DATE_FORMAT = " m/d/yy "; // "m/d/yy h:mm"
	// 定制浮点数格式
	private static String NUMBER_FORMAT = " #,##0.00 ";

	private String xlsFileName;

	private HSSFWorkbook workbook;

	private HSSFSheet sheet;

	private HSSFRow row;

	/**
	* 初始化Excel
	* 
	* @param fileName
	* 导出文件名
	* @return
	*/
	public void XLSExport(String fileName) {
	this.xlsFileName = fileName;
	this.workbook = new HSSFWorkbook();
	this.sheet = workbook.createSheet();
	}

	/**
	* 导出Excel文件
	* 
	* @throws IOException
	* @throws XLSException
	*/
	public void exportXLS() throws IOException {
	FileOutputStream fOut = new FileOutputStream(xlsFileName);
	workbook.write(fOut);
	fOut.flush();
	fOut.close();
	}

	/**
	* 增加一行
	* 
	* @param index
	* 行号
	*/
	public void createRow(int index) {
	this.row = this.sheet.createRow(index);
	}

	/**
	* 设置单元格
	* 
	* @param index
	* 列号
	* @param value
	* 单元格填充值
	*/
	public void setCell(int index, String value) {
	HSSFCell cell = this.row.createCell((short) index);
	cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	// cell.setEncoding(XLS_ENCODING);
	cell.setCellValue(value);
	}

	/**
	* 设置单元格
	* 
	* @param index
	* 列号
	* @param value
	* 单元格填充值
	*/
	public void setCell(int index, Calendar value) {
	HSSFCell cell = this.row.createCell((short) index);
	// cell.setEncoding(XLS_ENCODING);
	cell.setCellValue(value.getTime());
	HSSFCellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式
	cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DATE_FORMAT)); // 设置cell样式为定制的日期格式
	cell.setCellStyle(cellStyle); // 设置该cell日期的显示格式
	}

	/**
	* 设置单元格
	* 
	* @param index
	* 列号
	* @param value
	* 单元格填充值
	*/
	public void setCell(int index, int value) {
	HSSFCell cell = this.row.createCell((short) index);
	cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	cell.setCellValue(value);
	}

	/**
	* 设置单元格
	* 
	* @param index
	* 列号
	* @param value
	* 单元格填充值
	*/
	public void setCell(int index, double value) {
	HSSFCell cell = this.row.createCell((short) index);
	cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	cell.setCellValue(value);
	HSSFCellStyle cellStyle = workbook.createCellStyle(); // 建立新的cell样式
	HSSFDataFormat format = workbook.createDataFormat();
	cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT)); // 设置cell样式为定制的浮点数格式
	cell.setCellStyle(cellStyle); // 设置该cell浮点数的显示格式
	}


	public static void main(String[] args) {
	ExportExcel excel = new ExportExcel();
	excel.XLSExport("E:\\测试.xls");
	excel.createRow(0);
	excel.setCell(0, "序号");
	excel.setCell(1, "公司");
	excel.setCell(2, "网址");
	excel.setCell(3, "姓名");
	excel.createRow(1);// excel正文
	excel.setCell(0, "1");
	excel.setCell(1, "程序员之家");
	excel.setCell(2, "http://bbs.it-home.org");
	excel.setCell(3, "小贝");
	try {
	excel.exportXLS();
	System.out.println("导出excel成功");
	} catch (IOException e) {
	System.out.println("导出excel失败");
	e.printStackTrace();
	}
	}
}

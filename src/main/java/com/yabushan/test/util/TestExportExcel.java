package com.yabushan.test.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import jxl.CellView;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



/**
 * 测试导出excel报表
 * @author yabushan
 * 需要的jar包：(两种方式)
 * jxl.jar
 * poi-3.2-FINAL.jar
 *
 */
@SuppressWarnings("deprecation")
public class TestExportExcel {
	public static void main(String[] args) {
		System.out.println("======创建报表");
	//	createExcel();
		//displayExcel();
		exportPoiExcel();
	}
	/**
	 * jax.jar包方式
	 */
	public static  void createExcel(){
		try {
			//打开文件
			WritableWorkbook workbook = Workbook.createWorkbook(new File("E:\\test.xls"));
			//生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet=workbook.createSheet("第一页", 0);
			//在Label对象的构造子中指明单元格位置是第一列第一行(0，0)
			//以及单元格内容为test
			Label label=new Label(0, 0, "test");
			//将定义好的单元格添加到工作表中
			sheet.addCell(label);
			/**
			 * 生成一个保存数字的单元格
			 * 必须使用Number的完整包路径，否则又语法歧义
			 * 单元格位置是第二列，第一行，值为789.123
			 */
			jxl.write.Number number=new jxl.write.Number(1, 0, 757);
			sheet.addCell(number);
			sheet.insertColumn(1);
			workbook.copySheet(0, "第二页", 1);
			WritableSheet sheet2=workbook.getSheet(1);
			Range range=sheet2.mergeCells(0, 0, 0, 8);
			sheet2.unmergeCells(range);
			sheet2.addImage(new WritableImage(5, 5,10,20,new File("E:\\11.jpg")));
			CellView cView=new CellView();
			WritableCellFormat cFormat=new WritableCellFormat();
			cFormat.setBackground(Colour.BLUE);
			cView.setFormat(cFormat);
			cView.setSize(6000);
			cView.setSize(10);
			sheet2.setColumnView(2, cView);
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 读取excel中的内容
	 */
	public static  void displayExcel(){
		try {
			Workbook wb=Workbook.getWorkbook(new File("E:\\test.xls"));
			Sheet s=wb.getSheet(0);
				System.out.println(s.getCell(0,0).getContents());
				System.out.println(s.getCell(2,0).getContents());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * poi方式创建excel
	 */
	
	@SuppressWarnings("deprecation")
	public static void exportPoiExcel(){
		HSSFWorkbook wb=new HSSFWorkbook();//创建工作簿
		
		HSSFFont font=wb.createFont();
		font.setFontHeightInPoints((short) 24);
		font.setFontName("宋体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		style.setFont(font);
		
		
		
		 //内容的样式
        HSSFCellStyle content_style = wb.createCellStyle();
        content_style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        content_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        content_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        content_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        content_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        content_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        content_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        content_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont content_font = wb.createFont();
        content_font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        content_style.setFont(content_font);
        
		
		HSSFSheet sheet=wb.createSheet("test");//创建工作表，名称为test
		
		int iRow=0;//行号
		int iMaxCol=17;//最大列数
		HSSFRow row=sheet.createRow(iRow);
		HSSFCell cell=row.createCell(0);
		cell.setCellValue(new HSSFRichTextString("测试excel"));
		cell.setCellStyle(style);
		sheet.addMergedRegion(new Region(iRow,(short)0,iRow,(short)(iMaxCol-1))); 
		
		 for (int i = 1; i < 10; i++) {
	            row = sheet.createRow(i);
	            for (int j = 0; j <10; j++) {
	                HSSFCell cell1 = row.createCell(j);
	                cell1.setCellStyle(content_style);
	                cell.setCellValue(new HSSFRichTextString(i+j+""));
	                sheet.addMergedRegion(new Region((short)i, (short)i, (short)j, (short)j));
	            }
	        }
		
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		
		try {
			wb.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		byte[] xls=os.toByteArray();
		File file=new File("E:\\test11.xls");
		OutputStream outputStream=null;
		try {
			outputStream=new FileOutputStream(file);
			try {
				outputStream.write(xls);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private void outputExcel(String queryDate, String[] headers,
            List<List<String>> diaochas, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //createSheet(excel工作表名)
        HSSFSheet sheet = workbook.createSheet(queryDate);
        //下面是设置excel表中标题的样式
        HSSFCellStyle title_style = workbook.createCellStyle();
        title_style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        title_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        title_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        title_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        title_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        title_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        title_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont title_font = workbook.createFont();
        title_font.setColor(HSSFColor.VIOLET.index);
        title_font.setFontHeightInPoints((short) 12);
        title_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        title_style.setFont(title_font);
        //内容的样式
        HSSFCellStyle content_style = workbook.createCellStyle();
        content_style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        content_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        content_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        content_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        content_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        content_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        content_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        content_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont content_font = workbook.createFont();
        content_font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        content_style.setFont(content_font);
        //填充标题内容
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            //设置标题的宽度自适应
            sheet.setColumnWidth(i, headers[i].getBytes().length * 2 * 256);
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(title_style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //填充内容 囧。。。偷懒没有建立对象，直接用List存放的数据。
        for (int i = 0; i < diaochas.size(); i++) {
            row = sheet.createRow(i + 1);
            List<String> diaocha = diaochas.get(i);
            for (int j = 0; j < diaocha.size(); j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(content_style);
                HSSFRichTextString richString = new HSSFRichTextString(
                        diaocha.get(j));
                cell.setCellValue(richString);
            }
        }
        //这里调用reset()因为我在别的代码中调用了response.getWriter();
        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="
                + queryDate + ".xls");
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }
}

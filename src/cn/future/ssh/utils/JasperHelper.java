package cn.future.ssh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
/**
 * 报表的相关工具类
 * @author hzc
 *
 */
public class JasperHelper {
	private static Logger logger = Logger.getLogger(JasperHelper.class);
	public static final String PRINT_TYPE = "print";
	public static final String PDF_TYPE = "pdf";
	public static final String EXCEL_TYPE = "excel";
	public static final String HTML_TYPE = "html";
	public static final String WORD_TYPE = "word";

	public static void prepareReport(JasperReport jasperReport, String type) {
		logger.debug("The method======= prepareReport() start.......................");
		/*
		 * 如果导出的是excel，则需要去掉周围的margin
		 */
		if ("excel".equals(type)) {
			try {
				Field margin = JRBaseReport.class.getDeclaredField("leftMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				margin = JRBaseReport.class.getDeclaredField("topMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				margin = JRBaseReport.class.getDeclaredField("bottomMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				Field pageHeight = JRBaseReport.class.getDeclaredField("pageHeight");
				pageHeight.setAccessible(true);
				pageHeight.setInt(jasperReport, 2147483647);
			} catch (Exception exception) {
				System.out.println("导出是Excel，去除margin时出错。");
			}
		}
	}

	/**
	 * 导出excel
	 */
	public static void exportExcel(JasperPrint jasperPrint,
			String defaultFilename, HttpServletRequest request,
			HttpServletResponse response) throws IOException, JRException {
		logger.debug("执行导出excel   The method======= exportExcel() start.......................");
		/*
		 * 设置头信息
		 */
		response.setContentType("application/vnd.ms-excel");
		String defaultname = null;
		if (defaultFilename.trim() != null || defaultFilename != null) {
			defaultname = defaultFilename + ".xls";
		} else {
			defaultname = "export.xls";
		}
		String fileName = defaultname;
		//String fileName = new String(defaultname.getBytes("gbk"), "utf-8");
		response.setHeader("Content-disposition", "attachment; filename="
				+ fileName);
		ServletOutputStream ouputStream = response.getOutputStream();
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
		exporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);
		exporter.exportReport();
		ouputStream.flush();
		ouputStream.close();
	}

	/**
	 * 导出pdf，注意此处中文问题，
	 * 这里应该详细说：主要在ireport直接改变即可。
	 * 1）在ireport的classpath中加入iTextAsian.jar 
	 * 2）在ireport画jrxml时，看ireport最左边有个属性栏。
	 * 下边的设置就在点字段的属性后出现。 pdf font name ：STSong-Light ，pdf encoding ：UniGB-UCS2-H
	 */
	private static void exportPdf(JasperPrint jasperPrint,
			String defaultFilename, HttpServletRequest request,
			HttpServletResponse response) throws IOException, JRException {
		response.setContentType("application/pdf");
		String defaultname = null;
		if (defaultFilename.trim() != null || defaultFilename != null) {
			defaultname = defaultFilename;
		} else {
			defaultname = "export.pdf";
		}
		String fileName = new String(defaultname.getBytes("GBK"), "ISO8859_1");
		response.setHeader("Content-disposition", "attachment; filename="
				+ fileName);
		ServletOutputStream ouputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
	
	// 打印pdf报表
	/**
	 * @param jaspername jasper名字
	 * @param lists 数据源（放到集合里边）
	 */
	public static Boolean printPdf(String jaspername, List lists) {
		Boolean printFlag = false;
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		String sourceFileName = request.getRealPath("/jasper/" + jaspername);// jasper文件放在WebRoot/jasper/xx.jasper
		String printFileName = null;
		ArrayList dataList = (ArrayList) lists;
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		Map parameters = new HashMap();
		try{
			printFileName = JasperFillManager.fillReportToFile(sourceFileName, parameters, beanCollectionDataSource);
			if(printFileName != null) {
				printFlag = JasperPrintManager.printReport(printFileName, true); // 打印报表的一种方法
			}
			
		} catch(JRException e) {
			e.printStackTrace();
		}
		return printFlag;
		// 打印报表的两个方法
		
		//JasperPrintManager.printReport(printFileName, true);
		//JasperPrintManager.printReport(sourceFileName, true);
	}
	/**
	 * 批量打印
	 */
	public static void printPdfs() {
		JRPdfExporter exporter = new JRPdfExporter();
		
		// 打印单张报表
		//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		// 打印多张报表（不同模板的报表）
		//exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperprint);
	}

	/**
	 * 导出html
	 */
	private static void exportHtml(JasperPrint jasperPrint,
			String defaultFilename, HttpServletRequest request,
			HttpServletResponse response) throws IOException, JRException {
		response.setContentType("text/html");
		ServletOutputStream ouputStream = response.getOutputStream();
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
				Boolean.FALSE);
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

		exporter.exportReport();
		ouputStream.flush();
		ouputStream.close();
	}

	/**
	 * 导出word
	 */
	private static void exportWord(JasperPrint jasperPrint,
			String defaultFilename, HttpServletRequest request,
			HttpServletResponse response) throws JRException, IOException {
		response.setContentType("application/msword;charset=utf-8");
		String defaultname = null;
		if (defaultFilename.trim() != null || defaultFilename != null) {
			defaultname = defaultFilename + ".doc";
		} else {
			defaultname = "export.doc";
		}
		String fileName = new String(defaultname.getBytes("GBK"), "utf-8");
		response.setHeader("Content-disposition", "attachment; filename="
				+ fileName);
		JRExporter exporter = new JRRtfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				response.getOutputStream());
		exporter.exportReport();
	}

	/**
	 * 按照类型导出不同格式文件
	 * 
	 * @param datas   数据
	 * @param type    文件类型
	 * @param is      jasper文件的来源
	 * @param request
	 * @param response
	 * @param defaultFilename默认的导出文件的名称
	 */
	private static void export(Collection datas, String type,
			String defaultFilename, InputStream is, HttpServletRequest request, HttpServletResponse response) {
		logger.debug("导出判断     The method======= export() start.......................");
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
			prepareReport(jasperReport, type);
			JRDataSource ds = new JRBeanCollectionDataSource(datas, false);
			Map parameters = new HashMap();
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, ds);

			if (EXCEL_TYPE.equals(type)) {
				exportExcel(jasperPrint, defaultFilename, request, response);
			} else if (PDF_TYPE.equals(type)) {
				exportPdf(jasperPrint, defaultFilename, request, response);
			} else if (HTML_TYPE.equals(type)) {
				exportHtml(jasperPrint, defaultFilename, request, response);
			} else if (WORD_TYPE.equals(type)) {
				exportWord(jasperPrint, defaultFilename, request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导出入口
	 * 
	 * @param exportType  导出文件的类型
	 * @param jaspername jasper文件的名字 如： xx.jasper
	 * @param lists 导出的数据
	 * @param request
	 * @param response
	 * @param defaultFilename 默认的导出文件的名称
	 */
	public static void exportmain(String exportType, String jaspername, List lists, String defaultFilename) {
		logger.debug("进入导出    The method======= exportmain() start.......................");
		ActionContext ct = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ct.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		String filenurl = request.getRealPath("/jasper/" + jaspername);// jasper文件放在WebRoot/jasper/xx.jasper
		File file = new File(filenurl);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		export(lists, exportType, defaultFilename, is, request, response);
	}
}
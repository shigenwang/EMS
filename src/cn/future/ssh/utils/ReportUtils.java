package cn.future.ssh.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

/** 
 * 报表通用类（Demo，并没在项目中使用）
 * 使用jasperReport做报表时的工具支持类.有两个用途,生成jasperPrint对象,和设置导出时的session 
 */  
public class ReportUtils {  
    private HttpServletRequest request;  
    private HttpServletResponse response;  
    private HttpSession session;  
  
    public ReportUtils(HttpServletRequest request, HttpServletResponse response) {  
        this.request = request;  
        this.session = request.getSession();  
        this.response = response;  
    }  
  
    /** 
     * 获得JasperPrint对象;自定义填充报表时的parameter和dataSource. 参数说明和动态表头的用法参考上一方法 
     */  
    public JasperPrint getJasperPrint(String filePath, Map parameter,  
            JRDataSource dataSource) throws JRException {  
        JasperReport jasperReport = null;  
        try {  
            jasperReport = (JasperReport) JRLoader.loadObject(filePath);  
            return JasperFillManager.fillReport(jasperReport, parameter,  
                    dataSource);  
        } catch (JRException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    /** 
     * 获得JasperPrint对象;自定义填充报表时的parameter和connection 
     */  
    public JasperPrint getJasperPrint(String filePath, Map parameter,  
            Connection  conn) throws JRException {  
        JasperReport jasperReport = null;  
        try {  
            jasperReport = (JasperReport) JRLoader.loadObject(filePath);  
            return JasperFillManager.fillReport(jasperReport, parameter,  
                    conn);  
        } catch (JRException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
      
    /** 
     * 通过传入List类型数据源获取JasperPrint实例 
     */  
    public JasperPrint getPrintWithBeanList(String filePath, Map parameter,  
            List list) throws JRException {  
        JRDataSource dataSource = new JRBeanCollectionDataSource(list);  
        return getJasperPrint(filePath, parameter, dataSource);  
    }  
  
    /** 
     * 传入类型，获取输出器 
     *  
     * @param docType 
     * @return 
     */  
    public JRAbstractExporter getJRExporter(DocType docType) {  
        JRAbstractExporter exporter = null;  
        switch (docType) {  
        case PDF:  
            exporter = new JRPdfExporter();  
            break;  
        case HTML:  
            exporter = new JRHtmlExporter();  
            break;  
        case XLS:  
            exporter = new JExcelApiExporter();  
            break;  
        case XML:  
            exporter = new JRXmlExporter();  
            break;  
        case RTF:  
            exporter = new JRRtfExporter();  
            break;  
        case CSV:  
            exporter = new JRCsvExporter();  
            break;  
        case TXT:  
            exporter = new JRTextExporter();  
            break;  
        }  
        return exporter;  
    }  
  
    /** 
     * 获得相应类型的Content type 
     * @param docType 
     * @return 
     */  
    public String getContentType(DocType docType){  
        String contentType="text/html";  
        switch(docType){  
        case PDF:  
            contentType = "application/pdf";  
            break;  
        case XLS:  
            contentType = "application/vnd.ms-excel";  
            break;  
        case XML:  
            contentType = "text/xml";  
            break;  
        case RTF:  
            contentType = "application/rtf";  
            break;  
        case CSV:  
            contentType = "text/plain";  
            break;  
        }  
        return contentType;  
    }  
  
    public void setAttrToPage(JasperPrint jasperPrint, String report_fileName,  
            String report_type) {  
        session.setAttribute("REPORT_JASPERPRINT", jasperPrint);  
        session.setAttribute("REPORT_FILENAME", report_fileName);  
        session.setAttribute("REPORT_TYPE", report_type);  
    }  
  
    /** 
     * 定义了报表输出类型，固定了可输出类型 
     */  
    public static enum DocType {  
        PDF, HTML, XLS, XML, RTF, CSV, TXT  
    }  
  
    /** 
     * 编译报表模板文件jrxml，生成jasper二进制文件 
     *  
     * @param jrxmlPath 
     * @param jrsperPath 
     * @throws JRException 
     */  
    public void complieJrxml(String jrxmlPath, String jasperPath)  
            throws JRException {  
        JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);  
    }  
  
    /** 
     * 输出html静态页面，必须注入request和response 
     *  
     * @param jasperPath 
     * @param params 
     * @param sourceList 
     * @param imageUrl 
     *            报表文件使用的图片路径，比如 ../servlets/image?image= 
     * @throws JRException 
     * @throws IOException 
     * @throws ServletException 
     */  
    public void servletExportHTML(String jasperPath, Map params,  
            List sourceList, String imageUrl) throws JRException, IOException,  
            ServletException {  
        response.setContentType("text/html");  
        response.setCharacterEncoding("UTF-8");  
        JRAbstractExporter exporter = getJRExporter(DocType.HTML);  
  
        JasperPrint jasperPrint = getPrintWithBeanList(jasperPath, params,  
                sourceList);  
  
        session.setAttribute(  
                ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,  
                jasperPrint);  
  
        PrintWriter out = response.getWriter();  
  
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
        exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);  
        exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageUrl);  
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,false);//不显示px  
        exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.FALSE);  
        exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "<br style='page-break-before:always;'>");   
  
        exporter.exportReport();  
    }  
  
    /** 
     * 输出html静态页面，必须注入request和response 
     *  
     * @param jasperPath 
     * @param params 
     * @param sourceList 
     * @param imageUrl 
     *            报表文件使用的图片路径，比如 ../servlets/image?image= 
     * @throws JRException 
     * @throws IOException 
     * @throws ServletException 
     */  
    public void servletExportHTML(String jasperPath, Map params,  
            Connection conn, String imageUrl) throws JRException, IOException,  
            ServletException {  
        response.setContentType("text/html");  
        response.setCharacterEncoding("UTF-8");  
        JRAbstractExporter exporter = getJRExporter(DocType.HTML);  
  
        JasperPrint jasperPrint = getJasperPrint(jasperPath, params,  
                conn);  
  
        session.setAttribute(  
                ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,  
                jasperPrint);  
  
        PrintWriter out = response.getWriter();  
  
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
        exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);  
        exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageUrl);  
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,false);//不显示px  
        exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.FALSE);  
  
        exporter.exportReport();  
    }  
  
    /** 
     * 生成不同格式报表文档 
     *  
     * @param docType 
     *            文档类型 
     * @param jasperPath 
     */  
    public void servletExportDocument(DocType docType, String jasperPath,  
            Map params, List sourceList, String fileName) throws JRException,  
            IOException, ServletException {  
  
        if (docType == DocType.HTML) {  
            servletExportHTML(jasperPath, params, sourceList, fileName);  
            return;  
        }  
  
        JRAbstractExporter exporter = getJRExporter(docType);  
        // 获取后缀  
        String ext = docType.toString().toLowerCase();  
  
        if (!fileName.toLowerCase().endsWith(ext)) {  
            fileName += "." + ext;  
        }  
        // 判断资源类型  
        if (ext.equals("xls")) {  
            // 要想获得更好的视觉效果，可以添加以下代码  
             exporter.setParameter(  
             JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,  
             Boolean.TRUE); // 删除记录最下面的空行  
              
             exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,  
             Boolean.FALSE);// 删除多余的ColumnHeader  
              
             exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND  
             , Boolean.FALSE);// 显示边框  
        }  
  
        response.setContentType(getContentType(docType));  
        response.setHeader("Content-Disposition", "attachment; filename=\""  
                + URLEncoder.encode(fileName, "UTF-8") + "\"");  
  
        exporter.setParameter(JRExporterParameter.JASPER_PRINT,  
                getPrintWithBeanList(jasperPath, params, sourceList));  
          
        OutputStream outStream = null;  
        PrintWriter outWriter = null;  
          
        if(ext.equals("csv")){//解决中文乱码问题  
            response.setCharacterEncoding("GBK");  
            outWriter = response.getWriter();  
            exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,outWriter);  
        }else{  
            outStream = response.getOutputStream();  
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);  
            if(ext.equals("txt")){  
                exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(80));  
                exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(25));  
                exporter.setParameter(JRTextExporterParameter.CHARACTER_ENCODING,"GBK");  
            }  
        }  
        try {  
            exporter.exportReport();  
        } catch (JRException e) {  
            throw new ServletException(e);  
        } finally {  
            if (outStream != null) {  
                try {  
                    outStream.close();  
                } catch (IOException ex) {  
                }  
            }  
        }  
    }  
  
    /** 
     * 生成不同格式报表文档 
     *  
     * @param docType 
     *            文档类型 
     * @param jasperPath 
     */  
    public void servletExportDocument(DocType docType, String jasperPath,  
            Map params, Connection conn, String fileName) throws JRException,  
            IOException, ServletException {  
  
        if (docType == DocType.HTML) {  
            servletExportHTML(jasperPath, params, conn, fileName);  
            return;  
        }  
  
        JRAbstractExporter exporter = getJRExporter(docType);  
        // 获取后缀  
        String ext = docType.toString().toLowerCase();  
  
        if (!fileName.toLowerCase().endsWith(ext)) {  
            fileName += "." + ext;  
        }  
        // 判断资源类型  
        if (ext.equals("xls")) {  
            // 要想获得更好的视觉效果，可以添加以下代码  
             exporter.setParameter(  
             JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,  
             Boolean.TRUE); // 删除记录最下面的空行  
              
             exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,  
             Boolean.FALSE);// 删除多余的ColumnHeader  
              
             exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND  
             , Boolean.FALSE);// 显示边框  
        }  
  
        response.setContentType(getContentType(docType));  
        response.setHeader("Content-Disposition", "attachment; filename=\""  
                + URLEncoder.encode(fileName, "UTF-8") + "\"");  
  
        exporter.setParameter(JRExporterParameter.JASPER_PRINT,  
                getJasperPrint(jasperPath, params, conn));  
          
        OutputStream outStream = null;  
        PrintWriter outWriter = null;  
          
        if(ext.equals("csv")){//解决中文乱码问题  
            response.setCharacterEncoding("GBK");  
            outWriter = response.getWriter();  
            exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,outWriter);  
        }else{  
            outStream = response.getOutputStream();  
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);  
            if(ext.equals("txt")){  
                exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(80));  
                exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(25));  
                exporter.setParameter(JRTextExporterParameter.CHARACTER_ENCODING,"GBK");  
            }  
        }  
        try {  
            exporter.exportReport();  
        } catch (JRException e) {  
            throw new ServletException(e);  
        } finally {  
            if (outStream != null) {  
                try {  
                    outStream.close();  
                } catch (IOException ex) {  
                }  
            }  
        }  
    }  
  
} 
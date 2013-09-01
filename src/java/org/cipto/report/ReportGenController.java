/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cipto.report;


import com.mysql.jdbc.Connection;
import java.awt.Component;
import java.io.InputStream;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/**
 *
 * @author cipto
 */
public class ReportGenController {
    
  
    /** The faces messages. */  
    private final FacesMessage facesMessages=null;  
  
    /** 
     * Instantiates a new report generator. 
     */  
    public ReportGenController() {  
    }  
  
    /** 
     * Prints report in specified format. P=PDF, H=HTML, C=CSV, E=Excel 
     * 
     * 
     * @param params the params 
     * @param template the template 
     * @param attachmentName the attachment name 
     * 
     * @return true, if prints the report 
     */  
    @SuppressWarnings("unchecked")  
    public final boolean printReport(Map params,  
            String template, String attachmentName) {  
  
        Connection c = getConnection();  
  
        try {  
            FacesContext context = FacesContext.getCurrentInstance();  
            ExternalContext ext = context.getExternalContext();  
            InputStream fis = ext.getResourceAsStream(template);  
  
            JasperReport jasperReport = JasperCompileManager.compileReport(fis);  
  
           
                params.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);  
  
                JasperPrint jasperPrint = JasperFillManager.fillReport(  
                        jasperReport, params, c);  
                HttpServletResponse resp = (HttpServletResponse) ext  
                        .getResponse();  
                resp.setContentType("application/pdf");  
                String filename = new StringBuffer(attachmentName).append(  
                        ".pdf").toString();  
                resp.addHeader("Content-Disposition", "inline; filename="  
                        + filename);  
  
                JasperExportManager.exportReportToPdfStream(jasperPrint, resp  
                        .getOutputStream());  
  
        } catch (Exception e) {  
//            log.error(e);  
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error Dalam Membuat Report"));;
        } finally {  
            try {  
                c.close();  
            } catch (Exception e1) {  
//                log.error(e1);  
            }  
        }  
        return false;  
    }  
  
    private Connection getConnection() {  
//        EntityManager em = (EntityManager) Component.getInstance("EntityManager");  
  
        Connection c = null;  
//        Object del = em.getDelegate();  
  
//        if (del instanceof org.jboss.seam.persistence.HibernateSessionProxy) {  
//            c = ((org.jboss.seam.persistence.HibernateSessionProxy) del)  
//                    .connection();  
//        }  
  
        return c;  
    }  
}

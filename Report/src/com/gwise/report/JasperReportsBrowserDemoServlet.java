package com.gwise.report;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
/**
 * Servlet implementation class JasperReportsBrowserDemoServlet
 */
@WebServlet("/JasperReportsBrowserDemoServlet")
public class JasperReportsBrowserDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws JRException 
     * @see HttpServlet#HttpServlet()
     */
    public JasperReportsBrowserDemoServlet() throws JRException {
        super();
        // TODO Auto-generated constructor stub
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 
		response.setContentType("text/html;charset=UTF-8");
        
		ServletOutputStream servletOutputStream = response.getOutputStream();
		File reportFile = new File(getServletConfig().getServletContext()
		 .getRealPath("/reports/Barcode4JReport.jasper")); // jasper ���� ��ġ ����
		 byte[] bytes = null;
		 
		 try {
			 bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),
			 new HashMap(), new JREmptyDataSource()); // HashMap�� map.put(��para1��, value1)�� �Ķ���͸� ����
			 // ���� ���Ͽ����� $P{para1}�� �Ķ���� ���
			 // JREmptyDataSource ��� ���� connection��ü ��� ����
			 response.setContentType("application/pdf");
			 response.setContentLength(bytes.length);
			 servletOutputStream.write(bytes, 0, bytes.length);
			 servletOutputStream.flush();
			 servletOutputStream.close();
                         
		 } catch (JRException e) {
		 StringWriter stringWriter = new StringWriter();
		 PrintWriter printWriter = new PrintWriter(stringWriter);
		 e.printStackTrace(printWriter);
		 response.setContentType("text/plain");
		 response.getOutputStream().print(stringWriter.toString());
		 }				
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

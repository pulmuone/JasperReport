package com.gwise.report;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//File reportFile = new File(getServletConfig().getServletContext().getRealPath("/reports/Blank_A4_1.jasper"));
		
		 byte[] bytes = null;
		 
		 try 
		 {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.12.29:1521:bestdev", "BCWMS", "BCWMS");
			 
			 HashMap<String, Object> hashMap = new HashMap<String, Object>();
			 hashMap.put("para1", "2001926226");
			 
			 /*
			 // How to : runReportToPdf
			 bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), hashMap, conn);
			 
			 //bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), new HashMap(), new JREmptyDataSource()); 
			 // HashMap에 map.put(“para1”, value1)로 파라미터를 지정
			 // 보고서 파일에서는 $P{para1}로 파라미터 사용
			 // JREmptyDataSource 대신 실제 connection객체 사용 가능
			 response.setContentType("application/pdf");
			 response.setContentLength(bytes.length);
			 servletOutputStream.write(bytes, 0, bytes.length);
			 servletOutputStream.flush();
			 servletOutputStream.close();
			 */
			 
			//How to : runReportToPdfStream
			 InputStream reportStream = getServletConfig().getServletContext().getResourceAsStream("/reports/Blank_A4_1.jasper");
			 JasperRunManager.runReportToPdfStream(reportStream,
					 servletOutputStream,
					 hashMap,
					 conn);
			 reportStream.close();
			 response.setContentType("application/pdf");
			 servletOutputStream.flush();
			 servletOutputStream.close();
                         
		 } catch (JRException e) {
		 StringWriter stringWriter = new StringWriter();
		 PrintWriter printWriter = new PrintWriter(stringWriter);
		 e.printStackTrace(printWriter);
		 response.setContentType("text/plain");
		 response.getOutputStream().print(stringWriter.toString());
		 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

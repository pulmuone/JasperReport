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

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
/**
 * Servlet implementation class JasperReportsBrowserDemoServlet
 */
@WebServlet("/JasperReportsBrowserDemoServlet")
public class JasperReportsBrowserDemoServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws JRException 
     * @see HttpServlet#HttpServlet()
     */
    public JasperReportsBrowserDemoServlet2() throws JRException {
        super();
        // TODO Auto-generated constructor stub
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 
		 try 
		 {
			 //Class.forName("oracle.jdbc.driver.OracleDriver");
			 //Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.12.29:1521:bestdev", "BCWMS", "BCWMS");
			 
			 HashMap<String, Object> hashMap = new HashMap<String, Object>();
			 hashMap.put("para1", "2001926226");

			 response.setContentType("application/pdf"); //OutputWrite하기 전에 type설정해야 함.
			 JasperRunManager.runReportToPdfStream(
					 getServletConfig().getServletContext().getResourceAsStream("/reports/Barcode4JReport.jasper"),
					 response.getOutputStream(),
					 hashMap,
					 new JREmptyDataSource()
					 );
			 response.getOutputStream().flush();
			 response.getOutputStream().close();
                         
		 } catch (JRException e) {
		 StringWriter stringWriter = new StringWriter();
		 PrintWriter printWriter = new PrintWriter(stringWriter);
		 e.printStackTrace(printWriter);
		 response.setContentType("text/plain");
		 response.getOutputStream().print(stringWriter.toString());
		 } 
//		 catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}				
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

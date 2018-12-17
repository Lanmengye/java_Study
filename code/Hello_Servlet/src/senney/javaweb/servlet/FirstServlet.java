package senney.javaweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		ServletConfig config =getServletConfig();
		String servletName = config.getServletName();
		PrintWriter writer= response.getWriter();

		writer.println("≤‚ ‘÷–Œƒ"+"<br />");
		writer.println(servletName+"<br />");
		
		Enumeration<String> paramNames = config.getInitParameterNames();
		String paramName = null;
		while(paramNames.hasMoreElements()) {
			paramName = paramNames.nextElement();
			writer.println(paramName+": "+config.getInitParameter(paramName)+"<br />");
		}
		ServletContext context = config.getServletContext();
		
		Enumeration<String> contextparamNames = context.getInitParameterNames();
		while(contextparamNames.hasMoreElements()) {
			paramName = contextparamNames.nextElement();
			writer.println(paramName+": "+context.getInitParameter(paramName)+"<br />");
		}
		

		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package senney.javaweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletContextListenerTest
 */
@WebServlet("/ServletContextListenerTest")
public class ListenerTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListenerTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String[] countries=(String[]) request.getServletContext().getAttribute("countries");
		for(String country: countries)
		{
			out.println(country);
		}
		getServletContext().setAttribute("aaa", "bbb");
		getServletContext().setAttribute("aaa", "ccc");
		getServletContext().removeAttribute("aaa");
		HttpSession session = request.getSession();
		session.setAttribute("aaa", "111");
		session.setAttribute("bbb", 222);
		session.setAttribute("aaa", 111);
		session.removeAttribute("bbb");
		session.setMaxInactiveInterval(5);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

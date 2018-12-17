package senney.javaweb.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie使用示例
 */
@WebServlet("/UseCookie")
public class UseCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UseCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = 0;
		Cookie[] cookies = request.getCookies();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	      String curTime = format.format(new Date());
		if(cookies != null) {
			for(int i =0; i< cookies.length;i++)
			{
				if("count".equals(cookies[i].getName()))
				{
					count = Integer.parseInt(cookies[i].getValue())+1;
					out.write("Welcome back! this is "+count+" times you visited. ");
					cookies[i].setValue(String.valueOf(count));
					cookies[i].setMaxAge(24*60*60);
					response.addCookie(cookies[i]);
				}
				if("lastTime".equals(cookies[i].getName())) {
					out.write(" The last time you visited was "+ URLDecoder.decode(cookies[i].getValue(),"UTF-8"));
					cookies[i].setValue(URLEncoder.encode(curTime,"UTF-8"));
					cookies[i].setMaxAge(24*60*60);
					response.addCookie(cookies[i]);
				}
				
			}
		}else if(cookies ==null){
			response.getWriter().write("Welcome! this is the first time you visited.");
			Cookie countCookie = new Cookie("count","1");
			countCookie.setMaxAge(24*60*60);
			response.addCookie(countCookie);		  
			Cookie timeCookie = new Cookie("lastTime",URLEncoder.encode(curTime,"UTF-8"));
			countCookie.setMaxAge(24*60*60);
			response.addCookie(timeCookie);	
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

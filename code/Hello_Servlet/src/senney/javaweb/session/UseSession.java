package senney.javaweb.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Session使用示例
 */
@WebServlet("/UseSession")
public class UseSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UseSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 使用request对象的getSession()获取session，如果session不存在则创建一个
		HttpSession session = request.getSession();
		int count =0;
		// 将数据存储到session中
		session.setAttribute("data", "senney");
		session.setAttribute("hobby", new String[]{"reading","running","play tennis"});
		// 获取session的Id
		String sessionId = session.getId();
		// 判断session是不是新创建的
		if (session.isNew()) {
			out.print("session创建成功，session的id是：" + sessionId);
			out.println("<br/>第【"+(++count)+"】次访问……");
			session.setAttribute("count", count);
		} else {
			response.getWriter().print("服务器已经存在该session了，session的id是：" + sessionId);
			count = Integer.parseInt(session.getAttribute("count").toString())+1;
			out.println("<br/>第【"+count+"】次访问……");
			session.setAttribute("count", count);
			if(session.getAttribute("hobby")!=null)
			{
				out.write("<br/> 您的爱好是：");
				for(String s :( String[])session.getAttribute("hobby")) {
					out.write(s+"&nbsp;");
				}
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

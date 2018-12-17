package senney.javaweb.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通过重写URL实现会话管理
 */
@WebServlet("/Top10_TouristAttractions")
public class OverWriteURL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private List<String> londonAttractions;
	private List<String> parisAttractions;

	@Override
	public void init() throws ServletException {
		londonAttractions = new ArrayList<String>(10);
		londonAttractions.add("Buckingham Palace");
		londonAttractions.add("London Eye");
		londonAttractions.add("British Museum");
		londonAttractions.add("National Gallery");
		londonAttractions.add("Big Ben");
		londonAttractions.add("Tower of London");
		londonAttractions.add("National History Museum");
		londonAttractions.add("Canary Wharf");
		londonAttractions.add("2012 Olympic Park");
		londonAttractions.add("St Paul's Carhedral");

		parisAttractions = new ArrayList<String>(10);
		parisAttractions.add("Eiffel Tower");
		parisAttractions.add("Notre Dame");
		parisAttractions.add("The Louvre");
		parisAttractions.add("Champs Elysees");
		parisAttractions.add("Arc de Triomphe");
		parisAttractions.add("Sainte Chapelle Church");
		parisAttractions.add("Les Invalides");
		parisAttractions.add("Musee d'Orsay");
		parisAttractions.add("Montmarte");
		parisAttractions.add("Sacre Couer Basilica");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String city = request.getParameter("city");
		if (city != null && (city.equals("london") || city.equals("paris")))
			showAttractions(request, response, city);
		else
			showMainPage(request, response);
	}

	private void showMainPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html><head><title>Top 10 Tourist Attractions</title></head>"
				+ "<body>Please select a city:<br/><a href ='?city=london'>London</a><br/>"
				+ "<a href='?city=paris'>Paris</a></body></html>");
	}

	private void showAttractions(HttpServletRequest request, HttpServletResponse response, String city)
			throws ServletException, IOException {
		int page = 1;
		String pageParameter = request.getParameter("page");
		if (pageParameter != null) {
			try {
				page = Integer.parseInt(pageParameter);
			} catch (NumberFormatException e) {
			}
			page = page > 2 ? 1 : page;
		}
		List<String> attractions = null;
		if (city.equals("london"))
			attractions = londonAttractions;
		else if (city.equals("paris"))
			attractions = parisAttractions;
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("<html><head><title>Top 10 Tourist Attractions</title></head>");
		writer.println("<body><a href='Top10_TouristAttractions'>Select City</a><hr/>Page"+page+"<hr/>");
		int start = (page-1)*5;
		for (int i = start; i < start+5; i++) {
			writer.println(attractions.get(i)+"<br/>");
		}
		
		writer.println("<hr stylr='color:blue'/><a href='?city="+city+"&page=1'>Page 1</a>");
		writer.println("&nbsp; <a href='?city="+city+"&page=2'>Page 2</a>");
		writer.println("</body></html>");
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

package senney.javaweb.listener;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
        HttpSession session = se.getSession();
        ServletContext context=session.getServletContext();
        AtomicInteger userCounter = (AtomicInteger)context.getAttribute("userCounter");
        int userCount = userCounter.incrementAndGet();
        System.out.println("userCount incremented to: "+userCount);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	 HttpSession session = se.getSession();
         ServletContext context=session.getServletContext();
         AtomicInteger userCounter = (AtomicInteger)context.getAttribute("userCounter");
         int userCount = userCounter.decrementAndGet();
         System.out.println("userCount decremented to: "+userCount);
    }
	
}

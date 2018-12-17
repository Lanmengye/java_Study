package senney.javaweb.listenerTest;

import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class OnlineUserList
 *
 */
@WebListener
public class OnlineUserList implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

	private ServletContext context;
    /**
     * Default constructor. 
     */
    public OnlineUserList() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    @SuppressWarnings("unchecked")
	public void sessionDestroyed(HttpSessionEvent se)  { 
    	Set<String> all = (Set<String>) this.context.getAttribute("onlineList") ;
        all.remove(se.getSession().getAttribute("userid")) ;
        this.context.setAttribute("onlineList",all) ;
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    @SuppressWarnings(value={"rawtypes", "unchecked"})
	public void attributeAdded(HttpSessionBindingEvent se)  { 
    	 Set all = (Set) context.getAttribute("onlineList") ;
         all.add(se.getValue()) ;
         context.setAttribute("onlineList",all) ;
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    @SuppressWarnings(value={"rawtypes"})
	public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	 Set all = (Set) context.getAttribute("onlineList") ;
         all.remove(se.getSession().getAttribute("userid")) ;
         this.context.setAttribute("onlineList",all) ;
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	this.context = sce.getServletContext();
    	this.context.setAttribute("onlineList", new TreeSet<String>());
    }
	
}

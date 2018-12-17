package senney.javaweb.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletContextAttributeListenerDemo
 *
 */
@WebListener
public class ContextAttributeListener implements ServletContextAttributeListener {

    /**
     * Default constructor. 
     */
    public ContextAttributeListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent scae)  { 
    	 System.out.println("Context增加属性 --> 属性名称：" + scae.getName() + "，属性内容：" + scae.getValue()) ;
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent scae)  { 
    	System.out.println("Context删除属性 --> 属性名称：" + scae.getName() + "，属性内容：" + scae.getValue()) ;
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent scae)  { 
    	System.out.println("Context替换属性 --> 属性名称：" + scae.getName() + "，属性内容：" + scae.getValue());
    }
	
}

### Servlet基础

#### 什么是Servlet概述

Servlet（Server Applet）是Java Servlet的简称，称为小服务程序或服务连接器，用Java编写的服务器端程序，主要功能在于交互式地浏览和修改数据，生成动态Web内容。Servlet应用无法独立运行，必须运行在Servlet容器中，Servlet容器将用户的请求传递给Servlet应用，并将结果返回给用户。

#### Servlet运行过程

1. Web服务器检查是否已经装载并创建了Servlet对象，如果已经存在则直接执行第四步

2. 装载并创建该Servlet的一个实例对象。

3. 调用Servlet实例对象的`init()`方法

4. 调用Servlet实例对象的`service()`方法

     - 创建一个用于封装HTTP请求消息的`HttpServletRequest`对象
     - 创建一个代表HTTP响应消息的`HttpServletResponse`对象
     - 调用HttpServlet的`service()`方法并将请求和响应对象作为参数传递进去

5. WEB应用程序被停止或重新启动之前，将调用Servlet的`destroy()`方法并卸载Servlet

  ![](..\..\image\servlet运行过程.png)

<center>servlet运行过程</center>

#### Servlet生命周期

- 加载和实例化：由Servlet容器完成
- 初始化：实例化Servlet后会调用`init()`方法
- 处理用户请求：`service()`方法（doGet或doPost）
- 销毁：销毁之前会调用`destroy()`方法

补充：

在web.xml配置文件中，对每个servlet都有<load-on-startup></load-on-startup>这一配置项，它的含义是：标记容器是否在启动的时候就加载这个servlet。当值为0或者大于0时，表示容器在应用启动时就加载这个servlet；当是一个负数时或者没有指定时，则指示容器在该servlet被选择时才加载。正数的值越小，启动该servlet的优先级越高。

#### Servlet接口及其实现类

- Servlet接口：定义了Servlet和Servlet容器之间的契约，方法如下：
  - `init()`：在Servlet实例化后，调用`service()`前调用的初始化方法，只会被调用一次。
  - `service()`：调用`service()`方法响应用户请求。
  - `destroy()`：当一个Servlet被去除时，Servlet容器将会调用`destroy()`方法，该方法只会被调用一次。
  - `getServletInfo()`：返回Servlet的描述字符串。
  - `getServletConfig`：返回由Servlet容器传给`init()`方法的ServletConfig。

    ```java
    public abstract void init(ServletConfig config) throws ServletException;
    public abstract ServletConfig getServletConfig();
    public abstract String getServletInfo(); 
    public abstract void service(ServletRequest request, ServletResponse 				response) 	throws ServletException, IOException;
    public abstract void destroy();
    ```

- `GenericServlet`抽象类：实现了Servlet接口，未指定协议的Servlet，实现了接口中的`init()`,

  `getServletConfig()`,` getServletInfo()`,`destroy()`方法。

- `HttpServlet`抽象类： 继承`GenericServlet`抽象类，基于http协议的Servlet，重写了`service()`方法并对其进行重载，在`service()`方法中将`ServletRequest`对象包装成`HttpServletRequest`对象，将`ServletResponse`包装成`HttpServletResponse`对象，并调用了重载方法。在重载方法中调用了`doGet()`、`doPost()`等方法，当自定义Servlet类时只需要继承`HttpServlet`类，重写`doGet()`等方法即可。


#### ServletConfig和ServletContext

  - `ServletConfig`：用于封装servlet的配置信息。从一个servlet被实例化后，对任何客户端在任何时候访问有效，但仅对servlet自身有效，一个servlet的ServletConfig对象不能被另一个servlet访问。`ServletConfig`常用方法如下：

    ```java
    // 获取Servlet名称
    public abstract String getServletName();
    // 获取ServletContext
    public abstract ServletContext getServletContext();
    // 获取所有初始化参数名称
    public abstract Enumeration<String> getInitParameterNames();
    // 根据初始化参数名称获取初始化参数值  
    public abstract String getInitParameter(String paramString);
    ```

    配置Servlet初始化参数：

    ```xml
     <servlet>
        <servlet-name>MyFirstServlet</servlet-name>
        <servlet-class>senney.javaweb.FirstServlet</servlet-class>
        <!-- 配置Servlet初始化参数 可以使用ServletConfig对象获取-->
         <init-param>
    			<param-name>name_01</param-name>
    			<param-value>value_01</param-value>
    		</init-param>
    		<init-param>
    			<param-name>name_02</param-name>
    			<param-value>value_02</param-value>
    		</init-param>
        <init-param>
        	<param-name>userName</param-name>
        	<param-value>senney</param-value>
        </init-param>
        <!-- 配置Servlet初始化参数 -->
      </servlet>
    ```

  - `ServletContext`：servlet容器在启动时会加载web应用，并为每个web应用创建**唯一**的`Servletcontext`对象，可以把`ServletContext`看成是一个Web应用的服务器端组件的共享内存，在`ServletContext`中可以存放共享数据。`ServletContext`对象是真正的一个**全局对象**，凡是web容器中的Servlet都可以访问，`ServletContext`常用方法如下

    ```java
    // 根据全局初始化参数名称获取初始化参数值
    public abstract String getInitParameter(String paramString);
    // 获取所有全局初始化参数名称 
    public abstract Enumeration<String> getInitParameterNames();
    // 读取数据
    public abstract Object getAttribute(String paramString);
    // 保存数据
    public abstract void setAttribute(String paramString, Object paramObject);
    // 读取资源文件
    public abstract InputStream getResourceAsStream(String paramString);
    // 页面转发
    public abstract RequestDispatcher getRequestDispatcher(String paramString);
    ```

    配置Web程序应用初始化参数：

    ```xml
    <!-- 与Servlet节点层级相同  -->  
    <context-param>
      	<param-name>application_name</param-name>
      	<param-value>Hello_Servlet</param-value>
      </context-param>
    ```

  #### ServletRequest和ServletResponse

  - ServletRequest：封装了http请求的相关信息，常用方法如下：

  ```java
  // 读取数据
  public abstract Object getAttribute(String paramString);
  // 保存数据
  public abstract void setAttribute(String paramString, Object paramObject);
  // 获取请求主体的MIME类型
  public abstract String getContentType();
  // 获取地址栏或form表单请求参数
  public abstract String getParameter(String paramString);
  ```
  - ServletResponse：Servlet响应，常用方法如下：
  ```java
  public abstract void setContentType(String paramString);
  public abstract void setCharacterEncoding(String paramString);
  public abstract ServletOutputStream getOutputStream() throws IOException;
  public abstract PrintWriter getWriter() throws IOException;
  ```

  #### 页面跳转

- 请求转发
- 请求重定向

  #### 虚拟路径的配置

  - 配置方式

    1. 使用注解配置

       ```java
       // 简单形式
       @WebServlet("/Hello")
       // 复杂形式
       @WebServlet(name = "Hello", urlPatterns="/Hello",loadOnStartup=1,initParams={@WebInitParam(name ="name", value ="senney")})
       ```

    2. 使用web.xml进行配置

       ```xml
         <servlet>
         	<servlet-name>Hello</servlet-name>
         	<servlet-class>senney.javaweb.Hello</servlet-class>
         </servlet>
         <servlet-mapping>
         	<servlet-name>Hello</servlet-name>
         	<url-pattern>/Hello</url-pattern>
         </servlet-mapping>
       ```

  - url-pattern配置内容

    - 配置全路径，例如 `/Hello`
    - 匹配某个目录 ，例如`/login/*`
    - 匹配指定后缀的文件 ，例如`*.txt`



### 会话管理

#### 会话管理概念

会话可简单理解为：用户开一个浏览器，点击多个超链接，访问服务器多个web资源，然后关闭浏览器，整个过程称为一个会话。默认情况下，一个Web服务器无法区分一个HTTP请求是否为第一次访问，HTTP是无状态的，无法记录多次请求响应之间的联系，因此会话管理或会话跟踪成为Web应用开发一个无可避免的主题。

#### 会话管理实现技术

- url重写

  将一个或多个token添加到URL的查询字符串中，每个token通常为key=value形式，例如：`url?key1=value1&key2=value2&key3=value3...`

- 隐藏域

  使用隐藏域来保持状态类似于URL重写技术，但不是将值附加到URL上，而是放到HTML表单的隐藏域中。当表单提交时，隐藏域的值也同时提交到服务器端。隐藏域技术仅在网页有表单时有效。该技术相对于URL重写的优势在于：没有字符数限制，同时无须额外的编码。但该技术同URL一样，不适合跨越多个页面。

- Cookie

- HttpSession

### 监听器

### 过滤器






### 文件操作类File

`File`类位于`java.io`包中，常用来进行文件的自身操作（创建、删除等），File类对象通常表示一个具体的文件或文件夹，常用方法如下：

```java
//构造方法
public File(String pathname);
public File(String parent, String child);

//判断文件是否存在
public boolean exists();
//创建文件
public boolean createNewFile() throws IOException;
//删除文件
public boolean delete();

//判断File对象是否是目录
public boolean isDirectory();
//判断File对象是否是文件
public boolean isFile(); 
//获取文件大小（字节）
public long length();

//创建目录
public boolean mkdir();
public boolean mkdirs();

//列出目录下的所有文件或目录
public File[] listFiles();
```



### IO流总览

- 文件读写操作的基本流程：
  1. 通过`File`类定义一个要操作文件的路径；
  2. 通过字节流或字符流的子类对象为父对象实例化；
  3. 进行数据的读（输入）、写（输出）操作；
  4. 数据流属于资源操作，资源操作必须关闭。

- 字节流与字符流的区别：

  - 读写单位不同：字节流以字节为单位，字符流以字符为单位，根据码表映射字符，一次可能读多个字节。

  - 处理对象不同：字节流能处理所有类型的数据(如图片、视频、音频)等，而字符流只能处理文本数据。

  - 字节流在操作的时候本身是不会用到缓冲区的，是文件本身的直接操作的；而字符流在操作的时候下后是会用到缓冲区的，是通过缓冲区来操作文件

![](..\image\java_IO.png)

<center>IO流体系结构图</center>

### 字节流

- 字节输入流

  - `InputStream`：是所有的字节输入流的父类，它是一个抽象类
  - `FileInputStream`： 从本地文件读取数据的介质流
  - `ByteArrayOutputStream`：从字节数组读取数据的介质流
  - `ObjectInputStream`：用于从流中恢复之前序列化的java对象的装饰流。
  - `BufferedOInputStream`：带缓冲的输入流(装饰流)

  常用方法：

  ```java
  //读取单个字节
  public abstract int read() throws IOException;
  //将数据读取到字节数组中，同时返回读取长度
  public int read(byte b[]) throws IOException;
  //将数据读取到部分字节数组中，同时返回读取的数据长度
  public int read(byte b[], int off, int len) throws IOException;
  //关闭字节输入流
  public void close() throws IOException;
  ```

  常用构造方法(FileInputStream)：

  ```java
  //FileInputStream构造方法
  public FileInputStream(File file) throws FileNotFoundException;
  public FileInputStream(String name) throws FileNotFoundException;
  ```


- 字节输出流

  - `OutputStream`：是所有的字节输出流的父类，它是一个抽象类
  - `FileOutputStream`： 向本地文件写入数据的介质流
  - `ByteArrayOutputStream`：向字节数组写入数据的介质流
  - `ObjectOutputStream`：将可序列化的java对象写入`OutputStream`中的装饰流
  - `BufferedOutputStream`：带缓冲的输出流(装饰流)

  常用方法：

  ```java
  //输出单个字节
  public abstract void write(int b) throws IOException;
  //输出全部字节数组数据
  public void write(byte b[]) throws IOException;
  //输出部分字节数组数据
  public void write(byte b[], int off, int len) throws IOException;
  //关闭字节输出流
  public void close() throws IOException;
  ```

  常用构造方法(FileOutputStream)

  ```java
  //FileOutputStream构造方法
  public FileOutputStream(File file) throws FileNotFoundException；
  public FileOutputStream(File file, boolean append) throws FileNotFoundException;
  public FileOutputStream(String name) throws FileNotFoundException；
  public FileOutputStream(String name, boolean append)throws FileNotFoundException;
  ```

- 对象序列化：对象序列化的本质就是讲内存中保存的对象数据转换为二进制数据流进行传输的操作，通过实现`Serializable`接口可以使得对象能过被序列化，如果对象的的某些属性不需要被序列化，则可以使用关键字`transient`修饰。使用`ObjectOutputStream`可以实现序列化操作，将java对象持久化到本地文件中，使用`ObjectInputStream`可以从保存序列化对象的文件中读取java对象。


### 字符流

- 字符输入流

  - `Reader`：所有字符输入流的父类，它是一个抽象类
  - `InputStreamReader`：使用指定的字符编码集读取字节并将其解码为字符
  - `FileReader`：从文件中读出字符数据
  - `BufferedReader`：从字符输入流中读取文本，缓冲各个字符

  常用方法：

  ```java
  // 读取单个字符
  public int read() throws IOException;
  // 读取数据到字符数组中，返回读取的数据长度
  public int read(char cbuf[]) throws IOException;
  // 读取数据到部分字符数组中，同时返回读取的数据长度
  abstract public int read(char cbuf[], int off, int len) throws IOException;
  // 关闭字符输入流
  abstract public void close() throws IOException;
  
  //FileReader构造方法
  public FileReader(File file) throws FileNotFoundException;
  public FileReader(String fileName) throws FileNotFoundException;
  
  //BufferedReader构造方法
  public BufferedReader(Reader in);
  public BufferedReader(Reader in, int sz);
  
  //BufferedReader常用方法
  public String readLine() throws IOException;
  ```

- 字符输出流

  - `Writer`：所有字符输出流的父类，它是一个抽象类
  - `OutputStreamWriter`：可使用指定的字符编码集将要写入流中的字符编码成字节
  - `FileWriter`：将数据写入字符文件中
  - `BufferedWriter`：将文本写入字符输出流，缓冲各个字符

  常用方法：

  ```java
  //向文件中写入单个字符、字符数组、部分字符数组、字符串、部分字符串
  public void write(int c) throws IOException；
  public void write(char cbuf[]) throws IOException；
  abstract public void write(char cbuf[], int off, int len) throws IOException;
  public void write(String str) throws IOException
  public void write(String str, int off, int len) throws IOException
  
  //关闭字符输出流
   abstract public void close() throws IOException;
  
  //FileWriter构造方法
  public FileWriter(String fileName) throws IOException;
  public FileWriter(String fileName, boolean append) throws IOException;
  public FileWriter(File file) throws IOException;
  public FileWriter(File file, boolean append) throws IOException;
  
  //BufferedWriter构造方法
  public BufferedWriter(Writer out);
  public BufferedWriter(Writer out, int sz);
  ```


### System类对IO的支持

- `System.err`：`PrintStream`类对象，专门负责进行错误信息的输出操作
- `System.out`：`PrintStream`类对象，进行屏幕输出的操作对象
- `System.in`：`InputStream`类对象，结合`Scanner`类实现数据的键盘输入操作
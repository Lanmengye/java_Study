package senney.java.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;

public class IOStreamDemo {

	@Test
	public void readFile_InputStream() throws Exception {
		File f = new File("F:" + File.separator + "Note" + File.separator + "test" + File.separator + "java.txt");
		if (!f.exists())
			return;
		InputStream input = new FileInputStream(f);
		byte[] bytes = new byte[1024];
		int len = 0, foo = 0;
		while ((len = input.read()) != -1) {
			bytes[foo++] = (byte) len;
		}
		input.close();
		System.out.println(new String(bytes, 0, foo));
	}

	@Test
	public void writeFile_OutputStream() throws Exception {
		File f = new File("F:" + File.separator + "Note" + File.separator + "test" + File.separator + "java.txt");
		if (!f.getParentFile().exists())
			f.getParentFile().mkdirs();
		OutputStream output = new FileOutputStream(f, true);
		String content = "Hello, 欢迎学习java io...\r\n";
		output.write(content.getBytes());
		output.close();
	}

	@Test
	public void readFile_FileReader() throws Exception {
		File f = new File("F:" + File.separator + "Note" + File.separator + "test" + File.separator + "java.txt");
		if (!f.exists())
			return;
		Reader reader = new FileReader(f);
		char[] chars = new char[1024];
		int len = 0;
		while ((len = reader.read(chars)) != -1) {
			System.out.println(new String(chars, 0, len));
		}
		reader.close();
	}

	@Test
	public void writeFile_FileWriter() throws Exception {
		File f = new File("F:" + File.separator + "Note" + File.separator + "test" + File.separator + "java.txt");
		if (!f.getParentFile().exists())
			f.getParentFile().mkdirs();
		Writer writer = new FileWriter(f, true);
		String content = "Hello, java io, 测试FileWriter\r\n";
		writer.write(content);
		writer.close();
	}

	@Test
	public void copyFile_BufferedOutputStream() throws Exception {
		InputStream input = new FileInputStream("F:\\Note\\test\\day26_19(多线程)线程的状态图.avi");
		BufferedInputStream buf_in = new BufferedInputStream(input);
		File destf = new File("F:\\Note\\test\\thread_state.avi");
		if (!destf.getParentFile().exists())
			destf.getParentFile().mkdirs();
		OutputStream output = new FileOutputStream(destf);
		BufferedOutputStream buf_out = new BufferedOutputStream(output);

		byte[] bytes = new byte[1024];
		int len = 0;
		while ((len = buf_in.read(bytes)) != -1) {
			buf_out.write(bytes, 0, len);
		}

		buf_out.close();
		output.close();
		buf_in.close();
		input.close();
	}

}

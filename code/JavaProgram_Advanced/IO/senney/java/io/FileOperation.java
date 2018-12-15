package senney.java.io;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileOperation {
	@Test
	public void createFile() throws IOException {
		File file = new File("F:\\java\\test.txt");
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
//		if (file.exists())
//			file.delete();
		file.createNewFile();
		System.out.println("len = " + file.length());
	}

	@Test
	public void printFiles() {
		File file = new File("F:\\Note");
		listFile(file);
	}

	private void listFile(File file) {
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isDirectory())
				listFile(f);
			else
				System.out.println(file.getPath());
		}
	}
}

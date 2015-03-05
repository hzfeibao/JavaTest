package test.file;

import java.io.File;

public class FilePathTest {
	public static void testFilePath(){
		File file = new File("");
		System.out.println("AbsolutePath: " + file.getAbsolutePath());
		System.out.println("Path: " + file.getPath());
		System.out.println("" + FilePathTest.class.getClassLoader().getResource("").getPath());
	}
	
	public static void main(String[] args){
		testFilePath();
	}
}

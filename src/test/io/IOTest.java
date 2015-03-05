package test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class IOTest {
	private static String filePath="/home/zeng_weifeng/Downloads/tmp/test/"+File.separator;;
	public static void testRandomAccessFile() throws IOException{
		String fileName="/home/zeng_weifeng/Downloads/tmp/test/"+File.separator+"hello.txt";
        File f=new File(fileName);
        RandomAccessFile demo=new RandomAccessFile(f,"rw");
        demo.writeBytes("asdsad");
        demo.writeInt(12);
        demo.writeBoolean(true);
        demo.writeChar('A');
        demo.writeFloat(1.21f);
        demo.writeDouble(12.123);
        demo.close();   
	}
	
	public static void testByteStr() throws IOException {
        String fileName= filePath + "testByteStr.txt";
        File f=new File(fileName);
//        OutputStream out =new FileOutputStream(f);
        OutputStream out =new FileOutputStream(f,true);//追加内容
        String str="\r\nhello world你好asdfzzzzz";
        byte[] b=str.getBytes();
        out.write(b);
        out.close();
    }
	
	public static void testReadByte(String[] args) throws IOException {
        String fileName="D:"+File.separator+"hello.txt";
        File f=new File(fileName);
        InputStream in=new FileInputStream(f);
        byte[] b=new byte[1024];
        int len=in.read(b);
        in.close();
        System.out.println("读入长度为："+len);
        System.out.println(new String(b,0,len));
    }
	
	public static void main(String[] args) throws IOException{
//		testRandomAccessFile();
		testByteStr();
	}
}

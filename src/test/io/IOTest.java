package test.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.io.Writer;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
	
	public static void testSeparator(){
		System.out.println(File.separator + " " + File.separatorChar);
		System.out.println(File.pathSeparator + " " + File.pathSeparatorChar);
	}
	
	public static void testFolder(){
		File folder = new File("/home/hduser/tmp");
		if(!folder.exists())
			folder.mkdir();
	}
	
	public static void testList(){
		String path = "/home/hduser";
		File file = new File(path);
		String[] fs = file.list();
		for(String f : fs)
			System.out.println(f);
	}
	
	public static void testListFile() throws IOException{
		String path = "/home/hduser";
		File file = new File(path);
		File[] fs = file.listFiles();
		for(File f : fs)
			System.out.println(f.getName()+ "   path:" + f.getPath() + "   apatch:" + f.getAbsolutePath() + "   cpath:" + f.getCanonicalPath());
	}
	
	public static void testWriteStringToFile() throws IOException{
		String path = "/home/zeng_weifeng/tmp.txt";
		File file = new File(path);
//		OutputStream out =new FileOutputStream(file);
		OutputStream out =new FileOutputStream(file,true);
		String str="   你好abcdefg......   ";
        byte[] b=str.getBytes();
        out.write(b);
        out.close();
	}
	
	public static void testReadFile() throws IOException{
		String path = "/home/zeng_weifeng/tmp.txt";
		File file = new File(path);
		InputStream is = new FileInputStream(file);
		byte[] buf = new byte[1024];//byte[] b=new byte[(int)file.length()];
		int len = is.read(buf);
		is.close();
		System.out.println("len: " + len + " content: " +new String(buf));
		System.out.println(new String(buf,0,len));
	}
	
	public static void testWriteChar() throws IOException{
		String path = "/home/zeng_weifeng/tmp.txt";
		File file = new File(path);
		Writer out = new FileWriter(file, true);
		String str = "\nlolololololololololol..............";
		out.write(str);
		out.close();
	}
	
	public static void testFileReader() throws IOException{
		String path = "/home/zeng_weifeng/tmp.txt";
		File file = new File(path);
		 char[] ch=new char[100];
		Reader reader = new FileReader(file);
		int count = reader.read(ch);
		reader.close();
		System.out.println("len: " + count + "\ncontent: " + new String(ch, 0, count));
	}
	
	public static void testStreamWriter() throws IOException{
		String path = "/home/zeng_weifeng/tmp.txt";
		File file = new File(path);
		Writer out=new OutputStreamWriter(new FileOutputStream(file));
        out.write("\nhello");
        out.close();
        Reader read=new InputStreamReader(new FileInputStream(file));
        char[] b=new char[1024];
        int len=read.read(b);
        System.out.println(new String(b,0,len));
        read.close();
	}
	
	public static void testToLowerCaseInMemoryStream() throws IOException{
		String str="HELLOWORLD";
        ByteArrayInputStream input=new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream output=new ByteArrayOutputStream();
        int temp=0;
        while((temp=input.read())!=-1){
            char ch=(char)temp;
            output.write(Character.toLowerCase(ch));
        }
        String outStr=output.toString();
        input.close();
        output.close();
        System.out.println(outStr);
	}
	
	public static void testPipeStream(){
		Send send = new Send();
		Receive rec = new Receive();
		try{
			send.getOut().connect(rec.getIn());
			new Thread(send).start();
			new Thread(rec).start();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void testPrintFormat() throws IOException{
		String path = "/home/zeng_weifeng/tmp.txt";
		File file = new File(path);
		PrintStream print = new PrintStream(new FileOutputStream(file), true);
		String name = "zhangsan";
		Integer age = 21;
		print.printf("name:%s, age:%d", name, age);
		print.close();
	}
	
	public static void testStreamOutToScreen(){
		OutputStream out = System.out;
		try{
			out.write("Hello world".getBytes());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void testIORedirect(){
		String path = "/home/zeng_weifeng/tmp.txt";
		File file = new File(path);
		System.out.println("out stream will redirect to tmp.txt");
		try{
			System.setOut(new PrintStream(new FileOutputStream(file)));
			System.out.println("now we'll write to file tmp.txt by System.out.println method");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void testRedirectSystemIn(){
		String path = "/home/zeng_weifeng/tmp.txt";
		File file = new File(path);
		try{
			System.setIn(new FileInputStream(file));
			byte[] buf = new byte[1024];
			int len = 0;
			len = System.in.read(buf);
			System.out.println("len: " + len + "\ncontent: " + new String(buf, 0, len));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void testBufferedReader(){
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		System.out.println("请输入内容");
		try {
			str = buf.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("你输入的内容是：" + str);
	}
	
	public static void testScaner(){
		Scanner sca = new Scanner(System.in);
		int i = sca.nextInt();
		double d = sca.nextDouble();
		System.out.println("int: " + i + "   double: " + d);
	}
	
	public static void testScannerFromFile() throws IOException{
		String path = "/home/zeng_weifeng/tmp.txt";
		File file = new File(path);
		Scanner scan = new Scanner(file);
//		String str = scan.next();
		String str = scan.nextLine();
		System.out.println(str);
	}
	
	public static void testDateInOutputStream() throws IOException{
		String path = "/home/zeng_weifeng/tmp.txt";
		File file = new File(path);
		char[] ch = { 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
		for(char c : ch)
			out.writeChar(c);
		out.close();
		
		DataInputStream input = new DataInputStream(new FileInputStream(file));
		char[] chi = new char[10];
		int count = 0;
		char c;
		while((c=input.readChar()) != 'G'){
			chi[count++]=c;
		}
		input.close();
		System.out.println(chi);
	}
	
	public static void testSequenceInputStream() throws IOException{
		String path1 = "/home/zeng_weifeng/tmp.txt";
		File file1 = new File(path1);
		String path2 = "/home/zeng_weifeng/url.txt";
		File file2 = new File(path2);
		String path3 = "/home/zeng_weifeng/seq.txt";
		File file3 = new File(path3);
		InputStream input1 = new FileInputStream(file1);
		InputStream input2 = new FileInputStream(file2);
		OutputStream output = new FileOutputStream(file3);
		SequenceInputStream sis = new SequenceInputStream(input1, input2);
//		byte[] buf = new byte[10];
//		int t = 0;
//		while((t=sis.read(buf))!= -1){
//			output.write(buf, 0, t);
//		}
		int temp = 0;
		while ((temp = sis.read()) != -1) {
			output.write(temp);
		}
		input1.close();
		input2.close();
		output.close();
		sis.close();
	}
	
	public static void testZipOutputStream() throws IOException{
		String path = "/home/zeng_weifeng/tmp.txt";
		File file = new File(path);
		String zpath = "/home/zeng_weifeng/tmp.zip";
		File zfile = new File(zpath);
		InputStream input = new FileInputStream(path);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zfile));
		out.putNextEntry(new ZipEntry(file.getName()));
		out.setComment("only one file tmp.txt");
		byte[] buf = new byte[10];
		int t = 0;
		while((t=input.read(buf))!= -1){
			out.write(buf, 0, t);
		}
		input.close();
		out.close();
	}
	
	public static void testZipOutputStreamByMultiFile() throws IOException{
		String path = "/home/zeng_weifeng/Note";
		File file = new File(path);
		String zpath = "/home/zeng_weifeng/Note/note.zip";
		File zfile = new File(zpath);
		InputStream input = null;
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zfile));
		byte[] buf = new byte[10];
		int t = 0;
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f : files){
				if(f.isDirectory())
					continue;
				input = new FileInputStream(f);
				out.putNextEntry(new ZipEntry(f.getName()));
				while((t = input.read(buf)) != -1){
					out.write(buf, 0, t);
				}
				input.close();
			}
			out.close();
		}
	}
	
	public static void main(String[] args) throws IOException{
//		testRandomAccessFile();
//		testByteStr();
//		testSeparator();
//		testList();
//		testListFile();
//		testWriteStringToFile();
//		testReadFile();
//		testWriteChar();
//		testFileReader();
//		testStreamWriter();
//		testToLowerCaseInMemoryStream();
//		testPipeStream();
//		testPrintFormat();
//		testStreamOutToScreen();
//		testIORedirect();
//		testRedirectSystemIn();
//		testBufferedReader();
//		testScaner();
//		testScannerFromFile();
//		testDateInOutputStream();
//		testSequenceInputStream();
//		testZipOutputStream();
		testZipOutputStreamByMultiFile();
	}
}

class Send implements Runnable{
	private PipedOutputStream out = null;
	public Send(){
		out = new PipedOutputStream();
	}
	
	public PipedOutputStream getOut() {
		return out;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String message = "Hello world";
		try{
			out.write(message.getBytes());
			out.close();
			System.out.println("send msg: " + message);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

class Receive implements Runnable{
	private PipedInputStream in = null;
	public Receive(){
		in = new PipedInputStream();
	}
	public PipedInputStream getIn() {
		return in;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte[] buf = new byte[1024];
		int len = 0;
		try{
			len = in.read(buf);
			in.close();
			System.out.println("receive msg: "  + new String(buf, 0, len));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
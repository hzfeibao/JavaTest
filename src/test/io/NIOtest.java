package test.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOtest {
	public static void testFileChannel() throws IOException{
		String path = "/home/zeng_weifeng/tmp.txt";
		RandomAccessFile raFile = new RandomAccessFile(path, "rw");
		FileChannel inChannel = raFile.getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(10);
		int bytesRead = inChannel.read(buf);
		while(bytesRead != -1){
			System.out.println("read: " + bytesRead);
			buf.flip();
			while(buf.hasRemaining()){
				System.out.println((char)buf.get());
			}
			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		raFile.close();
	}
	
	public static boolean testFileChannel(Integer i){
		return false;
	}
	
	public static void testTransFrom() throws IOException{
		String path = "/home/zeng_weifeng/tmp.txt";
		RandomAccessFile fromFile = new RandomAccessFile(path, "rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		String path2 = "/home/zeng_weifeng/tmp2.txt";
		RandomAccessFile toFile = new RandomAccessFile(path2, "rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0;
		long count = fromChannel.size();// fromFile.length()???
		toChannel.transferFrom(fromChannel, position, count);
		
	}
	
	public static void testByteBufferCopyFile(String path, String newPath) throws IOException{
		RandomAccessFile fromFile = new RandomAccessFile(path, "rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile(newPath, "rw");
		FileChannel toChannel = toFile.getChannel();
		
		ByteBuffer buf = ByteBuffer.allocate(10240);
		int bytesRead = fromChannel.read(buf);
		while(bytesRead != -1){
			buf.flip();
			while(buf.hasRemaining()){
				toChannel.write(buf);
//				toChannel.write(buf[], 0, bytesRead);
//				toChannel.write(buf, bytesRead);
			}
			buf.clear();
			bytesRead = fromChannel.read(buf);
		}
		fromFile.close();
		toFile.close();
	}
	
	public static void testTransFromCopyFile(String path, String newPath) throws IOException{
		RandomAccessFile fromFile = new RandomAccessFile(path, "rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile(newPath, "rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0;
		long count = fromChannel.size();// fromFile.length()???
		toChannel.transferFrom(fromChannel, position, count);
	}
	
	public static void testByteBufferAndTransFrom() throws IOException{
		String path = "/home/zeng_weifeng/Downloads/josso-1.8.5.tar.gz";
		String path1 = "/home/zeng_weifeng/Downloads/josso-1.8.5bf.tar.gz";
		String path2 = "/home/zeng_weifeng/Downloads/josso-1.8.5tf.tar.gz";
		System.out.println("testByteBufferCopyFile start: " + System.currentTimeMillis());
		testByteBufferCopyFile(path, path1);
		System.out.println("testByteBufferCopyFile   end: " + System.currentTimeMillis());
		System.out.println("testTransFrom start: " + System.currentTimeMillis());
		testTransFromCopyFile(path, path2);
		System.out.println("testTransFrom   end: " + System.currentTimeMillis());
	}
	
	public static void testSelector() throws IOException{
		int port = 10030;//端口
		ServerSocketChannel serverChannel = ServerSocketChannel.open();//serverChannel
		ServerSocket serverSocket = serverChannel.socket();//socket
		Selector selector = Selector.open();//selector
		serverSocket.bind(new InetSocketAddress(port));//绑定端口
		serverChannel.configureBlocking(false);//非阻塞
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);//监听事件
		while(true){
			int n = selector.select();
			if(n == 0)
				continue;
			Iterator it = selector.selectedKeys().iterator();
			while(it.hasNext()){
				SelectionKey key = (SelectionKey)it.next();
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					SocketChannel channel = server.accept();
					if (channel == null) {

					}
					channel.configureBlocking(false);
					channel.register(selector, SelectionKey.OP_READ);
				}
				if(key.isReadable()){
					readDataFromSocket(key);
				}
				if(key.isWritable()){
					//write to channel
				}
				if(key.isConnectable()){
					//
				}
				it.remove();
			}
		}
	}
	
	public static void testOpKey(){
		System.out.println("OP_CONNECT:" + SelectionKey.OP_CONNECT);
		System.out.println("OP_ACCEPT:" + SelectionKey.OP_ACCEPT);
		System.out.println("OP_READ:" + SelectionKey.OP_READ);
		System.out.println("OP_WRITE:" + SelectionKey.OP_WRITE);
	}
	
	public static void readDataFromSocket(SelectionKey key){
//		key.toString();
	}
	
	public static void main(String[] args) throws IOException{
//		testFileChannel();
//		testTransFrom();
//		testByteBufferAndTransFrom();
//		testSelector();
		testOpKey();
	}
}

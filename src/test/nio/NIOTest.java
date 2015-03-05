package test.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {

	public static void main(String[] args) {
//		testByteBuffer();
		copyFile();
	}

	public static void testByteBuffer() {
		ByteBuffer buf = ByteBuffer.allocate(256);
		while (true) {
			// 从标准输入流读入一个字符
			int c;
			try {
				c = System.in.read();

				// 当读到输入流结束时，退出循环
				if (c == -1)
					break;
				// 把读入的字符写入 ByteBuffer 中
				buf.put((byte) c);
				// 当读完一行时，输出收集的字符
				if (c == '\n') {
					// 调用 flip() 使 limit 变为当前的 position 的值 ,position 变为 0,
					// 为接下来从 ByteBuffer 读取做准备
					buf.flip();
					// 构建一个 byte 数组
					byte[] content = new byte[buf.limit()];
					// 从 ByteBuffer 中读取数据到 byte 数组中
					buf.get(content);
					// 把 byte 数组的内容写到标准输出
					System.out.print(new String(content));
					// 调用 clear() 使 position 变为 0,limit 变为 capacity 的值，
					// 为接下来写入数据到 ByteBuffer 中做准备
					buf.clear();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void copyFile(){
		String file = "/home/zeng_weifeng/Downloads/tmp/Android-Phone-20140101-01-000007.jpg";
		String cpFile = "/home/zeng_weifeng/Downloads/tmp/vtlive/Android-Phone-20140101-01-000007.jpg";
		FileInputStream fin = null;
		FileOutputStream fout = null;
		FileChannel fcin = null;
		FileChannel fcout = null;
		try {
			fin = new FileInputStream(file);
			fout = new FileOutputStream(cpFile);
			fcin = fin.getChannel();
			fcout = fout.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(10240);
			while(true){
				buffer.clear();
				int r = fcin.read(buffer);
				if(r == -1){
					break;
				}
				buffer.flip();
				fcout.write(buffer);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
			fcin.close();
			fcout.close();
			fin.close();
			fout.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}

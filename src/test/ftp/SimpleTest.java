package test.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class SimpleTest {
	private static FTPClient ftp;
	
	public static boolean connect(String path, String ip, int port, String username, String password) throws SocketException, IOException{
		boolean result = false;
		ftp = new FTPClient();
		int reply;
		ftp.connect(ip, port);
		ftp.login(username, password);
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		reply = ftp.getReplyCode();
		if(!FTPReply.isPositiveCompletion(reply)){
			ftp.disconnect();
			System.out.println("failed to connection to ftp server");
			return result;
		}
		ftp.changeWorkingDirectory(path);
		result = true;
		return result;
	}
	
	public static void listDirFile() throws Exception{
		System.out.println("list dir and file");
		FTPFile[] dirs = ftp.listDirectories();
		if(dirs.length > 0){
			System.out.println("list dirs");
			for(int i = 0; i < dirs.length; i++){
				System.out.println(dirs[i].getName());
			}
		}else{
			System.out.println("no dirs in ftp server");
		}
		
		FTPFile[] fs = ftp.listFiles();
		if(fs.length > 0){
			System.out.println("list files");
			for(int i = 0; i < fs.length; i++){
				System.out.println(fs[i].getName());
			}
		}else{
			System.out.println("no file in ftp server");
		}
		
	}
	
	public static void changePath(String path){
		try{
			System.out.println("changeWorkingDirectory: " + ftp.changeWorkingDirectory(path));
			System.out.println("makeDirectory: " + ftp.makeDirectory(path));
			System.out.println("changeWorkingDirectory: " + ftp.changeWorkingDirectory(path));
		}catch(IOException e){
			try {
				System.out.println("makeDirectory: " + ftp.makeDirectory(path));
				System.out.println("changeWorkingDirectory: " + ftp.changeWorkingDirectory(path));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void upload(File file) throws IOException{
		if(file.isDirectory()){
			ftp.makeDirectory(file.getName());
			ftp.changeWorkingDirectory(file.getName());
			String[] files = file.list();
			for(int i = 0; i < files.length; i++){
				File f = new File(file.getPath() + File.separator + files[i]);
				if(f.isDirectory()){
					upload(f);
					ftp.changeToParentDirectory();
				}else{
					File fe = new File(file.getPath() + File.separator + files[i]);
					FileInputStream input = new FileInputStream(fe);
					ftp.storeFile(fe.getName(), input);
					input.close();
				}
			}
		}else{
			File fe = new File(file.getPath());
			FileInputStream input = new FileInputStream(fe);
			ftp.storeFile(fe.getName(), input);
			input.close();
		}
	}
	
	public static void disConn() throws Exception{
		ftp.disconnect();
	}
	
	public static boolean download(String path, String filename, String localPath) throws Exception{
		ftp.changeWorkingDirectory(path);
		//file not exist
		FTPFile[] fs = ftp.listFiles();
		for(FTPFile f : fs){
			if(f.getName().equals(filename)){
				System.out.println("file exist and download file");
				File localFile = new File(localPath + File.separator + f.getName());
				OutputStream os = new FileOutputStream(localFile);
				ftp.retrieveFile(f.getName(), os);
				os.close();
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		boolean cr = connect("","192.168.1.15", 21, "liveuploader", "liveuploader");
		File file = new File("/home/zeng_weifeng/20140421054200_14.297294147728401.jpg");
		if(cr == true){
			System.out.println("success to connect to ftp server");
//			listDirFile();
			changePath("20150112/12");
//			upload(file);
//			download("","20140421054200_14.297294147728401.jpg","/home/zeng_weifeng/Pictures/2015");
		}else{
			System.out.println("cant connect to ftp server");
		}
		
		
		
		disConn();
	}
}

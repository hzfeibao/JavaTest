package test.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 
 * @author sugen
 * TODO 压缩文件为zip
 */
public class ZipFileUtil {
	
	/*
	 * 压缩文件
	 */
	public static void zipFile(File file, ZipOutputStream zos, String dir) throws IOException{
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f : files)
				if(file.exists())
					zipFile(f, zos, dir + file.getName() + File.pathSeparatorChar);
		}else{
			String entryName = null;
			if(!"".equals(dir))
				entryName = dir + file.getName();
			else
				entryName = file.getName();
			ZipEntry entry = new ZipEntry(entryName);
			zos.putNextEntry(entry);
			BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[10240];
			int len = 0;
			while((len = is.read(buf)) != -1)
				zos.write(buf, 0, len);
			is.close();
			zos.closeEntry();
		}
	}
	
	/*
	 * 压缩多个文件
	 */
	public static void zipMultiFile(String[] filenames, ZipOutputStream zos, String dir) throws IOException{
		for(int i = 0; i < filenames.length; i++){
			File file = new File(filenames[i]);
			if(file.exists())
				zipFile(file, zos, dir);
		}
	}
	
	/*
	 * 压缩多个文件
	 */
	public static void zipMultiFile(List<String> filenames, ZipOutputStream zos, String dir) throws IOException{
		for(int i = 0; i < filenames.size(); i++){
			File file = new File(filenames.get(i));
			if(file.exists())
				zipFile(file, zos, dir);
		}
	}
	
	public static void test() throws IOException{
		String path = "/var/tmp/uploadpic";
		String[] ns = {"Android-Phone-20140101-01-000001.jpg",
						"Android-Phone-20140101-01-000004.jpg",
						"Android-Phone-20140101-01-000005.jpg",
						"Android-Phone-20140101-01-000007.jpg",
						"Android-Phone-20140101-01-000008.jpg"};
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(path + "/" + DateTimeUtil.getDateTimeStr("yyyyMMddHHmmss") + ".zip")));
		for(int i = 0; i < ns.length; i++){
			File file = new File(path + "/" + ns[i]);
			zipFile(file, zos, "");
		}
		zos.close();
	}
	
	public static void main(String[] args) throws IOException{
//		File file = new File("/var/tmp/uploadpic/Android-Phone-20140101-01-000008.jpg");
//		System.out.println(file.getAbsolutePath());
		test();
	}
}

package cn.zjc.html;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;

public class Html2Word {
	
	@Test
	public static void writeWordFile(ByteArrayInputStream bais,
			String outputPath) {
		try {
			if (!"".equals(outputPath)) {
				POIFSFileSystem poifs = new POIFSFileSystem();
				//DirectoryEntry directory = poifs.getRoot();
				//DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
				FileOutputStream ostream = new FileOutputStream(outputPath);
				poifs.writeFilesystem(ostream);
				bais.close();
				ostream.close();
				System.out.println("end......");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 将字符串写入输入流
	 * @param content
	 * @return
	 */
	public static ByteArrayInputStream getInputStream(String content){
		byte b[] = null;
		try {
			b = content.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		ByteArrayInputStream bais = new ByteArrayInputStream(b); 
		return bais;
	}
	
	/**
	 * 将文件写入输入流
	 * @param fileName 文件的绝对路径
	 * @param encoding 使用的编码格式
	 * @return
	 */
	public static ByteArrayInputStream getInputStreamByFile(String fileName,String encoding){
		ByteArrayInputStream bais = null;;
		InputStreamReader isr = null;;
		BufferedReader reader = null;;
		try {
			isr = new InputStreamReader(new FileInputStream(fileName),encoding);
			String str;
			String info="";
			reader = new BufferedReader(isr);
			while((str=reader.readLine())!=null){
				info=info+str;
			}
			byte b[] = info.getBytes();
			bais = new ByteArrayInputStream(b);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("编码格式错误!!!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("文件不存在!!!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("文件读取失败!!!");
		} finally {
			try {
				if(isr !=null){
					isr.close();
					isr = null;
				}
				if(reader !=null){
					reader.close();
					reader = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bais;
	}
	
	public static void main(String[] args) {
		String content = "<html><div style=\"text-align: center\"><span style=\"font-size: 28px\"><span style=\"font-family: 黑体\"> 制度发布通知<br /> <br /> </span></span></div></html>";
		
		ByteArrayInputStream bais = getInputStream(content);
		writeWordFile(bais, "E:/a.doc");
	}
}

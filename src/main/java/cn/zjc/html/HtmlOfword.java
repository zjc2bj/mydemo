package cn.zjc.html;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

public class HtmlOfword {

	@Test
	public void toWord() throws IOException{
		XWPFDocument doc = new XWPFDocument();
		//此处为html源码来源可为文件也可从页面获取
		InputStreamReader sr = new InputStreamReader(new FileInputStream("C:\\index.html"),"UTF-8");

		String str;
		String info="";
		BufferedReader reader = new BufferedReader(sr);
		while((str=reader.readLine())!=null){
			info=info+str;
		}

		//生成文件名
		String filePath = "QrySaleMainTestFileMS" + ".doc";
		//指定文件存放路径
		OutputStream out = new FileOutputStream("C:\\"+filePath);


		byte b[] = info.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		POIFSFileSystem poifs = new POIFSFileSystem();
		DirectoryEntry directory = poifs.getRoot();
		DocumentEntry documentEntry = directory.createDocument("WordDocument",
				bais);
		poifs.writeFilesystem(out);
		bais.close();
		doc.write(out);
		out.close();
	}
	

}

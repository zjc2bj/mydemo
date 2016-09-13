package cn.zjc.demo.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 用于输出的类
 * @author zjc
 *
 */
public class LogWriter {
	//是否在源文件上进行追加
	private static boolean append = true;
	//读取配置文件中的输出路径
	//private static String logFileName = LogWriter.getUrl();
	private static OutputStream os;
	
	private String filePath;
	
	public LogWriter(String filePath) {
		this.filePath = filePath;
	}
	
	
	/**
	 * 将字符串的数据写到输出流进行输出
	 * @param info
	 * @throws IOException
	 */
	public void log(String info){
		try {
			os = new FileOutputStream(filePath, append);;
			os.write((info+"\r\n").getBytes("utf-8"));
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("文件输出失败");
		} finally{
			if(os!=null){
				try {
					os.close();
					os = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 将字符串集合写到输出流进行输出
	 * @param list
	 */
	public void log(List<String> list){
		for(String str:list){
			log(str);
		}
	}
	
	public static void main(String[] args) {
		new LogWriter("C:\\1.txt").log("aaa");
		new LogWriter("C:\\1.txt").log("bbb");
		new LogWriter("C:\\1.txt").log("ccc");
	}
}

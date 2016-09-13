package cn.zjc.portband;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * 本地服务器的端口绑定器
 * @author Administrator
 *
 */
public class ServerPortBind {
	private static String hostname = "127.0.0.1";
	public static void main(String[] args) {
		//String hostname = "127.0.0.1";
		int port = 135;
		try {
			bind(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 开放本地主机（服务器）的指定端口
	 * @param hostname
	 * @param port
	 * @throws IOException 端口绑定失败
	 */
	public static void bind(int port) throws IOException {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress(hostname,port ));
			PortBandFrame.result.append("绑定端口号：" + port + "成功！ \n");
			
			if (port == PortBandFrame.maxPort) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				PortBandFrame.result.append("绑定完成!\n");
				PortBandFrame.scanning.setText("绑定完成！！！");
			}
			
			//System.out.println(ss.isBound());
			while(true){
				System.out.println(port+" 端口等待链接");
				ss.accept();
				System.out.println(port+" 端口已链接...");
			}
		} finally{
			try {
				if(ss != null){
					ss.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

package cn.zjc.portband;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * ���ط������Ķ˿ڰ���
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
	 * ���ű�������������������ָ���˿�
	 * @param hostname
	 * @param port
	 * @throws IOException �˿ڰ�ʧ��
	 */
	public static void bind(int port) throws IOException {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress(hostname,port ));
			PortBandFrame.result.append("�󶨶˿ںţ�" + port + "�ɹ��� \n");
			
			if (port == PortBandFrame.maxPort) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				PortBandFrame.result.append("�����!\n");
				PortBandFrame.scanning.setText("����ɣ�����");
			}
			
			//System.out.println(ss.isBound());
			while(true){
				System.out.println(port+" �˿ڵȴ�����");
				ss.accept();
				System.out.println(port+" �˿�������...");
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

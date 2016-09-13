package cn.zjc.portscan;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ServerConnection {
	private static final int timeout=50;
	
	public static boolean isPortConn(String hostname,int port) throws IOException{
		InetAddress address;
		SocketAddress sockaddr;
		Socket socket = new Socket();
        try {
        	address = InetAddress.getByName(hostname);
        	
        	// ���������Ͷ˿ںŴ����׽��ֵ�ַ
    		sockaddr = new InetSocketAddress(address, port);
            // �����׽������ӵ�����ָ����ʱֵ�ķ�����
        	socket.connect(sockaddr, timeout);
        	if(socket.isConnected()){
        		return true;
        	}
        	return false;
		}  finally{
			try {
				// �رմ��׽���
				if(socket != null){
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

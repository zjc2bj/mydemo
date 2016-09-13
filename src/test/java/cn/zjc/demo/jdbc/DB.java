package cn.zjc.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private static final String url ="jdbc:oracle:thin:@(DESCRIPTION =  (LOAD_BALANCE = ON)  (FAILOVER = ON)  (ADDRESS = (PROTOCOL = TCP)(HOST = 10.27.160.39)(PORT = 1521))  (ADDRESS = (PROTOCOL = TCP)(HOST = 10.27.160.40)(PORT = 1521))  (CONNECT_DATA =  (SERVICE_NAME = CARDTEST.global)  (FAILOVER_MODE = (TYPE = select) (METHOD = basic)) ) )" ;// orcl为数据库的SID
	private static final String user = "CARD_SYS_2012";
	private static final String password = "CARD_SYS_2012";
	private static final String driverName = "oracle.jdbc.driver.OracleDriver";
	
	private static  Connection conn = null;
	//类加载的时候执行初始化,单例模式;
	static{
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获取连接失败!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("DriverManager加载失败!!!");
		}
	}
	
	public static Connection getConn() throws Exception{
		try {
			if(conn.isClosed()){
				conn =  DriverManager.getConnection(url,user,password);
			}
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new RuntimeException("获取连接！！！");
			throw new Exception(e);//
		}
		//return null;
	}
	
	public static void close(Connection conn,Statement stmt,ResultSet rs){
		try{
			if(rs!=null){
				rs.close();
				rs = null;
			}
			if(stmt!=null){
				stmt.close();
				stmt = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("关闭异常！！！");
		}
	}
	
	/*public static void main(String[] args) {
		Connection conn2 = getConn();
		System.out.println(conn2);
	}*/
}

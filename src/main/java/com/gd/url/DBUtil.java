package com.gd.url;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static Connection getConn(){
    	 Connection conn = null;
    	 try{
    		 Class.forName("oracle.jdbc.driver.OracleDriver");
    		 conn = DriverManager.getConnection("jdbc:oracle:thin:@10.27.160.39:1521:CARDTEST2", "card_sys_1130", "card_sys_1130");
    	 }catch(Exception e){
    		  System.out.println("数据库异常");
    		  e.printStackTrace();
    	 }
    	 return conn;
    }
}

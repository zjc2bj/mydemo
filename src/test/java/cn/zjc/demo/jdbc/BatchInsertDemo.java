package cn.zjc.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import org.junit.Test;

public class BatchInsertDemo {

	public void excute() {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("insert into ex_log (EX_LOG_ID,EX_LOG_DATE) values (?,?)");

			// 关闭事务自动提交
			try {
				conn = DB.getConn();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn.setAutoCommit(false);
			pstmt = (PreparedStatement) conn.prepareStatement(sql.toString());
			/*
			 * for (int i = 0; i < list.size(); i++) { ExLog exLog =
			 * (ExLog)list.get(i); pst.setString(1, exLog.getExLogId());
			 * pst.setString(2, exLog.getExLogDate()); // 把一个SQL命令加入命令列表
			 * pst.addBatch(); }
			 */
			Long startTime = System.currentTimeMillis();
			
			// 执行批量更新
			pstmt.executeBatch();
			conn.commit();
			
			Long endTime = System.currentTimeMillis();
			System.out.println("cost：[" + (endTime - startTime)+"] Millis ");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DB.close(conn,pstmt,null);
		}
	}
	
	//@Test
	public void test(){
		Connection conn = null;
		try {
			conn = DB.getConn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(conn);
	}
}

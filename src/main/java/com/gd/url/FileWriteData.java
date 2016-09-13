package com.gd.url;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FileWriteData {

	public static PreparedStatement ps = null;
	public static ResultSet rs = null;
	private static final int perCount = 5;

	/**
	 * 验证TBL_MODULEURLACL表中是否已经存在uniqueid的数据
	 * @param conn
	 * @param uniqueid
	 * @return
	 */
	public static boolean checkModuleId(Connection conn, String uniqueid) {
		String sql = "select UNIQUEID from CARD_SYS_1130.TBL_MODULEURLACL where trim(UNIQUEID) =?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uniqueid);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 批量插入数据
	 * 
	 * @param list
	 * @param flag
	 * @param conn
	 * @throws Exception
	 */
	public static void batchListInsertData(List<String> list, int flag,
			Connection conn) throws Exception {
		String sql = "select ID from TBL_MODULE where trim(UNIQUEID) = ?";
		String moduleSql = "insert into CARD_SYS_1130.TBL_MODULEURLACL(ID,VERSION,URL,MODULE_ID,UNIQUEID) values (SEQ_MODULEURLACL.NEXTVAL,?,?,?,?)";
		String serviceSql = "insert into CARD_SYS_1130.TBL_SERVICEMETHODACL (ID,VERSION,SERVICENAME,REQUESTURL,METHODNAME) values (SEQ_SERVICEMETHODACL.NEXTVAL,?,?,?,?)";
		if (flag == 0) {
			for (int i = 0; i < list.size(); i++) {
				String s = list.get(i);
				String[] dataStr = s.split("]");
				String id = null;
				ps = conn.prepareStatement(sql);
				ps.setString(1, ((dataStr[2]).toString()).substring(
						((dataStr[2]).toString()).indexOf("[") + 1,
						((dataStr[2]).toString()).length()));
				rs = ps.executeQuery();
				while (rs.next()) {
					id = rs.getString("ID");
				}
				if (!checkModuleId(conn, id + "_"
						+ ((dataStr[3]).toString()).replace('[', ' ').trim())) {
					conn.setAutoCommit(false);
					ps = conn.prepareStatement(moduleSql);
					ps.setInt(1, 0);
					ps.setString(2, ((dataStr[3]).toString()).replace('[', ' ')
							.trim());
					ps.setInt(3, Integer.parseInt(id));
					ps.setString(4, id
							+ "_"
							+ ((dataStr[3]).toString()).replace('[', ' ')
									.trim());
					ps.addBatch();
				}
			}
			try {
				ps.executeBatch();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
				rs.close();
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				String s = list.get(i);
				String[] dataStr = s.split("]");
				conn.setAutoCommit(false);
				ps = conn.prepareStatement(serviceSql);
				ps.setInt(1, 0);
				ps.setString(
						2,
						((dataStr[3]).toString())
								.replace('[', ' ')
								.trim()
								.substring(
										7,
										((dataStr[3]).toString())
												.replace('[', ' ').trim()
												.lastIndexOf("/")));
				ps.setString(3, ((dataStr[3]).toString()).replace('[', ' ')
						.trim());
				ps.setString(
						4,
						((dataStr[3]).toString())
								.replace('[', ' ')
								.trim()
								.substring(
										((dataStr[3]).toString())
												.replace('[', ' ').trim()
												.lastIndexOf("/") + 1,
										((dataStr[3]).toString())
												.replace('[', ' ').trim()
												.length()));
				ps.addBatch();
			}
			try {
				ps.executeBatch();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}
		}
	}

	/**
	 * 读取文件
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public static void readFileByLinesInsertData(String fileName)
			throws Exception {
		List<String> moduleurlaclList = new ArrayList<String>();
		List<String> servicemethodaclList = new ArrayList<String>();
		File file = new File(fileName);
		BufferedReader reader = null;
		Connection conn = DBUtil.getConn();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				String[] ss = tempString.split("]");
				if (((ss[5]).toString()).replace('[', ' ').trim()
						.equals("MOD_MET_OFF")) {
					// MOD_MET_OFF的内容放到moduleurlaclList
					moduleurlaclList.add(tempString);
				} else {
					// 其他的内容放到servicemethodaclList
					servicemethodaclList.add(tempString);
				}
				if (moduleurlaclList.size()% perCount == 0) {
					batchListInsertData(moduleurlaclList, 0, conn);
					moduleurlaclList.clear();
				}
				if (servicemethodaclList.size()% perCount == 0) {
					batchListInsertData(servicemethodaclList, 1, conn);
					servicemethodaclList.clear();
				}
			}
			if (moduleurlaclList.size() > 0) {
				batchListInsertData(moduleurlaclList, 0, conn);
				moduleurlaclList.clear();
			}
			if (servicemethodaclList.size() > 0) {
				batchListInsertData(servicemethodaclList, 1, conn);
				servicemethodaclList.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		try {
			readFileByLinesInsertData("a.log");
		} catch (Exception e) {
		}
	}

}
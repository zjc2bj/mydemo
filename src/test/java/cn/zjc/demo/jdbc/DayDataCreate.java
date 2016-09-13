package cn.zjc.demo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DayDataCreate {
	public static Map<String,String> map  =  new HashMap<String, String>();
	//统计各个天表的数据量
	//public static  Map<String,String>  dayMap  =  new HashMap<String, String>();
	
	static{
		//map.put("TBL_TRADE_D01","2008-10-01");
		String dayName = "TBL_POINT_D";
		String dateStr = "2008-10-";
		for(int i=3; i<4; i++){
			if(i==8 )
				continue;
			if(i<10){
				dayName = dayName + "0";
				dateStr = dateStr+"0";
			}
			dayName = dayName + i;
			dateStr = dateStr + i;
			map.put(dayName, dateStr);
			dateStr = "2008-10-";
			dayName = "TBL_POINT_D";
		}
	}
	
	public int insertSql(String dayName,String dateStr) throws Exception{
		Connection conn = null;
		try{
		//获取连接异常后，跳出程序
			conn = DB.getConn();
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new Exception(e1);
		}
		
		PreparedStatement preState = null;
		LogWriter writer = new LogWriter("C:\\dayLog.txt");
		
		int recordNum = 0 ;
		
		try{
			conn.setAutoCommit(false);
			
			long diffTime = 0 ;long startTime = 0; long endTime = 0;
			startTime = System.currentTimeMillis();
			//logger.info("[--START--]number = (" +0+ "),time = (0) has append" );
			System.out.println(getTime()+"[--START--]number = (" +0+ "),time = (0) has append" );
			writer.log(getTime()+"  [--START--]number = (" +0+ "),time = (0) has append" );
			
			String insertSql = "";
			for(int i=1;i<21;i++){  
				for(int j=0;j<16;j++){
					insertSql = "INSERT /*+ append */ " +
							"INTO "+dayName+"(id,   version,   base_account_type,   bankcard,   org_id,   trade_status,   adjust_type,   subaccounttype,   cardtxno,   error_type,   balance,   cardloc_id,   rposno,   blackinterval,   rulesetid,   relatedid,   uniqueid,   cardasn,   carduser_type,   goodscategory_id,   oper_id,   activityid,   deposittype,   chequeid,   batchno,   epssn,   pointdescription,   recordtime,   graytradeid,   originaldate,   psamasn,   volumn,   bringpointsid,   accounttype,   invoicetype,   offlinetxno,   acquirer_id,   banktrace,   eftsn,   gift_id,   trade_type,   carduser_id,   rpossn,   businessdate,   customer_level,   cardtype,   maintradeid,   discount,   account_id,   realamount,   amount,   operno,   issuer_id,   banktermid,   subtradetype,   eftno,   occurtime) " +
							"(SELECT  seq_trade.nextval,   version,   base_account_type,   bankcard,   org_id,   trade_status,   adjust_type,   subaccounttype,   cardtxno+"+j*j+",   error_type,   balance+"+i*j+",   cardloc_id,   rposno,   blackinterval,   rulesetid,   relatedid,   uniqueid,   cardasn+"+i*i+",   carduser_type,   goodscategory_id,   oper_id,   activityid,   deposittype,   chequeid,   batchno,   epssn,   pointdescription,   recordtime,   graytradeid,   originaldate,   psamasn,   volumn,   bringpointsid,   accounttype,   invoicetype,   offlinetxno,   acquirer_id,   banktrace,   eftsn,   gift_id,   trade_type,   carduser_id,   rpossn,   to_date('"+dateStr+"','yyyy-mm-dd'),   customer_level,   cardtype,   maintradeid,   discount,   account_id,   realamount+"+i*j+",   amount+"+j*j+",   operno+"+j*j+",   issuer_id,   banktermid,   subtradetype,   eftno,   occurtime " +
									"FROM (SELECT ROWNUM ROW1,T.*  FROM  tbl_trade_d08 T)  where ROW1 > "+5000*j+" and ROW1 <="+5000*(j+1)+")";
					preState = conn.prepareStatement(insertSql);	 
					int updateCount = preState.executeUpdate();
					conn.commit();  
					recordNum = recordNum+updateCount;
					preState.close();
					System.out.println(getTime()+"j=="+j); 
				}
				System.out.println(getTime()+"i=="+i); 
				endTime = System.currentTimeMillis();
				diffTime = endTime - startTime;
				//logger.info("Insert"+dayName+" with /*+ append */ "+80000*i+" cost "+diffTime);
				System.out.println(getTime()+"Insert"+dayName+" with /*+ append */ "+80000*i+" cost "+diffTime);
				writer.log(getTime()+"  Insert"+dayName+" with /*+ append */ "+80000*i+" cost "+diffTime);
			}  
			endTime = System.currentTimeMillis();
			
			System.out.println(getTime()+"[--END--]number = (" + 10000 + "),time = ( " + (endTime - startTime) + ")");
			writer.log(getTime()+"  [--END--]number = (" + 10000 + "),time = ( " + (endTime - startTime) + ")");
		}catch(Exception e){
			e.printStackTrace();
			writer.log(getTime()+"  "+e.getMessage());
		}finally{
			DB.close(conn, preState, null);
		}
		return recordNum;
	}
	
	/**
	 * 将日期按照一定得格式 转化为字符串
	 */
	public static String date2LogString(Date date,String format){
		DateFormat sdf = new SimpleDateFormat(format);
		String strDate = sdf.format(date);
		return strDate;
	}
	
	public String getTime(){
		String format = "yyyy-MM-dd HH:mm:ss";
		Date date = new Date();
		String currentTime = date2LogString(date,format);
		return "["+currentTime+"] ";
	}
	

	/**
	 * @param writer
	 */
	public  void excuteCreate(LogWriter writer) {
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			try {
				Entry<String, String> entry = iterator.next();
				String dayName = entry.getKey(); 
				String dayStr = entry.getValue();
				
				//造天表数据，并统计耗时
				int count = new DayDataCreate().insertSql(dayName,dayStr);
				iterator.remove();
				writer.log(new DayDataCreate().getTime()+"=====  insert into  "+dayName+"  ["+count+"] =====");
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		if(map.size()!=0){
			excuteCreate(writer);
		}
	}
	

	public static void main(String[] args) {
		/*boolean temp = false;
		//定义任务开始时间 
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2013,9,8,0,0,0);
		
		while(!temp){
			try {
				Thread.sleep(300000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(cal2.compareTo(cal1) > 0){
				System.out.println("#############Time Is Wrong###############");;
				continue;
			}
			temp = true;
		}*/
			
		 //造数据
		 LogWriter writer = new LogWriter("C:\\dayResult.txt");
		new DayDataCreate().excuteCreate(writer);
		
		/*LogWriter monWriter = new LogWriter("C:\\monthResult.txt");
		new MothDataCreate().excuteCreateMonthData(monWriter);*/
		
		//查询天表对应的数量
		/*try {
			new DayDataCreate().excuteQueryDayTabCount();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
//========================================================================================================================	
	//查询天表的数据
	public long queryDayCount(Connection conn,String dayName) throws Exception{
		long value = 0l;
		String sql = "select count(*) from "+dayName+"";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//pstmt.setString(1, dayName);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			value = rs.getLong(1);
		}
		return value;
	}
	
	//执行遍历查询 天表数量
	/**
	 * @param writer
	 * @throws Exception 
	 */
	public  void excuteQueryDayTabCount() throws Exception {
		Connection conn = null;
		try{
		//获取连接异常后，跳出程序
			conn = DB.getConn();
		} catch (Exception e) {
			throw new Exception(e);
		}
		
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			try {
				Entry<String, String> entry = iterator.next();
				String dayName = entry.getKey(); 
				//String dayStr = entry.getValue();
				//System.out.println(dayName);
				long count = new DayDataCreate().queryDayCount(conn,dayName);
				System.out.println(dayName+"  ==  "+count);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}
}

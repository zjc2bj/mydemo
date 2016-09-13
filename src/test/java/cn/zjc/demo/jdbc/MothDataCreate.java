package cn.zjc.demo.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//import org.junit.Test;

public class MothDataCreate {
	//public static Map<String,String> map  =  new HashMap<String, String>();
	public static List<String> list = new ArrayList<String>();
	
	static{
		String str = "2008-09-";
		for(int i=10;i<=30;i++){
			if(i<10)
				str = str+"0";
			str = str+i;
			list.add(str);
			str = "2008-09-";
		}
	}
	
	public int insertSql(String monthName,String dateStr) throws Exception{
		Connection conn = null;;
		try {
			//获取连接异常后，跳出程序
			conn = DB.getConn();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new Exception(e1);
		}
		PreparedStatement preState = null;
		LogWriter writer = new LogWriter("C:\\monthLog.txt");
		
		int recordNum = 0 ;
		
		try{
			conn.setAutoCommit(false);
			
			long diffTime = 0 ;long startTime = 0; long endTime = 0;
			startTime = System.currentTimeMillis();
			//logger.info("[--START--]number = (" +0+ "),time = (0) has append" );
			System.out.println("[--START--]number = (" +0+ "),time = (0) has append" );
			writer.log(getTime()+"  [--START--]number = (" +0+ "),time = (0) has append" );
			
			String insertSql = "";
			for(int i=1;i<=20;i++){  
				for(int j=0;j<16;j++){
					insertSql = "INSERT /*+ append */ " +
							"INTO "+monthName+"(id,   version,   base_account_type,   bankcard,   org_id,   trade_status,   adjust_type,   subaccounttype,   cardtxno,   error_type,   balance,   cardloc_id,   rposno,   blackinterval,   rulesetid,   relatedid,   uniqueid,   cardasn,   carduser_type,   goodscategory_id,   oper_id,   activityid,   deposittype,   chequeid,   batchno,   epssn,   pointdescription,   recordtime,   graytradeid,   originaldate,   psamasn,   volumn,   bringpointsid,   accounttype,   invoicetype,   offlinetxno,   acquirer_id,   banktrace,   eftsn,   gift_id,   trade_type,   carduser_id,   rpossn,   businessdate,   customer_level,   cardtype,   maintradeid,   discount,   account_id,   realamount,   amount,   operno,   issuer_id,   banktermid,   subtradetype,   eftno,   occurtime) " +
							"(SELECT  seq_trade.nextval,   version,   base_account_type,   bankcard,   org_id,   trade_status,   adjust_type,   subaccounttype,   cardtxno+"+j*j+",   error_type,   balance+"+i*j+",   cardloc_id,   rposno,   blackinterval,   rulesetid,   relatedid,   uniqueid,   cardasn+"+i*i+",   carduser_type,   goodscategory_id,   oper_id,   activityid,   deposittype,   chequeid,   batchno,   epssn,   pointdescription,   recordtime,   graytradeid,   originaldate,   psamasn,   volumn,   bringpointsid,   accounttype,   invoicetype,   offlinetxno,   acquirer_id,   banktrace,   eftsn,   gift_id,   trade_type,   carduser_id,   rpossn,   to_date('"+dateStr+"','yyyy-mm-dd'),   customer_level,   cardtype,   maintradeid,   discount,   account_id,   realamount+"+i*j+",   amount+"+j*j+",   operno+"+j*j+",   issuer_id,   banktermid,   subtradetype,   eftno,   occurtime " +
									"FROM (SELECT ROWNUM ROW1,T.*  FROM  tbl_trade_D06 T)  where ROW1 > "+5000*j+" and ROW1 <="+5000*(j+1)+")";
					preState = conn.prepareStatement(insertSql);	 
					int updateCount = preState.executeUpdate();
					conn.commit();  
					recordNum = recordNum+updateCount;
				}
				System.out.println("i=="+i); 
				endTime = System.currentTimeMillis();
				diffTime = endTime - startTime;
				//logger.info("Insert"+dayName+" with /*+ append */ "+80000*i+" cost "+diffTime);
				System.out.println("Insert"+monthName+" with /*+ append */ "+80000*i+" cost "+diffTime);
				writer.log(getTime()+"  Insert"+monthName+" with /*+ append */ "+80000*i+" cost "+diffTime);
				preState.close();
			}  
			endTime = System.currentTimeMillis();
			
			System.out.println("[--END--]number = (" + 10000 + "),time = ( " + (endTime - startTime) + ")");
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
	 * @param date	日期
	 * @param format	转化的格式
	 * @return	String
	 */
	public static String date2LogString(Date date,String format){
		DateFormat sdf = new SimpleDateFormat(format);
		String strDate = sdf.format(date);
		return strDate;
	}
	
	public String getTime(){
		//yyyy-MM-dd HH:mm:ss
		String format = "yyyy-MM-dd HH:mm:ss";
		Date date = new Date();
		String currentTime = date2LogString(date,format);
		return "["+currentTime+"] ";
	}
	
	/**
	 * @param writer
	 */
	public  void excuteCreateMonthData(LogWriter writer) {
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			try{
				String dateStr = iterator.next();
				//System.out.println(dateStr);
				int count = new MothDataCreate().insertSql("TBL_TRADE_M200809",dateStr);
				iterator.remove();
				writer.log(new MothDataCreate().getTime()+" insert into  "+dateStr+"  ["+count+"]");
			} catch(Exception e){
				e.printStackTrace();
				continue;
			}
		}
		
		if(list.size()!=0){
			excuteCreateMonthData(writer);
		}
	}
	
	public static void main(String[] args) {
		LogWriter writer = new LogWriter("C:\\monthResult.txt");
		new MothDataCreate().excuteCreateMonthData(writer);
	}

	
	
	/*@Test
	public  void run(){
		Connection conn =  DB.getConn();
		try {
			conn.close();
			Connection conn2 =  DB.getConn();
			System.out.println(conn2.isClosed());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}

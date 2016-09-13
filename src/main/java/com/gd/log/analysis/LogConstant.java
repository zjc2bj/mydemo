package com.gd.log.analysis;
/**
 * 日志解析配置信息
 * 	日志解析 需符合格式
 * 	[--D--]cutDate[TBL_TRADE_D0101]
 * 	......
 * 	[--S--]number = (1),time = (0)
 * 	[--1--]............(1075)
 * 	[--2--]............(382945) 
 *		[--3--]............(892) 
 *		[--4--]............(3110)
 *		[--E--]number = (1),time = ( 388031)
 *		......
 * 	[--G--]cutDate[TBL_TRADE_D0101](10253958)
 * @author 
 *
 */
public class LogConstant {
	//日志存放路径
	public static final String sourceFilePath = "C:\\smice.2013-11-05_06.log";
	//解析的表格输出路径
	public static final String targetExcelPath = "C:\\smice.2013-11-05_06.xls";
	//日志编码格式
	public static final String encoding = "GBK";
	
	//日志的时间对应格式。如:[07:59:27]-->HH:mm:ss		[2013-10-06 01:08:43]-->yyyy-MM-dd HH:mm:ss
	public static final String str2DateFormat = "yyyy-MM-dd HH:mm:ss";
	//每日工作表 (开始、结束时间) 行显示的日期格式
	public static final String dayFormat = "HH:mm:ss";
	//"环境"工作表（开始时间）行 显示的日期格式
	public static final String initFormat = "HH:mm:ss";
	
	//每天工作表名的长度(天表名的长度)   如：tbl_trade_d01-->13;  tbl_trade_d0101-->15
	public static final int dayNameLen ="tbl_trade_d0101".length();	//截取“tbl_trade_” 后 作为   生成的工作表名称 
	//生成的工作表名 需要  截去的字符串（注意大小写）
	public static final String cutStr = "TBL_TRADE_";
	
	//每次切表数量(W)
	public static final int onceInsertCount = 5;

	//切表步骤名称(天工作表)
	public static final String countNum = "切换次数";
	public static final String step1 = "（1）临时全表数据导入临时表（5w条）";
	public static final String step2 = "（2）天表数据复制到月表";
	public static final String step3 = "（3）删除临时全表中的数据【所有临时小表】";
	public static final String step4 = "（4）truncate临时小表";
	public static final String stepCount = "总计";
	public static final String startTime = "开始时间";
	public static final String endTime = "结束时间";
	
	//总耗时
	public static final String step1TotalTime = "step1 total time";
	public static final String step2TotalTime = "step2 total time";
	public static final String step3TotalTime = "step3 total time";
	public static final String step4TotalTime = "step4 total time";
	
	//"汇总"工作表参数
	public static  int startMonthCount = 0;	//切换前月表起始数量
	
	public static final String dayTableName = "表名";
	public static final String dayTableCount = "测试数量";
	public static final String monCountBefCut = "切换前月表数据量（W条）[m201211]";	
	public static final String startCutTime = "开始时间";
	public static final String costScond = "用时(s)";
	public static final String costHours = "用时(hh:mm:ss)";
	/*
	//切表步骤名称(天工作表)
	public static final String countNum = "cutDate count";
	public static final String step1 = "cutDate count";
	public static final String step2 = "cutDate count";
	public static final String step3 = "cutDate count";
	public static final String step4 = "cutDate count";
	public static final String stepCount = "time sum";
	public static final String startTime = "once cutDate startTime";
	public static final String endTime = "once cutDate endTime";
	*/
	
	/*	
	public static final String dayTableName = "day table name";		//表名
	public static final String dayTableCount = "day table data count";		//测试数量
	public static final String monCountBefCut = "month data count before cut";		//切换前月表数据量（W条）[m200810]
	public static final String startCutTime = "strat cut data time";		//开始时间
	public static final String costScond = "cut data cost scond time";		//用时(S)
	public static final String costHours = "cut data cost hour time";		//用时(S)
	 */
}

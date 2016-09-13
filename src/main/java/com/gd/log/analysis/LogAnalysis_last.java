package com.gd.log.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import jxl.write.Number;

import org.junit.Test;

public class LogAnalysis_last {
	private static WritableWorkbook workbook = null;
	private static int sheetCount = 1;

	// 类加载的时候 先指定包含目标文件的workbook
	static {
		try {
			workbook = Workbook.createWorkbook(new File(LogConstant.targetExcelPath));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Test
	public void readFile2Excel() {
		EveCutData eveCutData = null;
		List<EveCutData> eveCutList =null;
		
		EveDayData eveDayData = null;
		List<EveDayData> eveDayList =  new ArrayList<EveDayData>();;
		
		boolean startOneDayFlag = false;
		BufferedReader bufReader = null;
		try {
			bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(LogConstant.sourceFilePath)), LogConstant.encoding));// 考虑到编码格式
			
			String lineTxt = null;
			while ((lineTxt = bufReader.readLine()) != null) {
				if (lineTxt.indexOf("cutDate") != -1 && lineTxt.indexOf("--D--") != -1) {
					startOneDayFlag = true;
					eveCutList = new ArrayList<EveCutData>();
					eveDayData = new EveDayData();
					// 截取表名 作为sheet名称 TBL_TRADE_
					eveDayData.dayName = lineTxt.substring(lineTxt.indexOf(LogConstant.cutStr) +LogConstant.cutStr.length(), lineTxt.indexOf(LogConstant.cutStr) + LogConstant.dayNameLen);
					eveDayData.perStartTime = lineTxt.substring(lineTxt.indexOf("[") + 1, lineTxt.indexOf("]"));
					continue;
				} 
				if (lineTxt.indexOf("cutDate") != -1 && lineTxt.indexOf("--G--") != -1) {
					eveDayData = createOneDaySheet(eveDayData, eveCutList, workbook);	//创建“每天切换”工作表
					eveDayList.add(eveDayData);
					startOneDayFlag = false;
					continue;
				} 
				if(startOneDayFlag){
					eveCutData = analysisData(lineTxt, eveCutData, eveCutList);
				}
			}
			if (startOneDayFlag) {
				eveDayData = createOneDaySheet(eveDayData, eveCutList, workbook);	//创建“每天切换”工作表
				eveDayList.add(eveDayData);
			}
			
			//创建并统计 “汇总” 工作表
			updateInitSheet(eveDayList);
		} catch (UnsupportedEncodingException e) {
			System.out.println("不支持的编码格式");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("未找到目标文件");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}  finally {
			try {
				workbook.write();
				bufReader.close();
				workbook.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public EveCutData analysisData(String line, EveCutData eveCutData, List<EveCutData> dataList) {
		if (line.indexOf("[--S--]") != -1) {
			eveCutData = new EveCutData();
			eveCutData.startTime = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
		}
		if (line.indexOf("[--1--]") != -1) {
			String stepStr1 = line.substring(line.lastIndexOf("(") + 1, line.lastIndexOf(")"));
			eveCutData.step1 = Long.parseLong(stepStr1.trim()) / 1000;
		}
		if (line.indexOf("[--2--]") != -1) {
			String stepStr2 = line.substring(line.lastIndexOf("(") + 1, line.lastIndexOf(")"));
			eveCutData.step2 = Long.parseLong(stepStr2.trim()) / 1000;
		}
		if (line.indexOf("[--3--]") != -1) {
			String stepStr3 = line.substring(line.lastIndexOf("(") + 1, line.lastIndexOf(")"));
			eveCutData.step3 = Long.parseLong(stepStr3.trim()) / 1000;
		}
		if (line.indexOf("[--4--]") != -1) {
			String stepStr4 = line.substring(line.lastIndexOf("(") + 1, line.lastIndexOf(")"));
			eveCutData.step4 = Long.parseLong(stepStr4.trim()) / 1000;
		}
		if (line.indexOf("[--E--]") != -1) {
			eveCutData.endTime = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
			eveCutData.stepCount = eveCutData.step1 + eveCutData.step2 + eveCutData.step3 + eveCutData.step4;
			dataList.add(eveCutData);
		}
		return eveCutData;
	}

	/**
	 * 添加“每天切换” 的工作表
	 * @param logDataList
	 * @param eveDayData.dayName
	 * @param workbook
	 * @return
	 */
	private EveDayData createOneDaySheet(EveDayData eveDayData, List<EveCutData> logDataList, WritableWorkbook workbook) {
		long STEP_1_COUNT = 0l;
		long STEP_2_COUNT = 0l;
		long STEP_3_COUNT = 0l;
		long STEP_4_COUNT = 0l;
		
		try {
			String[] sheetNames = workbook.getSheetNames();
			for (String name : sheetNames) {
				if (name.equals(eveDayData.dayName)) {
					eveDayData.dayName = eveDayData.dayName + "_1";
				}
			}

			WritableSheet sheet = workbook.createSheet(eveDayData.dayName, sheetCount++);

			// 设置格式
			WritableCellFormat strCellFormat = new WritableCellFormat();
			strCellFormat.setAlignment(Alignment.RIGHT);
			jxl.write.DateFormat dateFormat = new jxl.write.DateFormat(LogConstant.dayFormat);
			WritableCellFormat datecellFormat = new WritableCellFormat(dateFormat);
			datecellFormat.setAlignment(Alignment.RIGHT);

			// 写每行对应的标题
			sheet.addCell(new Label(0, 0, LogConstant.countNum));
			sheet.addCell(new Label(0, 1, LogConstant.step1));
			sheet.addCell(new Label(0, 2, LogConstant.step2));
			sheet.addCell(new Label(0, 3, LogConstant.step3));
			sheet.addCell(new Label(0, 4, LogConstant.step4));
			sheet.addCell(new Label(0, 5, LogConstant.stepCount));
			sheet.addCell(new Label(0, 6, LogConstant.startTime));
			sheet.addCell(new Label(0, 7, LogConstant.endTime));
			
			// 写四步的总耗时标题
			sheet.addCell(new Label(0, 10, LogConstant.step1TotalTime));
			sheet.addCell(new Label(0, 11, LogConstant.step2TotalTime));
			sheet.addCell(new Label(0, 12, LogConstant.step3TotalTime));
			sheet.addCell(new Label(0, 13, LogConstant.step4TotalTime));

			// 写每行对应的值
			int columnNum = 1;
			int countNum = 1; // 总LogData数
			for (EveCutData data : logDataList) {
				int rowNum = 0;
				sheet.addCell(new Number(columnNum, rowNum++, countNum++, strCellFormat));
				sheet.addCell(new Number(columnNum, rowNum++, data.step1, strCellFormat));
				sheet.addCell(new Number(columnNum, rowNum++, data.step2, strCellFormat));
				sheet.addCell(new Number(columnNum, rowNum++, data.step3, strCellFormat));
				sheet.addCell(new Number(columnNum, rowNum++, data.step4, strCellFormat));
				sheet.addCell(new Number(columnNum, rowNum++, data.stepCount, strCellFormat));
				sheet.addCell(new DateTime(columnNum, rowNum++, str2Date(data.startTime, LogConstant.str2DateFormat), datecellFormat));
				sheet.addCell(new DateTime(columnNum, rowNum, str2Date(data.endTime, LogConstant.str2DateFormat), datecellFormat));
				columnNum = columnNum + 1;
				
				STEP_1_COUNT += data.step1;
				STEP_2_COUNT += data.step2;
				STEP_3_COUNT += data.step3;
				STEP_4_COUNT += data.step4;
			}
			
			// 写四步的总耗时对应的值
			sheet.addCell(new Number(1, 10, STEP_1_COUNT, strCellFormat));
			sheet.addCell(new Number(1, 11, STEP_2_COUNT, strCellFormat));
			sheet.addCell(new Number(1, 12, STEP_3_COUNT, strCellFormat));
			sheet.addCell(new Number(1, 13, STEP_4_COUNT, strCellFormat));
			
			//计算天表数量
			eveDayData.increaseRate = (countNum-1)*LogConstant.onceInsertCount;
			//统计一天总耗时
			eveDayData.totalCost = STEP_1_COUNT + STEP_2_COUNT  + STEP_3_COUNT  + STEP_4_COUNT;

			return eveDayData;
		} catch (RowsExceededException e) {
			throw new RuntimeException("行数过长溢出。。。。。。");
		} catch (WriteException e) {
			throw new RuntimeException("添加单元格失败。。。。。。");
		}
	}

	/**
	 * 写入完成，向"汇总"工作表中添加对应数据
	 * @param dayName
	 */
	private void updateInitSheet(List<EveDayData> eveDayList) {
		try {
			int startMonthCount = LogConstant.startMonthCount;//月表起始数量
			int initColumn = 1;	//开始的列的位置(第二列)
			
			//创建”汇总“  工作表
			WritableSheet initSheet = workbook.createSheet("汇总", 0);
			initSheet.addCell(new Label(0, 0, LogConstant.dayTableName));
			initSheet.addCell(new Label(0, 1, LogConstant.dayTableCount));
			initSheet.addCell(new Label(0, 2, LogConstant.monCountBefCut));
			initSheet.addCell(new Label(0, 3, LogConstant.startCutTime));
			initSheet.addCell(new Label(0, 4, LogConstant.costScond));
			initSheet.addCell(new Label(0, 5, LogConstant.costHours));
			
			// 设置字符型、整形格式
			WritableCellFormat strCellFormat = new WritableCellFormat();
			strCellFormat.setAlignment(Alignment.RIGHT);
			// 设置日期格式
			jxl.write.DateFormat initFormat = new jxl.write.DateFormat(LogConstant.initFormat);
			WritableCellFormat initCellFormat = new WritableCellFormat(initFormat);
			initCellFormat.setAlignment(Alignment.RIGHT);
			
			for(EveDayData dayData : eveDayList){
				String costTotalHour = getHour(dayData.totalCost);

				initSheet.addCell(new Label(initColumn, 0, dayData.dayName, strCellFormat));
				initSheet.addCell(new Label(initColumn, 1,dayData. increaseRate+"W", strCellFormat));
				initSheet.addCell(new Label(initColumn, 2, startMonthCount+"W", strCellFormat));
				initSheet.addCell(new DateTime(initColumn, 3, str2Date(dayData.perStartTime, LogConstant.str2DateFormat),initCellFormat));
				initSheet.addCell(new Number(initColumn, 4, dayData.totalCost, strCellFormat));
				initSheet.addCell(new Label(initColumn++, 5, costTotalHour, strCellFormat));
				
				// 更新月表数量
				startMonthCount = dayData.increaseRate + startMonthCount;
			}
		} catch (RowsExceededException e) {
			throw new RuntimeException("行数过长溢出。。。。。。");
		} catch (WriteException e) {
			throw new RuntimeException("添加单元格失败。。。。。。");
		}
	}

	/**
	 * @param costScond	秒
	 * @return	小时的字符串表示形式
	 */
	public String getHour(long costScond) {
		String costhor = costScond/3600<10?"0"+costScond/3600 : costScond/3600+"";
		String costmin = (costScond%3600)/ 60<10?"0"+(costScond%3600)/ 60 : (costScond%3600)/ 60+"";
		String costsec = costScond%60<10?"0"+costScond%60 : costScond%60+"";
		String costTotalHour = costhor   +":"+	costmin	 +":"+ costsec;
		return costTotalHour;
	}

	/**
	 * 将字符串转化为日期
	 * @param source 一定格式的日期字符串形式
	 * @param format 转化的格式 "yyyy-MM-dd HH:mm:ss"
	 * @return Date类型
	 */
	private Date str2Date(String source, String format) {
		try {
			DateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(source);
			return date;
		} catch (ParseException e) {
			throw new RuntimeException("转化失败");
		}
	}
}

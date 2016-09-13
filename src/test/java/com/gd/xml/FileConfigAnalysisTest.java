package com.gd.xml;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.gd.xml.FileConfigAnalysis;
@SuppressWarnings("rawtypes")
public class FileConfigAnalysisTest {
	
	@Test
	public void testGetFileConfigMap(){
		String outputPath = FileConfigAnalysis.outputPath;
		System.out.println(outputPath);
		Map<String, String> fileConfigMap = FileConfigAnalysis.getFileConfigMap();
		System.out.println(fileConfigMap);
	}
	
	@Test
	public void testGetXmlConfMap(){
		String outputPath = FileConfigAnalysis.outputPath;
		System.out.println(outputPath);
		List<Map<String,Map>> xmlConfMap = FileConfigAnalysis.getXmlConfMap();
		System.out.println(xmlConfMap);
		/*
		[
		 {para={student=[examid, idcard]}, url={filePath1=c:/exam1.xml, filePath2=c:/exam2.xml, output=/app/log/system}}, 
		 {para={student=[examid, idcard]}, url={filePath1=c:/exam1.xml, filePath2=c:/exam2.xml, output=/app/log/system}}
		]
		*/
	}
}

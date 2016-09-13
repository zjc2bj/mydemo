package cn.zjc.testdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataDef {
	private String filePath ;
	private List<Object> dataList = new ArrayList<Object>();
	private Map<Integer,Object> dataMap= new HashMap<Integer,Object>();
	
	public void addObject(Object obj){
		dataList.add(obj);
	}
	
	public void put(Integer id,Object obj){
		dataMap.put(id, obj);
	}
	
	public Object loadByid(Integer id){
		return dataMap.get(id);
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public List<Object> getDataList() {
		return dataList;
	}
	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}
	public Map<Integer, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<Integer, Object> dataMap) {
		this.dataMap = dataMap;
	}
}

package com.gd.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesLoader {
	private static Properties prop = new Properties();

	public PropertiesLoader(String path) {
		try {
			prop.load(PropertiesLoader.class.getResourceAsStream(path));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("文件读取失败");
		} 

	}
	
	/**
	 * 
	 * @param key
	 * @return 获取指定配置文件中的单个属性值
	 */
	public String getValue(String key){
		String value = prop.getProperty(key);
		return value;
	}
	
	/**
	 * 
	 * @return 获取指定配置文件的所有键值
	 */
	public Set<String> getKeys(){
		Set<String> keys = prop.stringPropertyNames();
		return keys;
	}
	
	/**
	 * 获取properties文件中的所有键值对 封装到map
	 * @return
	 */
	public Map<String,String> getMap(){
		Map<String, String> map= new HashMap<String,String>();
		Set<String> keys = getKeys();
		for(String key:keys) {
			String value = getValue(key);
			map.put(key, value);
		}
		return map;
	}
}

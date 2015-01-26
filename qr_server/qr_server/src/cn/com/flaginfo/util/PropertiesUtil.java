package cn.com.flaginfo.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertiesUtil {

	private static Properties properties;
	private static String BUNDLE_NAME="config/system";
	private static PropertiesUtil util;
	private PropertiesUtil(){
		
	}
	/**
	 * singlton 模式 获取util对象
	 * @return
	 */
	public static PropertiesUtil getInstance(){
		if(null == util){
			util = new PropertiesUtil();
		}
		return util;
	}

	static{
		//开启本地读取模式
		ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);
		properties = new Properties();
		Enumeration<String> e = bundle.getKeys();
		while(e.hasMoreElements()){
			String key = e.nextElement();
			properties.put(key, bundle.getString(key));
		}
	}
	
	/**
	 * 获取配置信息
	 * @param key
	 * @return
	 */
	public String getValue(String key){
		return (String) properties.get(key);
	}
	
	public Enumeration<Object> getKeys(){
		return properties.keys();
	}
}

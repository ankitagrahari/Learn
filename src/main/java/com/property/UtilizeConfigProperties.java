package com.property;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class UtilizeConfigProperties {
	
	public Properties readConfigFileP(String fileName){
		Properties properties = new Properties();
		File file = new File(fileName);
		if(file.exists()){
			try{
				FileInputStream fis = new FileInputStream(file);
				properties.load(fis);
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		properties.put("client", "27");
        properties.put("db", "28");
		
		Map<String, String> treeMap = new TreeMap(properties);
		treeMap.entrySet()
				.stream()
				.forEach(e -> System.out.println(e.getKey()+"---"+e.getValue()));
		
//		Properties newProp = new Properties();
//		System.out.println("---------------------");
//		for(Map.Entry<String, String> entry: treeMap.entrySet()){
//			System.out.println(entry.getKey()+"---"+entry.getValue());
//			newProp.setProperty(entry.getKey(), entry.getValue());
//		}
		
//		newProp.putAll(treeMap);
//		newProp.entrySet()
//				.stream()
//				.forEach(e -> System.out.println(e.getKey()+"---"+e.getValue()));
		

		//src/main/java/com/property/myconf1.properties"
		ConfigProperties prop = new ConfigProperties();
		prop.putAll(treeMap);
		prop.entrySet()
				.stream()
				.forEach(e -> System.out.println(e.getKey()+"---"+e.getValue()));
		
		return prop;
	}
	
	
	public ConfigProperties readConfigFile(String fileName){
		ConfigProperties properties = new ConfigProperties();
		
		File file = new File(fileName);
		if(file.exists()){
			try{
				FileInputStream fis = new FileInputStream(file);
				properties.load(fis);
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		properties.setProperty("we", "27");
		properties.setProperty("ee", "28");
		
		System.out.println(properties.size());
		System.out.println("------------------------------------------------------");
		properties.entrySet()
				.stream()
				.forEach(e -> System.out.println(e.getKey()+"---"+e.getValue()));
		System.out.println("------------------------------------------------------");
		

		return properties;
	}
	
	public static void main(String[] args) {
		UtilizeConfigProperties obj = new UtilizeConfigProperties();
		String config = "src/main/java/com/property/myconf.properties";
		Properties prop = obj.readConfigFile(config);
	}
}

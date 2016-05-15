package com.yabushan.test.util;

	import java.io.BufferedWriter;  
	import java.io.File;  
	import java.io.FileNotFoundException;  
	import java.io.FileOutputStream;  
	import java.io.IOException;  
	import java.io.OutputStreamWriter;  
	import java.io.Writer;  
	import java.util.ArrayList;  
	import java.util.HashMap;  
	import java.util.List;  
	import java.util.Map;  
	  
	import freemarker.template.Configuration;  
	import freemarker.template.Template;  
	import freemarker.template.TemplateException;  
	  
	public class WordTest {  
		 public static void main(String[] args) {  
		        WordTest test = new WordTest();  
		        test.createWord();  
		    }  
	      
	    private Configuration configuration = null;  
	      
	    public WordTest(){  
	        configuration = new Configuration();  
	        configuration.setDefaultEncoding("UTF-8");  
	    }  
	      
	   
	      
	    public void createWord(){  
	        Map<String,Object> dataMap=new HashMap<String,Object>();  
	        getData(dataMap);  
	        System.out.print(this.getClass());
	        
	        configuration.setClassForTemplateLoading(this.getClass(), "/com/yabushan/test/util");  //FTL文件所存在的位置  
	        Template t=null;  
	        try {  
	            t = configuration.getTemplate("test.ftl"); //文件名  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        File outFile = new File("E:/"+Math.random()*10000+"BB.doc");  
	        Writer out = null;  
	        try {  
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));  
	        } catch (FileNotFoundException e1) {  
	            e1.printStackTrace();  
	        }  
	           
	        try {  
	            t.process(dataMap, out);  
	        } catch (TemplateException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    private void getData(Map<String, Object> dataMap) {  
	        dataMap.put("BBTEST", "标题");  
	        dataMap.put("nian", "2016");  
	        dataMap.put("yue", "3");  
	        dataMap.put("ri", "12");  
	        dataMap.put("shenheren", "发哥哥");  
	        dataMap.put("dianhua", "13020265912");  
	        dataMap.put("bianzhi", "CEO");  
	        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
	        for (int i = 0; i < 1000; i++) {  
	            Map<String,Object> map = new HashMap<String,Object>();  
	            map.put("xuhao", i);  
	            map.put("neirong", "内容"+i);  
	            list.add(map);  
	        }  
	          
	          
	        dataMap.put("list", list);  
	    }  
	}  


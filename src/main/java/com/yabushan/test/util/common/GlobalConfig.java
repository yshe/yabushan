/*package com.yabushan.test.util.common;

import org.springframework.context.ApplicationContext;

public class GlobalConfig {
	public static final String	CHARACTER_ENCODING	= "character_encoding";

	*//**
	 * 获取系统字符集设置，默认为UTF-8
	 *//*
	public static String getCharsetEncoding() {
		return getConfigValue(CHARACTER_ENCODING, "UTF-8");
	}

	*//**
	 * 获取指定名称配置项参数的值
	 * @param key 配置项参数名称
	 * @return 先从环境变量中获取，再从上下文配置项中获取
	 *//*
	public static String getConfigValue(String key) {
		return Env.variable(key, ApplicationContext.getProperty(key));
	}

	*//**
	 * 获取指定名称配置项参数的值
	 * @param key 配置项参数名称
	 * @param defaultValue 如果取出来是null或空字符串时的默认值
	 * @return 从环境变量中获取，再从上下文配置项中获取，如果取到的是null或空字符串，则返回默认值
	 *//*
	public static String getConfigValue(String key, String defaultValue) {
		String configValue = Env.variable(key);
		if (StringUtils.isNotEmpty(configValue)) {
			return configValue;
		}
		
		configValue = ApplicationContext.getProperty(key);
		return StringUtils.isEmpty(configValue)?defaultValue:configValue;
	}
}
*/
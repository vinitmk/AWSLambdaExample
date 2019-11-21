package com.sjsuext.cloudproject.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class Utilities {

	private final Properties properties;

	public Utilities() {
		properties = new Properties();

		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public static Date convertStringToDate(String date) {
		Date convertedDate = null;
		if (date != null && !StringUtils.isEmpty(date)) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
				convertedDate = dateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return convertedDate;
	}
	
	public static String convertDateToString(Date date) {
		String convertedDate = null;
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ssZ");
			convertedDate = dateFormat.format(date);
		}
		return convertedDate;
	}
}

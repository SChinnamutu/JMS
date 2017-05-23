package com.perf.blog.jms.util;

import java.text.ParseException;
import java.util.UUID;

import com.google.gson.Gson;
import com.perf.blog.jms.model.Order;

public class BasicUtil {

	public static String getUniqueId(){
		return UUID.randomUUID().toString();
	}
	
	public static String convertToJson(Object response) throws ParseException{
		Gson gson = new Gson();
	    return gson.toJson(response);
	}
	
	public static Order getObjectFromJson(String text){
		Gson gson = new Gson();
		return  gson.fromJson(text.toString(), Order.class);
	}
}

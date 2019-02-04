package com.restapp.models;



public class ModelHelper {
    public static String trim(String param) {
	if (null != param) {
	    return param.trim();
	}
	return "";
    }
}

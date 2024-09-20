package com.tiger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GloablConstant {


    public static String URL_SUFFIX = "transform.php?";
    public static String URL_GET_VERSION = "transform.php?p=get_version"; // GET请求

    public static Map<String,String> concurrentHashMap = new ConcurrentHashMap<>();

}

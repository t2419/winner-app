package com.tiger;

import com.tiger.status.SignInStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GloablConstant {


    public static String URL_SUFFIX = "transform.php?";
    public static String URL_GET_VERSION = "{0}/transform.php?p=get_version"; // GET请求

    public static Integer STATUSCODE_SUCC = 200; // GET请求

    public static String URL_HUANG_GUAN_DOMAIN = "https://www.maizuqiu.com/category-3{0}.html"; // 皇冠域名更新url  GET请求

    public static Map<String,String> BASE_PARAMS_MAP = new ConcurrentHashMap<>();

    public static List<String> DOMAIN_LIST = new ArrayList<>();

    public static SignInStatus SIGN_IN_STATUS = new SignInStatus();

}

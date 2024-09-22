package com.winner.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Map;

public class HttpUtil {


    public static void main(String[] args) {


//        RestTemplate

    }


    public static HttpEntity<LinkedMultiValueMap<Object, Object>> gennerateHttpEntityParam(Object object) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
        LinkedMultiValueMap<Object, Object> paramMap = new LinkedMultiValueMap<>();
        Map<Object,Object> parseObject = JSON.parseObject(JSON.toJSONString(object), Map.class);
        for (Map.Entry entry : parseObject.entrySet()) {
            paramMap.add(entry.getKey(), entry.getValue());
        }
        HttpEntity<LinkedMultiValueMap<Object, Object>> httpEntity = new HttpEntity<>(paramMap, httpHeaders);
        return httpEntity;


    }


}

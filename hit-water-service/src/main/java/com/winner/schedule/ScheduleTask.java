package com.winner.schedule;


import com.tiger.GloablConstant;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Component

//@EnableScheduling
public class ScheduleTask {

    @Autowired
    private RestTemplate restTemplate;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //    @Scheduled(cron = "2/7 * * * * ?")
    public void upDateVer() {

        System.out.println("update version");

        System.out.println(simpleDateFormat.format(System.currentTimeMillis()));

        Document parse = Jsoup.parse("");
    }


    //凌晨三点
    //    @Scheduled(cron = "0 0 3 * * ?")
    public void upDateDomain() {

        String domain_url = "";

        Set<String> domainSet = new HashSet<>();
        for (int i = 1; i < 11; i++) {
            if (i == 1) {
                domain_url = MessageFormat.format(GloablConstant.URL_HUANG_GUAN_DOMAIN, "");
            } else {
                domain_url = MessageFormat.format(GloablConstant.URL_HUANG_GUAN_DOMAIN, "_" + i);
            }
            try {
                ResponseEntity<String> response = restTemplate.exchange(domain_url, HttpMethod.GET, null, String.class, new HashMap<>());
                org.jsoup.nodes.Element body = Jsoup.parse(response.getBody()).body();
                Elements elements = body.getElementsByClass("desc").select("p");
                for (org.jsoup.nodes.Element element : elements) {
                    String text = element.text();
                    if (StringUtils.isNotBlank(text) && text.startsWith("http")) {
                        text = text.replaceFirst("/*\\.*$", "/");
                        domainSet.add(text);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }


}

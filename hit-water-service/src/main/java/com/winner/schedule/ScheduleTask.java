package com.winner.schedule;


import com.tiger.GloablConstant;
import com.winner.bean.Domain;
import com.winner.mapper.DomainMapper;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.tiger.GloablConstant.DOMAIN_LIST;
import static com.tiger.GloablConstant.STATUSCODE_SUCC;

@Component

//@EnableScheduling
public class ScheduleTask {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DomainMapper domainMapper;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private SAXReader saxReader;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void upDateVer() {

        System.out.println("update version");
        List<String> list = domainMapper.selectDomainByType("1");
        for (String domain : list) {
            String url = MessageFormat.format(GloablConstant.URL_GET_VERSION, domain);
            try {
                ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class, new HashMap<>());
                if (STATUSCODE_SUCC == responseEntity.getStatusCodeValue()) {

                    Document document = saxReader.read(new StringReader(responseEntity.getBody()));
                    Element rootElement = document.getRootElement();
                    String ver = rootElement.element("ver").getTextTrim();
                    GloablConstant.BASE_PARAMS_MAP.put("ver", ver);
                    break;
                }
            } catch (Exception e) {

            }
        }

        System.out.println(simpleDateFormat.format(System.currentTimeMillis()));

    }


    //凌晨三点
    @Scheduled(cron = "0 0 3 * * ?")
    public void upDateDomain() {

        String domain_url = "";
        Map<String, Domain> domainsMap = domainMapper.selectDomainsByType("1");
        Map<String, Domain> newDomainMap = new HashMap<>();
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
                        Domain domain = domainsMap.get(text);
                        if (null == domain) {
                            newDomainMap.put(text, domain = new Domain().setAddr(text).setType("1").setDomain(text));
                            domainsMap.put(text, domain);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        if (newDomainMap.size() > 0) {
            domainMapper.insertDomain(newDomainMap.values());
        }

    }


}

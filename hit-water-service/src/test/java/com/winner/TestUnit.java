package com.winner;


import com.tiger.GloablConstant;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class TestUnit {


    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private SAXReader saxReader;

    @Test
    public void getVersion() {

        System.out.println("111111");

        System.out.println(restTemplate == null);


//        ResponseEntity<String> response = restTemplate.exchange("https://hga026.com/transform.php?p=get_version", HttpMethod.GET, null, String.class, new HashMap<>());

        String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><serverrequest><code>666</code><ver>2024-09-21-CRM29_44</ver><site>EN72</site></serverrequest>";

//        if (200 == response.getStatusCode().value() && response.hasBody()) {
        if (true) {

            try {
                Document document = saxReader.read(new StringReader(body));

                Element rootElement = document.getRootElement();
                String ver = rootElement.element("ver").getTextTrim();
                GloablConstant.concurrentHashMap.put("ver", ver);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        }


//        System.out.println(response.getBody());

    }

    @Test
    public void stringUtil() {

        String t = "/...";
        int index = t.lastIndexOf("/");
        System.out.println(index);

//        System.out.println(t.substring(0, index + 1));
        System.out.println(t.matches("/\\.*$"));
        System.out.println(t.endsWith("/\\.*"));
    }

    @Test
    public void traceDomainResponses() {

        Set<String> domainSet = new HashSet<>();


        ResponseEntity<String> response = restTemplate.exchange("https://www.maizuqiu.com/category-3_2.html", HttpMethod.GET, null, String.class, new HashMap<>());
        org.jsoup.nodes.Element body = Jsoup.parse(response.getBody()).body();

        Elements elements = body.getElementsByClass("desc").select("p");
        for (org.jsoup.nodes.Element element : elements) {
            String text = element.text();
            if (StringUtils.isNotBlank(text) &&
                    text.startsWith("http")) {
                int index = text.lastIndexOf("/");
                if (index > -1) {
                    text = text.substring(0, index + 1);
                }

                text = text.replaceAll("/\\.*$","/");
                System.out.println(text);
            }
        }


    }


    @Test
    public void cycleUpdateDomain() {

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
                    if (StringUtils.isNotBlank(text) &&
                            text.startsWith("http")) {
                        text = text.replaceFirst("/*\\.*$","/");
                        System.out.println(text);
                        domainSet.add(text);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(domainSet.size());
    }

}

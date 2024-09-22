package com.winner;


import com.alibaba.fastjson.JSON;
import com.tiger.GloablConstant;
import com.tiger.net.LoginRstVo;
import com.winner.bean.Domain;
import com.winner.mapper.DomainMapper;
import com.winner.utils.HttpUtil;
import com.winner.utils.XmlContextUtil;
import com.winner.vo.respone.LoginStatus;
import com.winner.vo.respone.VersionStatus;
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
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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


        ResponseEntity<String> response = restTemplate.exchange("https://hga026.com///transform.php?p=get_version", HttpMethod.GET, null, String.class, new HashMap<>());

        String body = response.getBody();
//        String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><serverrequest><code>666</code><ver>2024-09-21-CRM29_44</ver><site>EN72</site></serverrequest>";

//        if (200 == response.getStatusCode().value() && response.hasBody()) {
        if (true) {

            try {
                Document document = saxReader.read(new StringReader(body));

                Element rootElement = document.getRootElement();
                String ver = rootElement.element("ver").getTextTrim();
                GloablConstant.BASE_PARAMS_MAP.put("ver", ver);
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

                text = text.replaceAll("/\\.*$", "/");
                System.out.println(text);
            }
        }


    }


    @Test
    public void cycleUpdateDomain() {

        String domain_url = "";

        Map<String, Domain> newDomainMap = new HashMap<>();
        Set<String> domainSet = new HashSet<>();
        Map<String, Domain> domainsMap = domainMapper.selectDomainsByType("1");
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
                        text = text.replaceFirst("/*\\.*$", "/");
                        Domain domain = domainsMap.get(text);
                        if (null == domain) {
                            newDomainMap.put(text, domain = new Domain().setAddr(text).setType("1").setDomain(text));
//                            domainMapper.insert(domainMap.get(text));
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
        System.out.println(domainSet.size());
    }


    @Autowired
    private DomainMapper domainMapper;

    @Test
    public void getDomain() {
//        List<Domain> domainList = domainMapper.getDomainList();
        Map<String, Domain> domainMap = domainMapper.selectDomainsByType("1");
        System.out.println(domainMap.size());
    }

    @Test
    public void getRedirectUrl() {

//        String s = restTemplate.postForObject("", null, String.class, new HashMap<>());
        HttpHeaders httpHeaders = restTemplate.headForHeaders("https://www.hga026.com/");
        URI uri = restTemplate.postForLocation("https://www.hga026.com/", null, new HashMap<>());
        System.out.println(httpHeaders);
    }

    @Test
    public void ajaxReq() throws Exception {

        String url = "https://66.133.92.121/"; // 替换为你要请求的URL
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // 设置请求类型
        con.setRequestMethod("POST");

        // 设置请求头
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        // 读取响应
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // 打印结果
        System.out.println(response.toString());
    }


    @Test
    public void readXml() {
        try {
            VersionStatus unmarshal = (VersionStatus) XmlContextUtil.readContext(VersionStatus.class, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><serverrequest><code>666</code><ver>2024-09-21-CRM29_44</ver><site>EN72</site></serverrequest>");
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void login() {

        String url = "https://hga038.com/transform.php";

        LoginRstVo loginRstVo = new LoginRstVo().setUsername("hitwater01").setPassword("a12345A");

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
            HttpEntity<LinkedMultiValueMap<Object, Object>> httpEntity =  HttpUtil.gennerateHttpEntityParam(loginRstVo);

            ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

            if (exchange.getStatusCodeValue() == GloablConstant.STATUSCODE_SUCC && exchange.hasBody()) {
                LoginStatus loginStatus = (LoginStatus) XmlContextUtil.readContext(LoginStatus.class, exchange.getBody());
            }
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

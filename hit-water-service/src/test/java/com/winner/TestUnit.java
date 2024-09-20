package com.winner;


import com.tiger.GloablConstant;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.util.HashMap;

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


        ResponseEntity<String> response = restTemplate.exchange("https://hga026.com/transform.php?p=get_version", HttpMethod.GET, null, String.class, new HashMap<>());


        if (200 == response.getStatusCode().value() && response.hasBody()) {

            try {
                Document document = saxReader.read(new StringReader(response.getBody()));

                Element rootElement = document.getRootElement();
                String ver = rootElement.element("ver").getTextTrim();
                GloablConstant.concurrentHashMap.put("ver",ver);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        }


        System.out.println(response.getBody());

    }


}

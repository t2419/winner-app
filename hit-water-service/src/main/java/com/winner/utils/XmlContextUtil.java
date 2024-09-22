package com.winner.utils;

import javax.xml.bind.JAXBContext;
import java.io.StringReader;

public class XmlContextUtil {


    public static Object readContext(Class clazz, String body) throws Exception {
        return JAXBContext.newInstance(clazz).createUnmarshaller().unmarshal(new StringReader(body));
    }

}

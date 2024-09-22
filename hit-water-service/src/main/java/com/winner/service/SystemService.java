package com.winner.service;


import com.tiger.GloablConstant;
import com.tiger.net.LoginRstVo;
import com.winner.mapper.DomainMapper;
import com.winner.utils.HttpUtil;
import com.winner.utils.XmlContextUtil;
import com.winner.vo.respone.LoginStatus;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class SystemService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DomainMapper domainMapper;

    @Autowired
    private SAXReader saxReader;

    public void login(LoginRstVo loginRstVo) {

        if (GloablConstant.DOMAIN_LIST.size() == 0) {
            List<String> list = domainMapper.selectDomainByType("1");
            if (0 == list.size()) return;
            GloablConstant.DOMAIN_LIST.addAll(list);
        }
        HttpEntity<LinkedMultiValueMap<Object, Object>> httpEntity = HttpUtil.gennerateHttpEntityParam(loginRstVo);
        for (String domain : GloablConstant.DOMAIN_LIST) {
            String url = domain.concat("/").concat(GloablConstant.URL_SUFFIX);
            try {
                ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
                if (exchange.getStatusCodeValue() == GloablConstant.STATUSCODE_SUCC && exchange.hasBody()) {
                    LoginStatus loginStatus = (LoginStatus) XmlContextUtil.readContext(LoginStatus.class, exchange.getBody());
                    if (loginStatus != null) {
                        GloablConstant.SIGN_IN_STATUS.setUid(loginStatus.getUid())
                                .setUsername(loginStatus.getUsername()).setDomain(loginStatus.getDomain())
                                .setCurrency(loginStatus.getCurrency()).setPayType(loginStatus.getPay_type()).setMid(loginStatus.getMid())
                                .setPasswdSafe(loginStatus.getPasswd_safe()).setStatus(loginStatus.getStatus())
                                .setBlackBoxStatus(loginStatus.getBlackBoxStatus()).setCodeMessage(loginStatus.getCode_message())
                                .setOddF(loginStatus.getOdd_f()).settTink(loginStatus.getT_link()).setLtype(loginStatus.getLtype());
                        break;
                    }
                }
            } catch (Exception e) {

            }
        }

    }

}

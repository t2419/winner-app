package com.winner.controller;


import com.tiger.net.LoginRstVo;
import com.winner.service.SystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {


    @Autowired
    private SystemService service;


    @RequestMapping("/login")
    public void login(LoginRstVo loginRstVo) {

        if (StringUtils.isBlank(loginRstVo.getUsername()) || StringUtils.isBlank(loginRstVo.getPassword())) return;

        service.login(loginRstVo);

    }


}

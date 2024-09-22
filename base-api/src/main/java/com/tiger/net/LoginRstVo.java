package com.tiger.net;

public class LoginRstVo {

//   chk_login=[p,langx,ver,username,password,app,auto,blackbox,userAgent]

   private String p = "chk_login";
   private String ver ="";
   private String username;
   private String password;
   private String app = "N";
//   private String blackbox = "";
//   private String userAgent = "";
//   private String auto = "IZGIGE";
//private String  userAgent=" TW96aWxsYS81LjAgKFdpbmRvd3MgTlQgMTAuMDsgV2luNjQ7IHg2NCkgQXBwbGVXZWJLaXQvNTM3LjM2IChLSFRNTCwgbGlrZSBHZWNrbykgQ2hyb21lLzEyOC4wLjAuMCBTYWZhcmkvNTM3LjM2";
   private String langx="zh-cn";

//    public String getUserAgent() {
//        return userAgent;
//    }
//
//    public LoginRstVo setUserAgent(String userAgent) {
//        this.userAgent = userAgent;
//        return this;
//    }

    public String getVer() {
        return ver;
    }

    public LoginRstVo setVer(String ver) {
        this.ver = ver;
        return this;
    }

    public String getP() {
        return p;
    }

    public LoginRstVo setP(String p) {
        this.p = p;
        return this;
    }


    public String getUsername() {
        return username;
    }

    public LoginRstVo setUsername(String username) {
        this.username = username;
        return this;
    }


    public String getLangx() {
        return langx;
    }

    public LoginRstVo setLangx(String langx) {
        this.langx = langx;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginRstVo setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getApp() {
        return app;
    }

    public LoginRstVo setApp(String app) {
        this.app = app;
        return this;
    }

}

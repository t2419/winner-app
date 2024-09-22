package com.winner.vo.respone;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "serverresponse")
public class LoginStatus {

    private String status;
    private String code_message;
    private String username;
    private String mid;
    private String uid;
    private String passwd_safe;
    private String ltype;
    private String currency;
    private String odd_f;
    private String pay_type;
    private String blackBoxStatus;
    private String domain;
    private String t_link;

    public String getStatus() {
        return status;
    }

    public LoginStatus setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCode_message() {
        return code_message;
    }

    public LoginStatus setCode_message(String code_message) {
        this.code_message = code_message;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginStatus setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getMid() {
        return mid;
    }

    public LoginStatus setMid(String mid) {
        this.mid = mid;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public LoginStatus setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getPasswd_safe() {
        return passwd_safe;
    }

    public LoginStatus setPasswd_safe(String passwd_safe) {
        this.passwd_safe = passwd_safe;
        return this;
    }

    public String getLtype() {
        return ltype;
    }

    public LoginStatus setLtype(String ltype) {
        this.ltype = ltype;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public LoginStatus setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getOdd_f() {
        return odd_f;
    }

    public LoginStatus setOdd_f(String odd_f) {
        this.odd_f = odd_f;
        return this;
    }

    public String getPay_type() {
        return pay_type;
    }

    public LoginStatus setPay_type(String pay_type) {
        this.pay_type = pay_type;
        return this;
    }

    public String getBlackBoxStatus() {
        return blackBoxStatus;
    }

    public LoginStatus setBlackBoxStatus(String blackBoxStatus) {
        this.blackBoxStatus = blackBoxStatus;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public LoginStatus setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getT_link() {
        return t_link;
    }

    public LoginStatus setT_link(String t_link) {
        this.t_link = t_link;
        return this;
    }
}

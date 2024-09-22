package com.winner.bean;

public class Domain {


    private Integer id;

    private String domain;
    private String addr;
    private String type;
    private String name;

    public Integer getId() {
        return id;
    }

    public Domain setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public Domain setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getAddr() {
        return addr;
    }

    public Domain setAddr(String addr) {
        this.addr = addr;
        return this;
    }

    public String getType() {
        return type;
    }

    public Domain setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public Domain setName(String name) {
        this.name = name;
        return this;
    }
}

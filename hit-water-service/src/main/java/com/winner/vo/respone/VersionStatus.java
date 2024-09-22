package com.winner.vo.respone;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "serverrequest")
public class VersionStatus {

    private String ver;
    private String code;
    private String site;


    @XmlElement(name = "ver")
    public String getVer() {
        return ver;
    }

    public VersionStatus setVer(String ver) {
        this.ver = ver;
        return this;
    }
    @XmlElement
    public String getCode() {
        return code;
    }

    public VersionStatus setCode(String code) {
        this.code = code;
        return this;
    }
    @XmlElement
    public String getSite() {
        return site;
    }

    public VersionStatus setSite(String site) {
        this.site = site;
        return this;
    }
}

package com.hch.hooney.companyscheduler.DAO;

/**
 * Created by hooney on 2018. 2. 2..
 */

public class User {
    private String eid;
    private String eName;
    private String ePhone;
    private String eTeam;
    private int ePoint;

    public User() {
        this.eid = "";
        this.eName = "";
        this.ePhone = "";
        this.eTeam = "";
        this.ePoint = 0;
    }

    public User(String eid, String eName, String ePhone,
                String eTeam, int ePoint) {
        this.eid = eid;
        this.eName = eName;
        this.ePhone = ePhone;
        this.eTeam = eTeam;
        this.ePoint = ePoint;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getePhone() {
        return ePhone;
    }

    public void setePhone(String ePhone) {
        this.ePhone = ePhone;
    }

    public String geteTeam() {
        return eTeam;
    }

    public void seteTeam(String eTeam) {
        this.eTeam = eTeam;
    }

    public int getePoint() {
        return ePoint;
    }

    public void setePoint(int ePoint) {
        this.ePoint = ePoint;
    }
}

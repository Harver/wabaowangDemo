package model;

public class Address {
    private int ad_id;//用户地址编号
    private String ad_name;//收货人
    private String ad_address;//用户配送地址
    private String ad_tel;//用户配送电话
    private int ad_u_id;//用户表外键

    public Address() { }
    public Address(int ad_id, String ad_name, String ad_address, String ad_tel, int ad_u_id) {
        this.ad_id = ad_id;
        this.ad_name = ad_name;
        this.ad_address = ad_address;
        this.ad_tel = ad_tel;
        this.ad_u_id = ad_u_id;
    }
    public Address(String ad_name, String ad_address, String ad_tel, int ad_u_id) {
        this.ad_name = ad_name;
        this.ad_address = ad_address;
        this.ad_tel = ad_tel;
        this.ad_u_id = ad_u_id;
    }
    public int getAd_id() {
        return ad_id;
    }

    public void setAd_id(int ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_name() {
        return ad_name;
    }

    public void setAd_name(String ad_name) {
        this.ad_name = ad_name;
    }

    public String getAd_address() {
        return ad_address;
    }

    public void setAd_address(String ad_address) {
        this.ad_address = ad_address;
    }

    public String getAd_tel() {
        return ad_tel;
    }

    public void setAd_tel(String ad_tel) {
        this.ad_tel = ad_tel;
    }

    public int getAd_u_id() {
        return ad_u_id;
    }

    public void setAd_u_id(int ad_u_id) {
        this.ad_u_id = ad_u_id;
    }
}

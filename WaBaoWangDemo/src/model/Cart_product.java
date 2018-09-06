package model;

import java.math.BigDecimal;

public class Cart_product {

    //购物车编号,商品信息,商品单价,购物数量

    private int c_id;//购物车编号
    private int c_num;//商品数量
    private String state;//购物状态
    private BigDecimal p_price;//商品单价
    private String p_info;//商品简介
    private String p_photo;//商品图片

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getC_num() {
        return c_num;
    }

    public void setC_num(int c_num) {
        this.c_num = c_num;
    }

    public BigDecimal getP_price() {
        return p_price;
    }

    public void setP_price(BigDecimal p_price) {
        this.p_price = p_price;
    }

    public String getP_info() {
        return p_info;
    }

    public void setP_info(String p_info) {
        this.p_info = p_info;
    }

    public String getP_photo() {
        return p_photo;
    }

    public void setP_photo(String p_photo) {
        this.p_photo = p_photo;
    }

    public Cart_product(int c_id, int c_num,String state,BigDecimal p_price, String p_info, String p_photo) {
        this.c_id = c_id;
        this.c_num = c_num;
        this.state=state;
        this.p_price = p_price;
        this.p_info = p_info;
        this.p_photo = p_photo;
    }

    public Cart_product() {
    }
}

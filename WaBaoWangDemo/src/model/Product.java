package model;

import java.math.BigDecimal;

public class Product {

    private int p_id;//商品编号
    private String p_name;//商品名称
    private BigDecimal p_price;//商品价格
    private String p_info;//商品简介
    private String p_photo;//商品图片
    private String p_state;//商品状态
    private int p_t_id;//类型表外键

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
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

    public String getP_state() {
        return p_state;
    }

    public void setP_state(String p_state) {
        this.p_state = p_state;
    }

    public int getP_t_id() {
        return p_t_id;
    }

    public void setP_t_id(int p_t_id) {
        this.p_t_id = p_t_id;
    }

    public Product() { }
    public Product(int p_id, String p_name, BigDecimal p_price, String p_info, String p_photo, String p_state) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_info = p_info;
        this.p_photo = p_photo;
        this.p_state = p_state;
    }

    @Override
    public String toString() {
        return "Product{" +
                "p_id=" + p_id +
                ", p_name='" + p_name + '\'' +
                ", p_price=" + p_price +
                ", p_info='" + p_info + '\'' +
                ", p_photo='" + p_photo + '\'' +
                ", p_state='" + p_state + '\'' +
                ", p_t_id=" + p_t_id +
                '}';
    }
}

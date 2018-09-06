package model_admin;

import java.math.BigDecimal;

public class product_admin {

    private int p_id;//商品编号
   // private String p_name;
    private BigDecimal p_price;//商品价格
    private String p_info;//商品简介
    private String p_photo;//商品图片
    private int p_state;//商品状态
    private int p_t_id;//类型表外键
    private String t_name;//类型表名称


    public product_admin() { }
    public product_admin(int p_id, BigDecimal p_price, String p_info, String p_photo, int p_state, int p_t_id, String t_name) {
        this.p_id = p_id;
        this.p_price = p_price;
        this.p_info = p_info;
        this.p_photo = p_photo;
        this.p_state = p_state;
        this.p_t_id = p_t_id;
        this.t_name = t_name;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
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

    public int getP_state() {
        return p_state;
    }

    public void setP_state(int p_state) {
        this.p_state = p_state;
    }

    public int getP_t_id() {
        return p_t_id;
    }

    public void setP_t_id(int p_t_id) {
        this.p_t_id = p_t_id;
    }

    public String getT_nam() {
        return t_name;
    }

    public void setT_nam(String t_nam) {
        this.t_name = t_nam;
    }
}

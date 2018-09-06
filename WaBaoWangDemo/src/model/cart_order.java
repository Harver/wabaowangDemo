package model;

import java.math.BigDecimal;

public class cart_order {
    //购物车编号,商品图片,商品信息，商品单价，商品数量，金额
    private int c_id;
    private String p_photo;
    private String p_info;
    private BigDecimal p_price;
    private int c_num;
    private BigDecimal c_sum;

    public cart_order() { }

    public cart_order(int c_id, String p_photo, String p_info, BigDecimal p_price, int c_num, BigDecimal c_sum) {
        this.c_id = c_id;
        this.p_photo = p_photo;
        this.p_info = p_info;
        this.p_price = p_price;
        this.c_num = c_num;
        this.c_sum = c_sum;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getP_photo() {
        return p_photo;
    }

    public void setP_photo(String p_photo) {
        this.p_photo = p_photo;
    }

    public String getP_info() {
        return p_info;
    }

    public void setP_info(String p_info) {
        this.p_info = p_info;
    }

    public BigDecimal getP_price() {
        return p_price;
    }

    public void setP_price(BigDecimal p_price) {
        this.p_price = p_price;
    }

    public int getC_num() {
        return c_num;
    }

    public void setC_num(int c_num) {
        this.c_num = c_num;
    }

    public BigDecimal getC_sum() {
        return c_sum;
    }

    public void setC_sum(BigDecimal c_sum) {
        this.c_sum = c_sum;
    }

    @Override
    public String toString() {
        return "cart_order{" +
                "c_id=" + c_id +
                ", p_photo='" + p_photo + '\'' +
                ", p_info='" + p_info + '\'' +
                ", p_price=" + p_price +
                ", c_num=" + c_num +
                ", c_sum=" + c_sum +
                '}';
    }
}

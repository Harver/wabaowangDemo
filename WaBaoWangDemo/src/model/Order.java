package model;

import java.math.BigDecimal;

public class Order {
    private int o_id;//订单编号
    private int o_state;//订单状态
    private BigDecimal o_money;//订单总金额
    private int o_ad_id;//用户地址表外键
    private int o_c_id;//购物车外键

    public Order() { }
    public Order(int o_id, int o_state, BigDecimal o_money, int o_ad_id, int o_c_id) {
        this.o_id = o_id;
        this.o_state = o_state;
        this.o_money = o_money;
        this.o_ad_id = o_ad_id;
        this.o_c_id = o_c_id;
    }
    public Order( int o_state, BigDecimal o_money, int o_ad_id, int o_c_id) {
        this.o_id = o_id;
        this.o_state = o_state;
        this.o_money = o_money;
        this.o_ad_id = o_ad_id;
        this.o_c_id = o_c_id;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getO_state() {
        return o_state;
    }

    public void setO_state(int o_state) {
        this.o_state = o_state;
    }

    public BigDecimal getO_money() {
        return o_money;
    }

    public void setO_money(BigDecimal o_money) {
        this.o_money = o_money;
    }

    public int getO_ad_id() {
        return o_ad_id;
    }

    public void setO_ad_id(int o_ad_id) {
        this.o_ad_id = o_ad_id;
    }

    public int getO_c_id() {
        return o_c_id;
    }

    public void setO_c_id(int o_c_id) {
        this.o_c_id = o_c_id;
    }
}

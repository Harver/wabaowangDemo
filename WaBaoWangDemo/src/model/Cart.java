package model;

import java.math.BigDecimal;

public class Cart {
    private int c_id;//购物车编号
    private String c_state;//购物车状态
    private int c_num;//数量
    private BigDecimal c_sum;//金额
    private int c_u_id;//用户表外键
    private int c_p_id;//商品表外键

    public Cart() { }
    public Cart(int c_id, String c_state, int c_num, BigDecimal c_sum, int c_u_id, int c_p_id) {
        this.c_id = c_id;
        this.c_state = c_state;
        this.c_num = c_num;
        this.c_sum = c_sum;
        this.c_u_id = c_u_id;
        this.c_p_id = c_p_id;
    }
    public Cart(String c_state, int c_num, BigDecimal c_sum, int c_u_id, int c_p_id) {
        this.c_state = c_state;
        this.c_num = c_num;
        this.c_sum = c_sum;
        this.c_u_id = c_u_id;
        this.c_p_id = c_p_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_state() {
        return c_state;
    }

    public void setC_state(String c_state) {
        this.c_state = c_state;
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

    public int getC_u_id() {
        return c_u_id;
    }

    public void setC_u_id(int c_u_id) {
        this.c_u_id = c_u_id;
    }

    public int getC_p_id() {
        return c_p_id;
    }

    public void setC_p_id(int c_p_id) {
        this.c_p_id = c_p_id;
    }
}

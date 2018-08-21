package com.example.adanvace.recycler.bean;

/**
 * @author alan
 *         Date  2018/6/12.
 *         Function :
 *         Issue :
 */

public class DataBean {

    private Object data;
    private int type;

    public DataBean(Object data, int type) {
        this.data = data;
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

package com.bean;

import java.io.Serializable;

public class Product implements Serializable {  
    
    private int pid;
    private String pname;
    private float price;


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public Product() {}

    public Product(int pid, String pname, float price) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{id=" + pid + ", name='" + pname + "', price=" + price + "}";
    }
}

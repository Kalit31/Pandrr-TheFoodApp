package com.example.foodapp.models;

public class Item_Looters
{
    String name,price,type,code;

    public Item_Looters() {
    }

    public Item_Looters(String name, String price, String type,String code) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

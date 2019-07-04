package com.example.foodapp.models;

public class Item_Looters
{
    String name,price,counter,code;


    boolean type;

    public Item_Looters() {
    }

    public Item_Looters(String name, String price, boolean type,String code, String counter) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.code = code;
        this.counter = counter;
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

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }


}

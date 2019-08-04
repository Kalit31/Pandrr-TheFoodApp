package com.example.foodapp.models;

public class Item
{
    private String name,price,counter,code;
    private int itemCount = 1;
    private int viewType;
    private boolean type;

    public Item() {
    }

    public Item(String name, String price, boolean type, String counter, int itemCount, String code, int viewType) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.counter = counter;
        this.itemCount = itemCount;
        this.code = code;
        this.viewType = viewType;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}

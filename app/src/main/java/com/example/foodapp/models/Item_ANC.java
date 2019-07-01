package com.example.foodapp.models;

public class Item_ANC
{
    String name,price,counter;
      boolean type;

    public Item_ANC() {
    }

    public Item_ANC(String name, String price, boolean type, String counter) {
        this.name = name;
        this.price = price;
        this.type = type;
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

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

}

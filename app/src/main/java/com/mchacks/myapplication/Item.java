package com.mchacks.myapplication;

import java.util.ArrayList;

/*
* Basic Item class
* */
public class Item {
    private String name;
    private ArrayList<String> tags;
    private  double price;

    public Item(String name, ArrayList<String> tags, double price)
    {
        this.setName(name);
        this.setTags(tags);
        this.setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

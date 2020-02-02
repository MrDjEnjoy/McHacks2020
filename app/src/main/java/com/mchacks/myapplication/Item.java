package com.mchacks.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

/*
* Basic Item class
* */
public class Item implements Serializable {
    private String name;
    private ArrayList<String> tags;
    private  String price;
    private String id;
    private String creator;

    public Item(String name, ArrayList<String> tags, String price, String id, String creator)
    {
        this.setName(name);
        this.setTags(tags);
        this.setPrice(price);
        this.id = id;
        this.creator = creator;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}

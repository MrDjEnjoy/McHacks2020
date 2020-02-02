package com.mchacks.myapplication;

import java.util.ArrayList;

/*
* Merchant Class that stores the information of a merchant profile
* */

public class Merchant {
    private String name;
    private String address;
    private String description;
    private ArrayList<Item> catalog;
    private ArrayList<String> sustainibility;

    public Merchant(String name, String address)
    {
        this.name = name;
        this.address = address;
        this.catalog = new ArrayList<Item>();
        this.sustainibility = new ArrayList<String>();
        this.description = "";
    }

    public Merchant(String name, String address, String description, ArrayList<Item> catalog, ArrayList<String> sustainibility)
    {
        this.name = name;
        this.address = address;
        this.description = description;
        this.catalog = catalog;
        this.sustainibility = sustainibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Item> getCatalog() {
        return catalog;
    }

    public void setCatalog(ArrayList<Item> catalog) {
        this.catalog = catalog;
    }

    public void addItem(Item i)
    {
        this.catalog.add(i);
    }

    public void removeItem(Item i)
    {
        this.catalog.remove(i);
    }
}

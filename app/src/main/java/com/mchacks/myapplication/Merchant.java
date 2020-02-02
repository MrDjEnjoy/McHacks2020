package com.mchacks.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

/*
* Merchant Class that stores the information of a merchant profile
* */

public class Merchant implements Serializable {
    private String name;
    private String address;
    private String description;
    private ArrayList<Item> catalog;
    private ArrayList<String> sustainibility;
    private String id;
    private String email;
    private String plusCode;

    public Merchant(String name, String address, String id, String email)
    {
        this.name = name;
        this.address = address;
        this.catalog = new ArrayList<>();
        this.sustainibility = new ArrayList<>();
        this.description = "";
        this.setId(id);
        this.setEmail(email);
    }

    public Merchant(String name, String address, String id, String email, String description, ArrayList<Item> catalog, ArrayList<String> sustainibility)
    {
        this.name = name;
        this.address = address;
        this.description = description;
        this.catalog = catalog;
        this.setSustainibility(sustainibility);
        this.setId(id);
        this.setEmail(email);
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

    public ArrayList<String> getSustainibility() {
        return sustainibility;
    }

    public void setSustainibility(ArrayList<String> sustainibility) {
        this.sustainibility = sustainibility;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

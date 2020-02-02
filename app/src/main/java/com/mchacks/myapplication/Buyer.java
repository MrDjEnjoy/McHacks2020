package com.mchacks.myapplication;

import java.util.ArrayList;

/*
* Basic Buyer class to keep track of grocery list and merchant selected for grocery shopping
*/

public class Buyer {
    private ArrayList<String> groceryList; //will be a list of tags to find the items
    private ArrayList<Merchant> merchantList;

    private static Buyer instance;
    private Buyer()
    {
        this.groceryList = new ArrayList<String>();
        this.merchantList = new ArrayList<Merchant>();
    }

    public static Buyer getInstance() {
        if (instance == null)
        {
            instance = new Buyer();
        }
        return instance;
    }

    public void addToGrocery(String food)
    {
        this.groceryList.add(food);
    }

    public void removeFromGrocery(String food)
    {
        this.groceryList.remove(food);
    }

    public void addMerchant(Merchant m)
    {
        this.merchantList.add(m);
    }

    public void removeMerchant(Merchant m)
    {
        this.merchantList.remove(m);
    }
}

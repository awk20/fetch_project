package com.receipts.fetchproject.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

// Embeddable allows this class to not require a primary key
@Embeddable
public class Item {
    private String shortDescription;

    private String price;

    public void setShortDescription(String shortDescription){
        this.shortDescription = shortDescription;
    }

    public String getShortDescription(){
        return this.shortDescription;
    }

    public void setprice(String price){
        this.price = price;
    }

    public String getPrice(){
        return this.price;
    }
}

package com.receipts.fetchproject.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Receipt {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String retailer;

    private String purchaseDate;

    private String purchaseTime;

    // CascadeType -> all operations on the parent will happen to the child
    // orphanRemoval -> if Item is removed from items then its removed from database
    // JoinColumn -> gives foreign key column in Item table referencing parent table
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "receipt_id")
    @ElementCollection
    @CollectionTable(name = "", joinColumns = @JoinColumn(name = "receipt_id"))
    private List<Item> items;

    private String total;

    public Receipt(){
        this.id = UUID.randomUUID().toString();
    }

    public Receipt(String retailer, String purchaseDate, String purchaseTime, List<Item> items){
        this.id = UUID.randomUUID().toString();
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.items = items;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setRetailer(String retailer){
        this.retailer = retailer;
    }

    public String getRetailer(){
        return this.retailer;
    }

    public void setPurchaseDate(String purchaseDate){
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseDate(){
        return this.purchaseDate;
    }

    public void setPurchaseTime(String purchaseTime){
        this.purchaseTime = purchaseTime;
    }

    public String getPurchaseTime(){
        return this.purchaseTime;
    }

    public void setItems(List<Item> items){
        this.items = items;
    }

    public List<Item> getItems(){
        return this.items;
    }

    public void setTotal(String total){
        this.total = total;
    }

    public String getTotal(){
        return this.total;
    }
}

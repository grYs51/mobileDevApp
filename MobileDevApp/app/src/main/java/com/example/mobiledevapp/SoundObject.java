package com.example.mobiledevapp;

public class SoundObject {

    private String ItemName;
    private Integer ItemID;

    public SoundObject(String itemName, Integer itemID) {
        this.ItemID = itemID;
        this.ItemName = itemName;
    }

    public String getItemName() {

        return ItemName;
    }

    public Integer getItemID() {

        return ItemID;
    }
}

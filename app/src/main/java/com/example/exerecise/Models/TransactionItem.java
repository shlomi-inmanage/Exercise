package com.example.exerecise.Models;


import java.io.Serializable;

public class TransactionItem  extends TransactionListItem implements Serializable  {

    private String id;
    private String title;
    private int price;
    private String description;
    private String image;
    private int optionsToShow;
    private String lat;
    private String lon;
    private String website;
    private String phone;

    public TransactionItem(String id, String title, int price, String description, String image, int optionsToShow, String lat, String lon, String website, String phone) {
        super(id,title,price,image,0);
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.image = image;
        this.optionsToShow = optionsToShow;
        this.website = website;
        this.phone = phone;
        this.lat = lat;
        this.lon = lon;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getOptionsToShow() {
        return optionsToShow;
    }

    public void setOptionsToShow(int optionsToShow) {
        this.optionsToShow = optionsToShow;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

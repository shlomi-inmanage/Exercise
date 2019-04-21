package com.example.exerecise.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class TransactionItem {

    private String id;
    private String title;
    private int price;
    private String description;
    private String image;
    private int optionsToShow;
    private JSONObject gps;
    private String lat;
    private String lon;
    private String website;
    private String phone;

    public TransactionItem(String id, String title, int price, String description, String image, int optionsToShow, JSONObject gps, String website, String phone) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.image = image;
        this.optionsToShow = optionsToShow;
        this.gps = gps;
        this.website = website;
        this.phone = phone;
        setLat();
        setLon();
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

    public JSONObject getGps() {
        return gps;
    }

    public void setGps(JSONObject gps) {
        this.gps = gps;
    }

    public String getLat() {
        return lat;
    }

    public void setLat() {
        try {
            this.lat = gps.getString("lat");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getLon() {
        return lon;
    }

    public void setLon() {
        try {
            this.lon = gps.getString("lon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

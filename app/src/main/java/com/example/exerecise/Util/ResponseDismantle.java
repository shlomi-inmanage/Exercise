package com.example.exerecise.Util;

import com.example.exerecise.Models.TransactionItem;
import com.example.exerecise.Models.TransactionListItem;

import org.json.JSONException;
import org.json.JSONObject;

public class ResponseDismantle {

    String id;
    String title;
    int price;
    String description;
    String image;
    int optionsToShow;
    JSONObject gps;
    String website;
    String phone;
    int order_num;

    public TransactionItem getItem(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getString("id");
        title = jsonObject.getString("title");
        price = jsonObject.getInt("price");
        description = jsonObject.getString("description");
        image = jsonObject.getString("image");
        optionsToShow = jsonObject.getInt("optionsToShow");
        gps = jsonObject.getJSONObject("gps");
        website = jsonObject.getString("website");
        phone = jsonObject.getString("phone");

        TransactionItem response = new TransactionItem(id,title,price,description,image,optionsToShow,
                gps,website,phone);
        return response;
    }

    public String getBanner(JSONObject jsonObject) throws JSONException{
        String banner = jsonObject.getString("banner_url");
        return banner;
    }

    public TransactionListItem getListItem(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getString("id");
        title = jsonObject.getString("title");
        price = jsonObject.getInt("price");
        image = jsonObject.getString("image");
        order_num = jsonObject.getInt("order_num");


        TransactionListItem response = new TransactionListItem(id,title,price,image, order_num);
        return response;
    }
}

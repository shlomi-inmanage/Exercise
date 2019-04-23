package com.example.exerecise.Util.Server_Response;

import com.example.exerecise.Models.TransactionItem;
import com.example.exerecise.Models.TransactionListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ResponseDismantle {

    private String id;
    private String title;
    private int price;
    private String description;
    private String image;
    private int optionsToShow;
    private JSONObject gps;
    private String website;
    private String phone;
    private int order_num;
    private String lat;
    private String lon;

    public TransactionItem getItem(JSONObject jsonObject) throws JSONException {
        JSONObject data = jsonObject.getJSONObject("data");
        JSONObject object = data.getJSONObject("itemArr");
        id = object.getString("id");
        title = object.getString("title");
        price = object.getInt("price");
        description = object.getString("description");
        image = object.getString("image");
        optionsToShow = object.getInt("optionsToShow");
        gps = object.getJSONObject("gps");
        website = object.getString("website");
        phone = object.getString("phone");
        lat = gps.getString("lat");
        lon = gps.getString("lon");
        TransactionItem response = new TransactionItem(id,title,price,description,image,optionsToShow,
                lat,lon,website,phone);
        return response;
    }

    public String getBanner(JSONObject jsonObject) throws JSONException{
        JSONObject data = jsonObject.getJSONObject("data");
        String banner = data.getString("banner_url");
        return banner;
    }

    public ArrayList<TransactionListItem> getListItem(JSONObject jsonObject) throws JSONException {
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray array = data.getJSONArray("itemsArr");
        ArrayList<TransactionListItem> transactionListItemsList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject item = array.getJSONObject(i);
            id = item.getString("id");
            title = item.getString("title");
            price = item.getInt("price");
            image = item.getString("image");
            order_num = item.getInt("order_num");
            TransactionListItem response = new TransactionListItem(id,title,price,image, order_num);
            transactionListItemsList.add(response);
        }
        return transactionListItemsList;
    }
}

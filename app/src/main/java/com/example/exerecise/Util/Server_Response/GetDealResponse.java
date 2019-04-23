package com.example.exerecise.Util.Server_Response;

import com.example.exerecise.Models.TransactionItem;

import org.json.JSONException;
import org.json.JSONObject;

public class GetDealResponse extends BaseResponse {

    private JSONObject response;
    private TransactionItem transactionItem;
    private String id;
    private String title;
    private int price;
    private String description;
    private String image;
    private int optionsToShow;
    private JSONObject gps;
    private String website;
    private String phone;
    private String lat;
    private String lon;

    public GetDealResponse(JSONObject response) throws JSONException {
        super(response);
        this.response = getParsed();
        setTransactionItem();

    }

    public TransactionItem getTransactionItem() {
        return transactionItem;
    }

    public void setTransactionItem() throws JSONException {
        JSONObject object = response.getJSONObject("itemArr");
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
        TransactionItem transactionItem = new TransactionItem(id,title,price,description,image,optionsToShow,
                lat,lon,website,phone);
        this.transactionItem = transactionItem;
    }
}

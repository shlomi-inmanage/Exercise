package com.example.exerecise.Util.Server_Response;

import com.example.exerecise.Models.TransactionListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetDealsResponse extends BaseResponse {

    private JSONObject response;
    private ArrayList<TransactionListItem> transactionListItemsList;
    private String banner;
    private String id;
    private String title;
    private int price;
    private String image;
    private int order_num;


    public GetDealsResponse(JSONObject response) throws JSONException {
        super(response);
        this.response = getParsed();
        setTransactionListItemsList();
        setBanner();
    }

    public ArrayList<TransactionListItem> getTransactionListItemsList() {
        return transactionListItemsList;
    }

    public void setTransactionListItemsList() throws JSONException {
        JSONArray array = response.getJSONArray("itemsArr");
        transactionListItemsList = new ArrayList<>();
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
        this.transactionListItemsList = transactionListItemsList;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner() throws JSONException {
        String banner = response.getString("banner_url");
        this.banner = banner;
    }
}

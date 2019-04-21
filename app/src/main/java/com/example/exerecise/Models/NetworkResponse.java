package com.example.exerecise.Models;

import com.example.exerecise.Util.ResponseDismantle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NetworkResponse {

    private JSONObject response;
    private TransactionItem transactionItem;
    private ArrayList<TransactionListItem> transactionListItemsList;
    private String banner;

    public NetworkResponse(JSONObject response) {
        this.response = response;
        setResponse();
    }

    public JSONObject getResponse() {
        return response;
    }

    public void setResponse() {
        try {
            JSONObject data = response.getJSONObject("data");
            ResponseDismantle responseDismantle = new ResponseDismantle();
            try {
                JSONObject object = data.getJSONObject("itemArr");
                this.transactionItem = responseDismantle.getItem(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                JSONArray object = data.getJSONArray("itemsArr");
                transactionListItemsList = new ArrayList<>();
                for (int i = 0; i < object.length(); i++) {
                  this.transactionListItemsList.add(responseDismantle.getListItem(object.getJSONObject(i)));
                }
                this.banner = responseDismantle.getBanner(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

    public TransactionItem getTransactionItem() {
        return transactionItem;
    }

    public void setTransactionItem(TransactionItem transactionItem) {
        this.transactionItem = transactionItem;
    }

    public ArrayList<TransactionListItem> getTransactionListItemsList() {
        return transactionListItemsList;
    }

    public void setTransactionListItemsList(ArrayList<TransactionListItem> transactionListItemsList) {
        this.transactionListItemsList = transactionListItemsList;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}

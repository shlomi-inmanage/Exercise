package com.example.exerecise.Models;

import com.example.exerecise.Util.ResponseDismantle;
import com.example.exerecise.Util.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NetworkResponse {

    private JSONObject response;
    private VolleyCallback volleyCallback;
    private TransactionItem transactionItem;
    private ArrayList<TransactionListItem> transactionListItemsList;
    private String banner;
    private String url;

    public NetworkResponse(JSONObject response, String url, VolleyCallback volleyCallback) throws JSONException {
        this.response = response;
        this.url = url;
        this.volleyCallback = volleyCallback;
        setResponse();
    }

    public JSONObject getResponse() {
        return response;
    }

    public void setResponse() throws JSONException {
        ResponseDismantle responseDismantle = new ResponseDismantle();
        switch (url){
            case Constants.URL_GET_ARRAY_OF_DEALS:
                this.transactionListItemsList = responseDismantle.getListItem(response);
                this.banner = responseDismantle.getBanner(response);
                break;
            case Constants.URL_GET_SINGLE_DEAL:
                this.transactionItem = responseDismantle.getItem(response);
                break;
        }
        volleyCallback.onSuccess(this);

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

package com.example.exerecise.Models.Server_Request_Parameters;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.exerecise.Util.Interfaces.BaseServerResponseInterface;
import com.example.exerecise.Util.Server_Response.GetDealResponse;
import com.example.exerecise.Util.Server_Response.GetDealsResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerRequestHandler {

    private Context mContext;
    private BaseServerResponseInterface baseServerResponseInterface;

    public ServerRequestHandler(Context mContext) {
        this.mContext = mContext;
        this.baseServerResponseInterface = (BaseServerResponseInterface)mContext;
    }

    public void getDealsRequest(){
        GetDealsRequest getDealsRequest = new GetDealsRequest(null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    GetDealsResponse getDealsResponse = new GetDealsResponse(response);
                    baseServerResponseInterface.getServerResponseDeals(getDealsResponse.getTransactionListItemsList(), getDealsResponse.getBanner());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        },mContext);
    }

    public void getDealRequest(String id){
        GetDealRequest getDealsRequest = new GetDealRequest(id,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    GetDealResponse getDealResponse = new GetDealResponse(response);
                    baseServerResponseInterface.getServerResponseDeal(getDealResponse.getTransactionItem());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        },mContext);
    }
}

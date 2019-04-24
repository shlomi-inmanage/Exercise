package com.example.exerecise.Util.Server_Request;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.exerecise.Util.Server_Request.Requests.GetDealRequest;
import com.example.exerecise.Util.Server_Request.Requests.GetDealsRequest;
import com.example.exerecise.Util.Interfaces.LoaderManager;
import com.example.exerecise.Util.Interfaces.ServerResponseGetDealDetails;
import com.example.exerecise.Util.Interfaces.ServerResponseGetDeals;
import com.example.exerecise.Util.Server_Response.GetDealResponse;
import com.example.exerecise.Util.Server_Response.GetDealsResponse;

import org.json.JSONObject;

public class ServerRequestHandler {

    private Context mContext;
    private LoaderManager loaderManager;
    private boolean mShowLoader;

    public ServerRequestHandler(Context mContext, boolean showLoader) {
        this.mContext = mContext;
        this.loaderManager = (LoaderManager)mContext;
        this.mShowLoader = showLoader;
        if(mShowLoader){
            loaderManager.showLoader();
        }

    }

    public void sendDealsRequest(ServerResponseGetDeals serverResponseGetDeals){
        GetDealsRequest getDealsRequest = createGetDealsRequest(serverResponseGetDeals);
        RequestQueue queue = VolleyController.getInstance(mContext).getRequestQueue();
        queue.add(getDealsRequest);
    }



    public void sendDealRequest(ServerResponseGetDealDetails serverResponseGetDealDetails, String id){
        GetDealRequest getDealRequest = createGetDealRequest(id, serverResponseGetDealDetails);
        RequestQueue queue = VolleyController.getInstance(mContext).getRequestQueue();
        queue.add(getDealRequest);
    }

    private GetDealRequest createGetDealRequest(String id, final ServerResponseGetDealDetails serverResponseGetDealDetails) {
        return new GetDealRequest(id,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GetDealResponse getDealResponse = new GetDealResponse(response);
                if(serverResponseGetDealDetails!=null){
                    serverResponseGetDealDetails.getServerResponseDealDetails(getDealResponse.getTransactionItem());
                    loaderManager.hideLoader();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    if(serverResponseGetDealDetails!=null){
                        serverResponseGetDealDetails.onError(error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private GetDealsRequest createGetDealsRequest(final ServerResponseGetDeals serverResponseGetDeals) {
        return new GetDealsRequest(null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GetDealsResponse getDealsResponse = new GetDealsResponse(response);
                if(serverResponseGetDeals != null){
                    serverResponseGetDeals.getServerResponseDeals(getDealsResponse.getTransactionListItemsList(), getDealsResponse.getBanner());
                    loaderManager.hideLoader();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(serverResponseGetDeals != null){
                    serverResponseGetDeals.onError(error.getMessage());
                }

            }
        });
    }

}

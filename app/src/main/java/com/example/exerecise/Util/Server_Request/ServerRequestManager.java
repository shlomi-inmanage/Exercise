package com.example.exerecise.Util.Server_Request;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.exerecise.Util.LoaderObservable;
import com.example.exerecise.Util.Server_Request.Requests.BaseRequest;
import com.example.exerecise.Util.Server_Request.Requests.GetDealRequest;
import com.example.exerecise.Util.Server_Request.Requests.GetDealsRequest;
import com.example.exerecise.Util.Interfaces.ServerResponseGetDealDetails;
import com.example.exerecise.Util.Interfaces.ServerResponseGetDeals;
import com.example.exerecise.Util.Server_Response.GetDealResponse;
import com.example.exerecise.Util.Server_Response.GetDealsResponse;

import org.json.JSONObject;

public class ServerRequestManager {

    private static ServerRequestManager INSTANCE = null;
    private LoaderObservable loaderObservable;
    private Context mContext;

    public static ServerRequestManager getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = new ServerRequestManager(context);
        }
        return INSTANCE;
    }

    private ServerRequestManager(Context mContext) {
        this.mContext = mContext;
        loaderObservable = LoaderObservable.getInstance();
    }

    public void sendDealsRequest(ServerResponseGetDeals serverResponseGetDeals){
        GetDealsRequest getDealsRequest = createGetDealsRequest(serverResponseGetDeals);
        sendRequest(getDealsRequest);
    }



    public void sendDealRequest(ServerResponseGetDealDetails serverResponseGetDealDetails, String id){
        GetDealRequest getDealRequest = createGetDealRequest(id, serverResponseGetDealDetails);
        sendRequest(getDealRequest);
    }

    private void sendRequest(BaseRequest baseRequeste) {
        loaderObservable.setShowLoader(true);
        RequestQueue queue = VolleyController.getInstance(mContext).getRequestQueue();
        queue.add(baseRequeste);
    }

    private GetDealRequest createGetDealRequest(String id, final ServerResponseGetDealDetails serverResponseGetDealDetails) {
        return new GetDealRequest(id,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GetDealResponse getDealResponse = new GetDealResponse(response);
                if(serverResponseGetDealDetails!=null){
                    serverResponseGetDealDetails.getServerResponseDealDetails(getDealResponse.getTransactionItem());
                    loaderObservable.setShowLoader(false);
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
                    loaderObservable.setShowLoader(false);
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

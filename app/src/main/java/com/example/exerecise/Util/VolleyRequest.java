package com.example.exerecise.Util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.exerecise.MainActivity;
import com.example.exerecise.Models.NetworkResponse;
import com.example.exerecise.Models.Server_Request.CustomJSONObjectRequest;
import com.example.exerecise.Models.Server_Request.ServerRequestParameters;
import com.example.exerecise.Models.Server_Request.VolleyController;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyRequest {

    private Context mContext;
    private Activity mActivity;
    private VolleyCallback mVolleyCallback;
    private ServerRequestParameters serverRequestParameters;
    private LoaderManager loaderManager;
    private static final String  TAG = "VolleyRequest";


    public VolleyRequest(Context mContext, ServerRequestParameters serverRequestParameters, final VolleyCallback callback) {
        this.mContext = mContext;
        this.serverRequestParameters = serverRequestParameters;
        this.mVolleyCallback = callback;
        loaderManager = (LoaderManager)mContext;
        getRequest();
    }

    public void getRequest(){
        if(serverRequestParameters.isShow_loader()){
            loaderManager.showLoader();
        }
        RequestQueue queue = VolleyController.getInstance(mContext).getRequestQueue();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (serverRequestParameters.getMethod(), serverRequestParameters.getBuilder().getFinalUrl(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            NetworkResponse networkResponse = new NetworkResponse(response,serverRequestParameters.getBuilder().getBaseUrl(), mVolleyCallback);
                            loaderManager.hideLoader();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: "+error.getMessage());
                        // TODO: Handle error
                    }
                });
        queue.add(jsonObjectRequest);
    }

}

package com.example.exerecise.Util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class VolleyRequest {

    private Context mContext;
    private String url;
    private getResponse mCallback;
    public static final String  TAG = "VolleyRequest";


    public VolleyRequest(Context mContext, String url) {
        this.mContext = mContext;
        this.url = url;
        mCallback = (getResponse)mContext;
        getRequest();
    }

    public void getRequest(){
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        mCallback.getJSONObject(response);
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

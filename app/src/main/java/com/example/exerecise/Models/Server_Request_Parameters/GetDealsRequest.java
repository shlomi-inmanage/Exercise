package com.example.exerecise.Models.Server_Request_Parameters;


import android.content.Context;
import android.support.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.example.exerecise.Models.Constants;
import com.example.exerecise.Util.Server_Request.VolleyController;

import org.json.JSONObject;

public class GetDealsRequest extends BaseRequest {

    private Context context;

    public GetDealsRequest(@Nullable JSONObject jsonRequest, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener, Context context) {
        super(Method.GET, Constants.URL_GET_ARRAY_OF_DEALS, jsonRequest, listener, errorListener);
        RequestQueue queue = VolleyController.getInstance(context).getRequestQueue();
        this.context = context;
        queue.add(this);
    }

}

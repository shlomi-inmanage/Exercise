package com.example.exerecise.Util.Server_Request.Requests;


import android.support.annotation.Nullable;

import com.android.volley.Response;
import com.example.exerecise.Models.Constants;
import com.example.exerecise.Util.Server_Response.BaseResponse;
import com.example.exerecise.Util.Server_Response.GetDealsResponse;

import org.json.JSONObject;

public class GetDealsRequest extends BaseRequest {

    public GetDealsRequest(@Nullable JSONObject jsonRequest, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
        super(Method.GET, Constants.URL_GET_ARRAY_OF_DEALS, jsonRequest, listener, errorListener);
    }

    @Override
    protected BaseResponse createResponse(JSONObject jsonObject) {
        return new GetDealsResponse(jsonObject);
    }
}

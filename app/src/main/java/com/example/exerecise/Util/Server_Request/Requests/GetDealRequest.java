package com.example.exerecise.Util.Server_Request.Requests;

import android.support.annotation.Nullable;

import com.android.volley.Response;
import com.example.exerecise.Util.Server_Response.BaseResponse;

import org.json.JSONObject;

public class GetDealRequest extends BaseRequest {


    private static final String ID ="[id]";
    private static final String URL = "https://androidtest.inmanage.com/api/1.0/android/getDeal_"+ID+ ".txt";
    public GetDealRequest(String id, @Nullable JSONObject jsonRequest, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener) {
        super(Method.GET,  URL.replace(ID,id), jsonRequest, listener, errorListener);
    }

    @Override
    protected BaseResponse createResponse(JSONObject jsonObject) {
        return null;
    }
}

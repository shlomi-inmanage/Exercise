package com.example.exerecise.Models.Server_Request_Parameters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.exerecise.Models.Constants;
import com.example.exerecise.Models.Server_Request_Parameters.BaseRequest;
import com.example.exerecise.Util.Server_Request.VolleyController;

import org.json.JSONObject;

public class GetDealRequest extends BaseRequest {

    private Context context;
    private String url;
    private Response.Listener<JSONObject> mlistener;
    private Response.ErrorListener merrorListener;

    public GetDealRequest(String id, @Nullable JSONObject jsonRequest, Response.Listener<JSONObject> listener, @Nullable Response.ErrorListener errorListener, final Context context) {
        super(Method.GET, Constants.URL_GET_ARRAY_OF_DEALS, jsonRequest, listener, errorListener);
        this.context = context;
        this.url = "https://androidtest.inmanage.com/api/1.0/android/getDeal_"+id+".txt";
        this.mlistener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
            }
        };
        this.merrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Error",Toast.LENGTH_LONG).show();
            }
        };
        setUrl(url);
        setListener(mlistener);
        setErrorListener(merrorListener);
        RequestQueue queue = VolleyController.getInstance(context).getRequestQueue();
        queue.add(this);
    }



}

package com.example.exerecise.Util.Interfaces;


import com.example.exerecise.Util.Server_Response.NetworkResponse;

import org.json.JSONException;

public interface VolleyCallback {
    void onSuccess(NetworkResponse result) throws JSONException;
    void onError(String result) throws Exception;
}

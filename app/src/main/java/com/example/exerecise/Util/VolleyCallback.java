package com.example.exerecise.Util;


import com.example.exerecise.Models.NetworkResponse;

import org.json.JSONException;

public interface VolleyCallback {
    void onSuccess(NetworkResponse result) throws JSONException;
    void onError(String result) throws Exception;
}

package com.example.exerecise.Util.Server_Response;

import org.json.JSONException;
import org.json.JSONObject;

public class BaseResponse {

    private JSONObject response;
    private JSONObject parsed;

    public BaseResponse(JSONObject response) {
        this.response = response;
    }

    public JSONObject getResponse() {
        return response;
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

    public JSONObject getParsed() throws JSONException {
        JSONObject data = response.getJSONObject("data");
        return data;
    }

    public void setParsed(JSONObject parsed) {
        this.parsed = parsed;
    }
}

package com.example.exerecise.Models.Server_Request;

import java.util.ArrayList;
import java.util.HashMap;

public class Body {

    private HashMap<String,String> bodyParams;

    public Body(HashMap<String, String> bodyParams) {
        this.bodyParams = bodyParams;
    }

    public HashMap<String, String> getBodyParams() {
        return bodyParams;
    }

    public void setBodyParams(HashMap<String, String> bodyParams) {
        this.bodyParams = bodyParams;
    }
}

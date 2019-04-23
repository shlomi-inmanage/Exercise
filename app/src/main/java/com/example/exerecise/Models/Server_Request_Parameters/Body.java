package com.example.exerecise.Models.Server_Request_Parameters;

import java.util.HashMap;

public class Body {

    private HashMap<StringUrl, StringUrl> bodyParams;

    public Body(HashMap<StringUrl, StringUrl> bodyParams) {
        this.bodyParams = bodyParams;
    }

    public HashMap<StringUrl, StringUrl> getBodyParams() {
        return bodyParams;
    }

    public void setBodyParams(HashMap<StringUrl, StringUrl> bodyParams) {
        this.bodyParams = bodyParams;
    }
}

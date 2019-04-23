package com.example.exerecise.Models.Server_Request;

import java.util.ArrayList;
import java.util.HashMap;

public class Header {

    private HashMap<String,String> headerParams;

    public Header(HashMap<String, String> headerParams) {
        this.headerParams = headerParams;
    }

    public HashMap<String, String> getHeaderParams() {
        return headerParams;
    }

    public void setHeaderParams(HashMap<String, String> headerParams) {
        this.headerParams = headerParams;
    }
}

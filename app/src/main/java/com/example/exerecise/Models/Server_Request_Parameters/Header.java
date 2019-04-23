package com.example.exerecise.Models.Server_Request_Parameters;

import java.util.HashMap;

public class Header {

    private HashMap<StringUrl, StringUrl> headerParams;

    public Header(HashMap<StringUrl, StringUrl> headerParams) {
        this.headerParams = headerParams;
    }

    public HashMap<StringUrl, StringUrl> getHeaderParams() {
        return headerParams;
    }

    public void setHeaderParams(HashMap<StringUrl, StringUrl> headerParams) {
        this.headerParams = headerParams;
    }
}

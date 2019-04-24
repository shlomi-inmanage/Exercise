package com.example.exerecise.Models.Server_Request_Parameters;

import java.util.ArrayList;
import java.util.HashMap;

public class StringUrl {

    private java.lang.String finalUrl;
    private java.lang.String baseUrl;
    private HashMap<String,String> keysAndValues;

    public StringUrl(java.lang.String baseUrl, java.lang.String finalUrl,HashMap<String,String> keysAndValues) {
        this.baseUrl = baseUrl;
        this.finalUrl = finalUrl;
        this.keysAndValues = keysAndValues;
        setFinalUrl();
    }

    public java.lang.String getFinalUrl() {
        return finalUrl;
    }

    private void setFinalUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(finalUrl);
        if(keysAndValues!=null){
            for (int i = 0; i < keysAndValues.size(); i++) {
                if(i==0){
                    sb.append("?");
                }
                sb.append(keysAndValues.get(i)+"="+keysAndValues.get(i));
            }
        }
        this.finalUrl = sb.toString();
    }

    public java.lang.String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(java.lang.String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public HashMap<String, String> getKeysAndValues() {
        return keysAndValues;
    }

    public void setKeysAndValues(HashMap<String, String> keysAndValues) {
        this.keysAndValues = keysAndValues;
    }
}

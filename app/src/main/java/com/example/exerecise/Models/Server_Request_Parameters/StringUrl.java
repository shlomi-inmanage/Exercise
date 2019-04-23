package com.example.exerecise.Models.Server_Request_Parameters;

import java.util.ArrayList;

public class StringUrl {

    private java.lang.String finalUrl;
    private java.lang.String baseUrl;
    private ArrayList<java.lang.String> keys;
    private ArrayList<java.lang.String> values;

    public StringUrl(java.lang.String baseUrl, java.lang.String finalUrl, ArrayList<java.lang.String> keys, ArrayList<java.lang.String> values) {
        this.baseUrl = baseUrl;
        this.keys = keys;
        this.values = values;
        this.finalUrl = finalUrl;
        setFinalUrl();
    }

    public java.lang.String getFinalUrl() {
        return finalUrl;
    }

    private void setFinalUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(finalUrl);
        if(keys!=null&&values!=null){
            if(keys.size()==values.size()){
                for (int i = 0; i < keys.size(); i++) {
                    if(i==0){
                        sb.append("?");
                    }
                    sb.append(keys.get(i)+"="+values.get(i));
                }
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

    public ArrayList<java.lang.String> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<java.lang.String> keys) {
        this.keys = keys;
    }

    public ArrayList<java.lang.String> getValues() {
        return values;
    }

    public void setValues(ArrayList<java.lang.String> values) {
        this.values = values;
    }
}

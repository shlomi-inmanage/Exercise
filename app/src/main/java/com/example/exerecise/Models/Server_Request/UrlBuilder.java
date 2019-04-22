package com.example.exerecise.Models.Server_Request;

import java.util.ArrayList;

public class UrlBuilder {

    private String finalUrl;
    private String baseUrl;
    private ArrayList<String> keys;
    private ArrayList<String> values;

    public UrlBuilder(String baseUrl, ArrayList<String> keys, ArrayList<String> values) {
        this.baseUrl = baseUrl;
        this.keys = keys;
        this.values = values;
        setFinalUrl();
    }

    public String getFinalUrl() {
        return finalUrl;
    }

    private void setFinalUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl);
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

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<String> keys) {
        this.keys = keys;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }
}

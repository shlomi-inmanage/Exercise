package com.example.exerecise.Models.Server_Request;

import java.util.ArrayList;
import java.util.HashMap;

public class Header {
    private ArrayList<String> keys;
    private ArrayList<String> values;
    private HashMap<String,String> headerParams;

    public Header(ArrayList<String> keys, ArrayList<String> values) {
        this.keys = keys;
        this.values = values;
        setBodyParams(keys, values);
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

    public HashMap<String, String> getHeaderParams() {
        return headerParams;
    }

    public void setHeaderParams(HashMap<String, String> headerParams) {
        this.headerParams = headerParams;
    }

    public boolean setBodyParams(ArrayList<String> keys, ArrayList<String> values) {
        if(keys.size()==values.size()){
            for (int i = 0; i < keys.size(); i++) {
                this.headerParams.put(keys.get(i),values.get(i));
            }
            return true;
        }else{
            return false;
        }
    }
}

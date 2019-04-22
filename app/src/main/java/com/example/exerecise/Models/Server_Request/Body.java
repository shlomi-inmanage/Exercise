package com.example.exerecise.Models.Server_Request;

import java.util.ArrayList;
import java.util.HashMap;

public class Body {

    private ArrayList<String> keys;
    private ArrayList<String> values;
    private HashMap<String,String> bodyParams;

    public Body(ArrayList<String> keys, ArrayList<String> values) {
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

    public HashMap<String, String> getBodyParams() {
        return bodyParams;
    }

    public void setBodyParams(HashMap<String, String> bodyParams) {
        this.bodyParams = bodyParams;
    }

    public boolean setBodyParams(ArrayList<String> keys, ArrayList<String> values) {
        if(keys.size()==values.size()){
            for (int i = 0; i < keys.size(); i++) {
                this.bodyParams.put(keys.get(i),values.get(i));
            }
            return true;
        }else{
            return false;
        }
    }
}

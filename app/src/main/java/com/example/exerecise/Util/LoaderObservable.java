package com.example.exerecise.Util;

import java.util.Observable;

public class LoaderObservable extends Observable {

    private boolean showLoader;
    private static LoaderObservable INSTANCE = null;

    public static LoaderObservable getInstance(){
        if(INSTANCE==null){
            INSTANCE = new LoaderObservable();
        }
        return INSTANCE;
    }

    private LoaderObservable(){
    }

    public boolean isShowLoader() {
        return showLoader;
    }

    public void setShowLoader(boolean showLoader) {
        this.showLoader = showLoader;
        setChanged();
        notifyObservers();
    }
}

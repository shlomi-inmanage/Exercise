package com.example.exerecise.Util;
import com.example.exerecise.Util.Interfaces.ObservableHandler;

import java.util.Observable;
import java.util.Observer;

public class ObserverManager {


    private Observable mLoaderObservable;
    private static ObserverManager INSTANCE = null;

    public static ObserverManager getInstance(){
        if(INSTANCE==null){
            INSTANCE = new ObserverManager();
        }
        return INSTANCE;
    }

    private ObserverManager() {
        mLoaderObservable = LoaderObservable.getInstance();
    }

    public void addLoaderObserver(Observer observer){
        mLoaderObservable.addObserver(observer);
    }

    public void removeLoaderObserver(Observer observer){
        if(mLoaderObservable!=null){
            mLoaderObservable.deleteObserver(observer);
        }
    }

    public void getObservableType(Observable observable, ObservableHandler observableHandler){
        if(observable instanceof LoaderObservable){
            LoaderObservable loaderObservable = (LoaderObservable)observable;
            showLoader(loaderObservable,observableHandler);
        }
    }

    public void showLoader(LoaderObservable observable, ObservableHandler observableHandler) {
        boolean showLoader = false;
        if (observable != null) {
            showLoader = observable.isShowLoader();
        }
        observableHandler.showLoader(showLoader);
    }
}

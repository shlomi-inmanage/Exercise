package com.example.exerecise;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.exerecise.Util.Interfaces.ObservableHandler;
import com.example.exerecise.Util.ObserverManager;

import java.util.Observable;
import java.util.Observer;

public abstract class BaseActivity extends AppCompatActivity implements Observer {

    private ObserverManager observerManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observerManager = ObserverManager.getInstance();
        observerManager.addLoaderObserver(this);
    }

    public void showOrHideLoader(boolean showLoader){
        if(showLoader){
            showDialog();
        }else{
            hideDialog();
        }
    }
    public void showDialog(){
        //shows loader and hides fragments
        getLoader().setVisibility(View.VISIBLE);
    }

    public void hideDialog(){
        //shows fragments and hides loader
        getLoader().setVisibility(View.GONE);
    }

    @Override
    public void update(Observable observable, Object o) {
        observerManager.getObservableType(observable, new ObservableHandler() {
            @Override
            public void showLoader(boolean showLoader) {
                showOrHideLoader(showLoader);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        observerManager.removeLoaderObserver(this);
    }

    protected abstract View getLoader();
}

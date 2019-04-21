package com.example.exerecise;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.exerecise.Fragments.DealDetailsFragment;
import com.example.exerecise.Fragments.MainFragment;
import com.example.exerecise.Models.NetworkResponse;
import com.example.exerecise.Util.ChangeFragment;
import com.example.exerecise.Util.VolleyRequest;
import com.example.exerecise.Util.getResponse;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements getResponse, ChangeFragment {

    private ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader = findViewById(R.id.loader);
        String url = "https://androidtest.inmanage.com/api/1.0/android/getDeals.txt";
        VolleyRequest volleyRequest = new VolleyRequest(this,url, Constants.MAIN_FRAGMENT);
        showDialog();
    }

    @Override
    public void getJSONObject(JSONObject response, int goBackTo) {
        NetworkResponse networkResponse = new NetworkResponse(response);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (goBackTo){
            case Constants.MAIN_FRAGMENT:
                Fragment mainFragment = MainFragment.newInstance(networkResponse.getTransactionListItemsList(), networkResponse.getBanner());
                fragmentTransaction.replace(R.id.fragment_view, mainFragment, "mainFragment");
                fragmentTransaction.commit();
                break;

            case Constants.DEAL_DETAILS_FRAGMENT:
                Fragment dealDetailsFragment = DealDetailsFragment.newInstance(networkResponse.getTransactionItem());
                fragmentTransaction.replace(R.id.fragment_view, dealDetailsFragment,"dealDetailsFragment");
                fragmentTransaction.commit();
                break;
        }
        hideDialog();

    }

    @Override
    public void changeFragment(String id) {
        String url = "https://androidtest.inmanage.com/api/1.0/android/getDeal_"+id+".txt";
        VolleyRequest volleyRequest = new VolleyRequest(this,url, Constants.DEAL_DETAILS_FRAGMENT);
        showDialog();
    }

    public void showDialog(){
        loader.setVisibility(View.VISIBLE);
    }

    public void hideDialog(){
        loader.setVisibility(View.GONE);
    }
}

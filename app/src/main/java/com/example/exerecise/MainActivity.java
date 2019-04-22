package com.example.exerecise;

import android.app.ActionBar;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.exerecise.Fragments.DealDetailsFragment;
import com.example.exerecise.Fragments.MainFragment;
import com.example.exerecise.Models.NetworkResponse;
import com.example.exerecise.Models.TransactionItem;
import com.example.exerecise.Util.ChangeFragment;
import com.example.exerecise.Util.VolleyRequest;
import com.example.exerecise.Util.getResponse;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements getResponse, ChangeFragment {

    private ProgressBar loader;
    private View fragmentSpace;
    private final static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1234;
    private MainFragment mainFragment;
    private DealDetailsFragment dealDetailsFragment;
    private VolleyRequest volleyRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loader = findViewById(R.id.loader);
        fragmentSpace = findViewById(R.id.fragment_view);
        String url = "https://androidtest.inmanage.com/api/1.0/android/getDeals.txt";
        volleyRequest = new VolleyRequest(this,url, Constants.MAIN_FRAGMENT);
        showDialog();
    }

    @Override
    public void getJSONObject(JSONObject response, int goBackTo) {
        NetworkResponse networkResponse = new NetworkResponse(response);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (goBackTo){
            case Constants.MAIN_FRAGMENT:
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                mainFragment = MainFragment.newInstance(networkResponse.getTransactionListItemsList(), networkResponse.getBanner());
                fragmentTransaction.replace(R.id.fragment_view, mainFragment, "mainFragment").addToBackStack("MainFragment");
                fragmentTransaction.commit();
                break;

            case Constants.DEAL_DETAILS_FRAGMENT:
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                ArrayList<TransactionItem> items = new ArrayList<>();
                items.add(networkResponse.getTransactionItem());
                dealDetailsFragment = DealDetailsFragment.newInstance(items);
                fragmentTransaction.replace(R.id.fragment_view, dealDetailsFragment,"dealDetailsFragment").addToBackStack("DealDetailsFragment");
                fragmentTransaction.commit();
                break;
        }
        hideDialog();

    }

    @Override
    public void changeFragment(String id) {
        String url = "https://androidtest.inmanage.com/api/1.0/android/getDeal_"+id+".txt";
        volleyRequest = new VolleyRequest(this,url, Constants.DEAL_DETAILS_FRAGMENT);
        showDialog();
    }

    public void showDialog(){
        fragmentSpace.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);
    }

    public void hideDialog(){
        fragmentSpace.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        dealDetailsFragment.phoneIntent();
                } else {

                    Toast.makeText(MainActivity.this, "Permission Is Needed In Order To Access Phone", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        String url = "https://androidtest.inmanage.com/api/1.0/android/getDeals.txt";
        volleyRequest = new VolleyRequest(this,url, Constants.MAIN_FRAGMENT);
        showDialog();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

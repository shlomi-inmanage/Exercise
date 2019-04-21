package com.example.exerecise;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.exerecise.Fragments.MainFragment;
import com.example.exerecise.Models.NetworkResponse;
import com.example.exerecise.Util.ChangeFragment;
import com.example.exerecise.Util.VolleyRequest;
import com.example.exerecise.Util.getResponse;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements getResponse, ChangeFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://androidtest.inmanage.com/api/1.0/android/getDeals.txt";
        VolleyRequest volleyRequest = new VolleyRequest(this,url);
    }

    @Override
    public void getJSONObject(JSONObject response) {
        NetworkResponse networkResponse = new NetworkResponse(response);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = MainFragment.newInstance(networkResponse.getTransactionListItemsList(), networkResponse.getBanner());
        fragmentTransaction.replace(R.id.fragment_view, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void changeFragment(String id) {

    }
}

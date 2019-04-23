package com.example.exerecise;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.exerecise.Fragments.DealDetailsFragment;
import com.example.exerecise.Fragments.MainFragment;
import com.example.exerecise.Models.Server_Request_Parameters.ServerRequestHandler;
import com.example.exerecise.Models.TransactionItem;
import com.example.exerecise.Models.TransactionListItem;
import com.example.exerecise.Util.Interfaces.BaseServerResponseInterface;
import com.example.exerecise.Util.Interfaces.ChangeFragment;
import com.example.exerecise.Util.Interfaces.LoaderManager;
import com.example.exerecise.Util.Interfaces.VolleyCallback;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ChangeFragment,  LoaderManager, BaseServerResponseInterface {

    private ProgressBar loader;
    private View fragmentSpace;
    private final static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1234;
    private MainFragment mainFragment;
    private DealDetailsFragment dealDetailsFragment;
    private Toolbar toolbar;
    public VolleyCallback volleyCallback;
    private ServerRequestHandler serverRequestHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();
        serverRequestHandler.getDealsRequest();
//        showDialog();
//        initMainFragment();
    }


    @Override
    public void changeFragment(String id) {

        serverRequestHandler.getDealRequest(id);
    }

    public void showDialog(){
        //shows loader and hides fragments
        fragmentSpace.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);
    }

    public void hideDialog(){
        //shows fragments and hides loader
        fragmentSpace.setVisibility(View.VISIBLE);
        loader.setVisibility(View.GONE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                //handles call phone request permission
                callPermissionResponseHandler(grantResults);
                break;
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void callPermissionResponseHandler(int[] grantResults){
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            dealDetailsFragment.phoneIntent();
        } else {
            Toast.makeText(MainActivity.this, "Permission Is Needed In Order To Access Phone", Toast.LENGTH_LONG).show();
        }
    }

    private void initViews(){
        loader = findViewById(R.id.loader);
        fragmentSpace = findViewById(R.id.fragment_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        serverRequestHandler = new ServerRequestHandler(this);
    }

    private void initMainFragment(ArrayList<TransactionListItem> transactionListItemsList, String banner){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mainFragment = MainFragment.newInstance(transactionListItemsList, banner);
        fragmentTransaction.replace(R.id.fragment_view, mainFragment, "mainFragment").addToBackStack("MainFragment");
        fragmentTransaction.commit();
    }

    private void initDealFragment(TransactionItem item){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<TransactionItem> items = new ArrayList<>();
        items.add(item);
        if(dealDetailsFragment==null){
            dealDetailsFragment = DealDetailsFragment.newInstance(items);
        }
        fragmentTransaction.replace(R.id.fragment_view, dealDetailsFragment,"dealDetailsFragment").addToBackStack("DealDetailsFragment");
        fragmentTransaction.commit();
    }


    @Override
    public void showLoader() {
        showDialog();
    }

    @Override
    public void hideLoader() {
        hideDialog();
    }

    @Override
    public void onError(String result) throws Exception {

    }

    @Override
    public void getServerResponseDeals(ArrayList<TransactionListItem> transactionListItemsList, String banner) {
        initMainFragment(transactionListItemsList,banner);
    }

    @Override
    public void getServerResponseDeal(TransactionItem item) {
        initDealFragment(item);
    }
}

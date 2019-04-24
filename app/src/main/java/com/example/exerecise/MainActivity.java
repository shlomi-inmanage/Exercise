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
import com.example.exerecise.Util.Interfaces.ChangeFragment;
import com.example.exerecise.Util.Interfaces.LoaderManager;



public class MainActivity extends AppCompatActivity implements ChangeFragment,  LoaderManager{

    private ProgressBar loader;
    private View fragmentSpace;
    private final static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1234;
    private MainFragment mainFragment;
    private DealDetailsFragment dealDetailsFragment;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();
        initMainFragment();
    }


    @Override
    public void changeFragment(String dealId) {
        initDealFragment(dealId);
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
        initMainFragment();
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
    }

    private void initMainFragment(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mainFragment = new MainFragment();
        fragmentTransaction.replace(R.id.fragment_view, mainFragment, "mainFragment").addToBackStack("MainFragment");
        fragmentTransaction.commit();
    }

    private void initDealFragment(String dealId){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dealDetailsFragment = new DealDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id",dealId);
        dealDetailsFragment.setArguments(bundle);
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

}

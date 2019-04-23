package com.example.exerecise.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.bumptech.glide.Glide;
import com.example.exerecise.MainActivity;
import com.example.exerecise.Models.Constants;
import com.example.exerecise.Models.NetworkResponse;
import com.example.exerecise.Models.Server_Request.ServerRequestParameters;
import com.example.exerecise.Models.Server_Request.UrlBuilder;
import com.example.exerecise.Models.TransactionItem;
import com.example.exerecise.R;
import com.example.exerecise.Util.GeneralFuncs;
import com.example.exerecise.Util.GetPermissions;
import com.example.exerecise.Util.VolleyCallback;
import com.example.exerecise.Util.VolleyRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.util.ArrayList;


public class DealDetailsFragment extends Fragment implements OnMapReadyCallback, VolleyCallback {

    private static final String LIST_KEY = "list_key";
    private final static int SHOW_PHONE_BUTTON = 1;
    private final static int SHOW_NAV_BUTTON = 2;
    private final static int SHOW_WEBSITE_BUTTON = 4;
    private TransactionItem item;
    private Context mContext;
    private Activity mActivity;
    private TextView title, price, description;
    private ImageView imageView;
    private MapView mapView;
    private GoogleMap mMap;
    private GeneralFuncs generalFuncs;
    private Button btn_phone, btn_nav, btn_website;


    public DealDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.deal_details_fragment, container,false);
        initViews(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            getDealDetails(bundle.getString("id"));
        }
        return view;
    }



    private void setInfo(){
        title.setText(item.getTitle());
        price.setText(generalFuncs.priceSet(item.getPrice()) +mContext.getResources().getString(R.string.cost));
        description.setText(item.getDescription());
        Glide.with(mContext).load(item.getImage()).into(imageView);
        initButtons();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(item!=null){
            setMarker(new LatLng(Double.valueOf(item.getLat()),Double.valueOf(item.getLon())));
        }
    }

    private void setMarker(LatLng latLng){
        mMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
    }

    private void showButtons(int b){
        switch (b){
            case SHOW_PHONE_BUTTON:
                btn_phone.setVisibility(View.VISIBLE);
                break;
            case SHOW_NAV_BUTTON:
                btn_nav.setVisibility(View.VISIBLE);
                break;
            case SHOW_WEBSITE_BUTTON:
                btn_website.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initButtons(){
        btn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPermissions getPermissions = new GetPermissions(mContext,mActivity);
                if(getPermissions.getPhonePermission()){
                    phoneIntent();
                }
            }
        });

        btn_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(mActivity, Uri.parse(item.getWebsite()));
            }
        });

        btn_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:" + item.getLat() + "," +
                        item.getLon()));
                mContext.startActivity(intent);
            }
        });
    }


    public void phoneIntent(){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + item.getPhone()));
        startActivity(intent);
    }

    public boolean onBackPressed() {
        return false;
    }

    private void initViews(View view, Bundle savedInstanceState){
        mContext = getContext();
        mActivity = getActivity();
        mapView = view.findViewById(R.id.ddf_mapView);
        title = view.findViewById(R.id.ddf_title);
        price = view.findViewById(R.id.ddf_price);
        description = view.findViewById(R.id.ddf_description);
        imageView = view.findViewById(R.id.ddf_imageView);
        btn_phone = view.findViewById(R.id.ddf_phone_btn);
        btn_nav = view.findViewById(R.id.ddf_navigate_btn);
        btn_website = view.findViewById(R.id.ddf_website_btn);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        generalFuncs = new GeneralFuncs();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = getActivity();
        ((MainActivity)context).volleyCallback = this;
    }

    @Override
    public void onSuccess(NetworkResponse result) throws JSONException {
        item = result.getTransactionItem();
        showButtons(item.getOptionsToShow());
        setInfo();
    }

    @Override
    public void onError(String result) throws Exception {

    }

    private void getDealDetails(String id){
        String url ="https://androidtest.inmanage.com/api/1.0/android/getDeal_"+id+".txt";
        ServerRequestParameters serverRequestParameters = new ServerRequestParameters(null,null, Request.Method.GET,
                new UrlBuilder(Constants.URL_GET_SINGLE_DEAL,url,null,null),Constants.MAIN_FRAGMENT,true);
        VolleyRequest volleyRequest = new VolleyRequest(mContext,serverRequestParameters,this);
    }
}

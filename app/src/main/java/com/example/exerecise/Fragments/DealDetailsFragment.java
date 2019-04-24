package com.example.exerecise.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.exerecise.Models.TransactionItem;
import com.example.exerecise.R;
import com.example.exerecise.Util.GeneralFuncs;
import com.example.exerecise.Util.GetPermissions;
import com.example.exerecise.Util.Interfaces.ServerResponseGetDealDetails;
import com.example.exerecise.Util.Server_Request.ServerRequestManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class DealDetailsFragment extends Fragment implements OnMapReadyCallback {

    private final static int SHOW_PHONE_BUTTON = 1;
    private final static int SHOW_NAV_BUTTON = 2;
    private final static int SHOW_WEBSITE_BUTTON = 4;
    private TransactionItem mItem;
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
        Bundle bundle = getArguments();
        if(bundle!=null){
            String id = bundle.getString("id");
            sendDealRequest(id);
        }
        return view;
    }



    private void setInfo(){
        title.setText(mItem.getTitle());
        price.setText(generalFuncs.priceSet(mItem.getPrice()) +mContext.getResources().getString(R.string.cost));
        description.setText(mItem.getDescription());
        Glide.with(mContext).load(mItem.getImage()).into(imageView);
        initButtons();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(mItem!=null){
            setMarker(new LatLng(Double.valueOf(mItem.getLat()),Double.valueOf(mItem.getLon())));
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
                customTabsIntent.launchUrl(mActivity, Uri.parse(mItem.getWebsite()));
            }
        });

        btn_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:" + mItem.getLat() + "," +
                        mItem.getLon()));
                mContext.startActivity(intent);
            }
        });
    }


    public void phoneIntent(){
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mItem.getPhone()));
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
    }

    private void sendDealRequest(String id){
        ServerRequestManager serverRequestHandler = ServerRequestManager.getInstance(mContext);
        serverRequestHandler.sendDealRequest(new ServerResponseGetDealDetails() {
            @Override
            public void getServerResponseDealDetails(TransactionItem item) {
                mItem = item;
                showButtons(mItem.getOptionsToShow());
                setInfo();
            }

            @Override
            public void onError(String result) {
                Toast.makeText(mContext,result,Toast.LENGTH_LONG).show();
            }
        },id);
    }


}

package com.example.exerecise.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.exerecise.Models.TransactionItem;
import com.example.exerecise.R;
import com.example.exerecise.Util.GeneralFuncs;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;


public class DealDetailsFragment extends Fragment implements OnMapReadyCallback{

    private static final String LIST_KEY = "list_key";
    private TransactionItem item;
    private Context mContext;
    private TextView title, price, description;
    private ImageView imageView;
    private MapView mapView;
    private GoogleMap mMap;
    private GeneralFuncs generalFuncs;


    public static DealDetailsFragment newInstance(TransactionItem item){
        DealDetailsFragment detailsFragment = new DealDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(LIST_KEY,  item);
        detailsFragment.setArguments(bundle);

        return detailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.deal_details_fragment, container,false);
        mContext = getContext();
        mapView = view.findViewById(R.id.ddf_mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        generalFuncs = new GeneralFuncs();
        item = (TransactionItem) getArguments().getSerializable(LIST_KEY);
        title = view.findViewById(R.id.ddf_title);
        price = view.findViewById(R.id.ddf_price);
        description = view.findViewById(R.id.ddf_description);
        imageView = view.findViewById(R.id.ddf_imageView);
        setInfo();
        return view;
    }

    private void setInfo(){
        title.setText(item.getTitle());
        price.setText(generalFuncs.priceSet(item.getPrice()) +mContext.getResources().getString(R.string.cost));
        description.setText(item.getDescription());
        Glide.with(mContext).load(item.getImage()).into(imageView);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}

package com.example.exerecise.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.exerecise.Adapters.RecyclerViewAdapter;
import com.example.exerecise.MainActivity;
import com.example.exerecise.Models.Constants;
import com.example.exerecise.Models.NetworkResponse;
import com.example.exerecise.Models.Server_Request.ServerRequestParameters;
import com.example.exerecise.Models.Server_Request.UrlBuilder;
import com.example.exerecise.Models.TransactionListItem;
import com.example.exerecise.R;
import com.example.exerecise.Util.Pass_Response_To_Fragment_Interface;
import com.example.exerecise.Util.VolleyCallback;
import com.example.exerecise.Util.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainFragment extends Fragment implements VolleyCallback {

    private Context mContext;
    private Activity mActivity;
    private static final String LIST_KEY = "list_key";
    private static final String BANNER_KEY = "banner_key";
    private final static int BANNER_VIEW = 2;
    private final static int BANNER_LOCATION = 2;
    private final static int CARD_VIEW = 1;
    private String banner;
    private ArrayList<TransactionListItem> listItems;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;



    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.main_fragment, container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        mRecyclerView =view.findViewById(R.id.mf_recyclerView);
    }


    private void initRecyclerView(){
        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager manager = new GridLayoutManager(mContext,2);
        ((GridLayoutManager) manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if(i==BANNER_LOCATION){
                    return BANNER_VIEW;
                }else{
                    return CARD_VIEW;
                }
            }
        });
        mRecyclerView.setLayoutManager(manager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(listItems,mContext,banner);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = getActivity();
        ((MainActivity)context).volleyCallback = this;
        GetListOfDeals();
    }

    @Override
    public void onSuccess(NetworkResponse result) throws JSONException {
        listItems = result.getTransactionListItemsList();
        banner = result.getBanner();
        initRecyclerView();
    }

    @Override
    public void onError(String result) throws Exception {
        Toast.makeText(mContext,result, Toast.LENGTH_LONG).show();

    }

    private void GetListOfDeals(){
        ServerRequestParameters serverRequestParameters = new ServerRequestParameters(null,null, Request.Method.GET,
                new UrlBuilder(Constants.URL_GET_ARRAY_OF_DEALS,Constants.URL_GET_ARRAY_OF_DEALS,null,null),Constants.MAIN_FRAGMENT,true);
        VolleyRequest volleyRequest = new VolleyRequest(mContext,serverRequestParameters,this);
    }
}

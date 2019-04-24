package com.example.exerecise.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.exerecise.Adapters.RecyclerViewAdapter;
import com.example.exerecise.Models.TransactionListItem;
import com.example.exerecise.R;
import com.example.exerecise.Util.Interfaces.ServerResponseGetDeals;
import com.example.exerecise.Util.Server_Request.ServerRequestManager;

import java.util.ArrayList;

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private final static int BANNER_VIEW = 2;
    private final static int BANNER_LOCATION = 2;
    private final static int CARD_VIEW = 1;
    private String mBanner;
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
        sendDealsRequest();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initViews(View view){
        swipeRefreshLayout = view.findViewById(R.id.mf_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
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
        mRecyclerViewAdapter = new RecyclerViewAdapter(listItems,mContext,mBanner);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onRefresh() {
        sendDealsRequest();
    }

    private void sendDealsRequest(){
        ServerRequestManager serverRequestHandler = ServerRequestManager.getInstance(mContext);
        serverRequestHandler.sendDealsRequest(new ServerResponseGetDeals() {
            @Override
            public void getServerResponseDeals(ArrayList<TransactionListItem> transactionListItemsList, String banner) {
                listItems = transactionListItemsList;
                mBanner= banner;
                initRecyclerView();
                stopSwipeRefreshLayoutSpin();
            }

            @Override
            public void onError(String result) {
                Toast.makeText(mContext,result,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void stopSwipeRefreshLayoutSpin(){
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}

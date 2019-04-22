package com.example.exerecise.Fragments;

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

import com.example.exerecise.Adapters.RecyclerViewAdapter;
import com.example.exerecise.Models.TransactionListItem;
import com.example.exerecise.R;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private Context mContext;
    private static final String LIST_KEY = "list_key";
    private static final String BANNER_KEY = "banner_key";
    private String banner;
    private ArrayList<TransactionListItem> listItems;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    public static MainFragment newInstance(ArrayList<TransactionListItem> list, String banner){
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BANNER_KEY, banner);
        bundle.putSerializable(LIST_KEY, list);
        mainFragment.setArguments(bundle);

        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.main_fragment, container,false);
        mContext = getContext();
        listItems = (ArrayList<TransactionListItem>) getArguments().getSerializable(LIST_KEY);
        banner = (String) getArguments().getSerializable(BANNER_KEY);
        mRecyclerView =view.findViewById(R.id.mf_recyclerView);
        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager manager = new GridLayoutManager(mContext,2);
        ((GridLayoutManager) manager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if(i==2){
                    return 2;
                }else{
                    return 1;
                }

            }
        });
        mRecyclerView.setLayoutManager(manager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(listItems,mContext,banner);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        return view;
    }
}

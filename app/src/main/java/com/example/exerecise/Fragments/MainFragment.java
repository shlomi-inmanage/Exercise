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

import com.example.exerecise.Adapters.RecyclerViewAdapter;
import com.example.exerecise.MainActivity;
import com.example.exerecise.Models.NetworkResponse;
import com.example.exerecise.Models.TransactionListItem;
import com.example.exerecise.R;
import com.example.exerecise.Util.Pass_Response_To_Fragment_Interface;

import java.util.ArrayList;

public class MainFragment extends Fragment implements Pass_Response_To_Fragment_Interface {

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

    public static MainFragment newInstance(ArrayList<TransactionListItem> list, String banner){
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BANNER_KEY, banner);
        bundle.putSerializable(LIST_KEY, list);
        mainFragment.setArguments(bundle);

        return mainFragment;
    }

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.main_fragment, container,false);
        initViews(view);
//        getAndHandleBundle();
        return view;
    }

    private void initViews(View view){
        mRecyclerView =view.findViewById(R.id.mf_recyclerView);
    }

    private void getAndHandleBundle(){
        listItems = (ArrayList<TransactionListItem>) getArguments().getSerializable(LIST_KEY);
        banner = (String) getArguments().getSerializable(BANNER_KEY);
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
        ((MainActivity)context).to_fragment_interface = this;
    }

    @Override
    public void pass_response_to_main_fragment(NetworkResponse networkResponse) {
        listItems = networkResponse.getTransactionListItemsList();
        banner = networkResponse.getBanner();
        initRecyclerView();

    }
}

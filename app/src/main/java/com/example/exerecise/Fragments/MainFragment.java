package com.example.exerecise.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.exerecise.Adapters.GridViewAdapter;
import com.example.exerecise.Models.TransactionListItem;
import com.example.exerecise.R;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private Context mcContext;
    private static final String LIST_KEY = "list_key";
    private static final String BANNER_KEY = "banner_key";
    private String banner;
    private ArrayList<TransactionListItem> listItems;
    private GridView gridView;
    private GridViewAdapter viewAdapter;

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
        mcContext = getContext();
        listItems = (ArrayList<TransactionListItem>) getArguments().getSerializable(LIST_KEY);
        banner = (String) getArguments().getSerializable(BANNER_KEY);
        View view  = inflater.inflate(R.layout.main_fragment, container,false);
        gridView = (GridView)view.findViewById(R.id.mf_gridview);
        viewAdapter = new GridViewAdapter(listItems,mcContext,banner);
        gridView.setAdapter(viewAdapter);
        return view;
    }
}

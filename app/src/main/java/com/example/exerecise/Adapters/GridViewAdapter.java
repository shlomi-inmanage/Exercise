package com.example.exerecise.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.exerecise.Models.TransactionListItem;
import com.example.exerecise.R;
import com.example.exerecise.Util.ChangeFragment;

import java.util.ArrayList;

public class GridViewAdapter  extends BaseAdapter {

    private ArrayList<TransactionListItem> transactionListItemsList;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private String banner;
    private ChangeFragment mCallback;

    public GridViewAdapter(ArrayList<TransactionListItem> transactionListItemsList, Context context, String banner) {
        this.transactionListItemsList = transactionListItemsList;
        this.mContext = context;
        this.banner = banner;
        this.layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mCallback = (ChangeFragment)mContext;
    }

    @Override
    public int getCount() {
        return transactionListItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return transactionListItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecyclerView.ViewHolder viewHolder;
        final TransactionListItem item = transactionListItemsList.get(position);
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.main_fragment_gridview_cardview, null);
        }else{
            viewHolder = (RecyclerView.ViewHolder)convertView.getTag();
        }

        final TextView title = convertView.findViewById(R.id.cv_title);
        final TextView price = convertView.findViewById(R.id.cv_price);
        final ImageView image = convertView.findViewById(R.id.cv_imageview);

        title.setText(item.getTitle());
        price.setText(String.valueOf(item.getPrice()));
        Glide.with(mContext).load(item.getImage()).into(image);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.changeFragment(item.getId());
            }
        });
        return convertView;
    }
}

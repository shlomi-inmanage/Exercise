package com.example.exerecise.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.exerecise.Models.TransactionListItem;
import com.example.exerecise.R;
import com.example.exerecise.Util.ChangeFragment;
import com.example.exerecise.Util.GeneralFuncs;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<TransactionListItem> transactionListItemsList;
    private final static int BANNER_VIEW = 1;
    private final static int BANNER_LOCATION = 2;
    private final static int CARD_VIEW = 0;
    private Context mContext;
    private String banner;
    private ChangeFragment mCallback;
    private GeneralFuncs generalFuncs;

    public RecyclerViewAdapter(ArrayList<TransactionListItem> transactionListItemsList, Context context, String banner) {
        generalFuncs =new GeneralFuncs();
        this.transactionListItemsList = generalFuncs.sortArrayList(transactionListItemsList);
        this.mContext = context;
        this.banner = banner;
        this.mCallback = (ChangeFragment)mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view;
        if(i==BANNER_VIEW){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.main_fragment_gridview_banner,null);
        }else{
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.main_fragment_gridview_cardview,null);
        }
        return new RecyclerViewAdapter.ViewHolder(view, i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int i) {
        if(viewHolder.getItemViewType()==BANNER_VIEW){
            Glide.with(mContext).load(banner).into(viewHolder.image);
        }else{
            final TransactionListItem transactionListItem;
            if(i>BANNER_LOCATION){
                transactionListItem = transactionListItemsList.get(i-1);

            }else{
                transactionListItem = transactionListItemsList.get(i);
            }
            viewHolder.title.setText(transactionListItem.getTitle());
            viewHolder.price.setText(generalFuncs.priceSet(transactionListItem.getPrice())+mContext.getResources().getString(R.string.cost));
            Glide.with(mContext).load(transactionListItem.getImage()).into(viewHolder.image);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.changeFragment(transactionListItem.getId());
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return transactionListItemsList.size()+1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title ;
        public TextView price ;
        public ImageView image ;

        public ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            switch (viewType){
                case CARD_VIEW:
                    title = itemView.findViewById(R.id.cv_title);
                    price = itemView.findViewById(R.id.cv_price);
                    image = itemView.findViewById(R.id.cv_imageview);
                    break;
                case BANNER_VIEW:
                    image = itemView.findViewById(R.id.mf_banner);
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = CARD_VIEW;
        if(position==BANNER_LOCATION){
            type=BANNER_VIEW;
        }
        return type;
    }
}

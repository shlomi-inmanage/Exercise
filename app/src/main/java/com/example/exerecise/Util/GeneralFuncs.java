package com.example.exerecise.Util;

import com.example.exerecise.Models.TransactionListItem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GeneralFuncs {

    public ArrayList<TransactionListItem> sortArrayList(ArrayList<TransactionListItem> list){
        Collections.sort(list, new Comparator<TransactionListItem>() {
            @Override
            public int compare(TransactionListItem o1, TransactionListItem o2) {
                return o1.getOrder_num() - o2.getOrder_num();
            }
        });
        return list;
    }

    public String priceSet(int price){
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        String newPrice  = formatter.format(price);
        return newPrice;
    }
}

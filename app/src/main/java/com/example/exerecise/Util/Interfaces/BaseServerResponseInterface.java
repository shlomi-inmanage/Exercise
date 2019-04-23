package com.example.exerecise.Util.Interfaces;

import com.example.exerecise.Models.TransactionItem;
import com.example.exerecise.Models.TransactionListItem;

import java.util.ArrayList;

public interface BaseServerResponseInterface {
    void onError(String result) throws Exception;
    void getServerResponseDeals(ArrayList<TransactionListItem> transactionListItemsList, String banner);
    void getServerResponseDeal(TransactionItem item);
}

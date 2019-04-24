package com.example.exerecise.Util.Interfaces;

import com.example.exerecise.Models.TransactionListItem;

import java.util.ArrayList;

public interface ServerResponseGetDeals extends BaseServerResponseInterface {
    void getServerResponseDeals(ArrayList<TransactionListItem> transactionListItemsList, String banner);
}

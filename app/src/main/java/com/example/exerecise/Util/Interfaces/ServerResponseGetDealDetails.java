package com.example.exerecise.Util.Interfaces;

import com.example.exerecise.Models.TransactionItem;

public interface ServerResponseGetDealDetails extends BaseServerResponseInterface {
    void getServerResponseDealDetails(TransactionItem item);
}

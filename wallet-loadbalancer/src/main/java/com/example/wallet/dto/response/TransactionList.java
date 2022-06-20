package com.example.wallet.dto.response;

import java.util.List;

import lombok.Data;
import lombok.ToString;
@Data
@ToString()
public class TransactionList {
    private List<TransactionResponse> transactionResponses;

    public TransactionList() {
    }

    // standard constructor and getter/setter
}

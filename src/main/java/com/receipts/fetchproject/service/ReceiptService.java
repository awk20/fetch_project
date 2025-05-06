package com.receipts.fetchproject.service;

import com.receipts.fetchproject.model.Receipt;

import java.util.List;
import java.util.Optional;

public interface ReceiptService {
    Receipt saveReceipt(Receipt receipt);

    List<Receipt> fetchReceiptList();

    Optional<Receipt> findReceiptById(String id);
}

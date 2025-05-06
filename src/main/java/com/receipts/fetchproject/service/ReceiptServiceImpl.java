package com.receipts.fetchproject.service;

import com.receipts.fetchproject.model.Receipt;
import com.receipts.fetchproject.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptServiceImpl implements ReceiptService{
    @Autowired
    ReceiptRepository receiptRepository;

    @Override
    public Receipt saveReceipt(Receipt receipt){
        return receiptRepository.save(receipt);
    }

    @Override
    public List<Receipt> fetchReceiptList(){
        return (List<Receipt>) receiptRepository.findAll();
    }

    @Override
    public Optional<Receipt> findReceiptById(String id){
        return receiptRepository.findById(id);
    }
}

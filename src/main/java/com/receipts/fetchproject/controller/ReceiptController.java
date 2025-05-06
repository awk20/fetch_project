package com.receipts.fetchproject.controller;

import com.receipts.fetchproject.model.Item;
import com.receipts.fetchproject.model.Receipt;
import com.receipts.fetchproject.model.ReceiptPoints;
import com.receipts.fetchproject.service.ReceiptService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ReceiptController {

    // Autowired tells Spring Boot to make a Bean of the ReceiptService upon startup
    @Autowired
    private ReceiptService receiptService;


//    @PostMapping("receipts/process")
//    public Receipt saveReceipt(@RequestBody Receipt receipt){
//        return receiptService.saveReceipt(receipt);
//    }


    @PostMapping("receipts/process")
    public ResponseEntity<Map<String, String>> saveReceipt(@RequestBody Receipt receipt){
        receiptService.saveReceipt(receipt);
        Map<String, String> response = new HashMap<>();
        response.put("id", receipt.getId());
        return ResponseEntity.ok(response);
    }

    //@GetMapping("/receipts/{id}/points")
    @GetMapping("receipts")
    public List<Receipt> getReceiptList(){
        return receiptService.fetchReceiptList();
    }

    @GetMapping("receipts/{id}")
    public Optional<Receipt> getReceiptById(@PathVariable("id") String id){
        return receiptService.findReceiptById(id);
    }

    @GetMapping("receipts/{id}/points")
    public ReceiptPoints getReceiptPoints(@PathVariable("id") String id){
        // Get Receipt object
        Optional<Receipt> optionalReceipt = receiptService.findReceiptById(id);
        Integer points = 0;
        if(!optionalReceipt.isEmpty()){
            Receipt receipt = optionalReceipt.get();
            // Add points for every alphanumeric character in retailer name
            //logger.info(Integer.valueOf(receipt.getRetailer().length()) + " points added");
            //points += Integer.valueOf(receipt.getRetailer().length()); // *********** CHECK THIS
            String retailer = receipt.getRetailer();
            int tempRetailerPoints = 0;
            for(int i = 0; i < retailer.length(); i++) {
                if (Character.isLetterOrDigit(retailer.charAt(i))) {
                    tempRetailerPoints++;
                    points += 1;
                }
            }

            // 50 points if total is round dollar amount
            double price = Double.valueOf(receipt.getTotal());
            // check if price has no cent value
            if(price == Math.floor(price)){
                points += 50;
            }

            // Add points if total is multiple of 0.25
            if(price % 0.25 == 0){
                points += 25;
            }

            // Add points for every two items in list
            List<Item> items = receipt.getItems();

            int listSize = items.size();
            points += (listSize / 2) * 5;

            // Add points for trimmed length of item descriptions
            for(int i = 0; i < items.size(); i++){
                Item item = items.get(i);
                // If trimmed item description length is multiple of 3 take price, multiply by 0.2, & round up
                if(item.getShortDescription().trim().length() % 3 == 0){
                    points += (int) Math.ceil(Double.valueOf(item.getPrice()) * 0.2);
                }
            }

            // Add points if day was odd
            String date = receipt.getPurchaseDate();
            // Get day out of full date
            String day = date.substring(date.length() - 2);
            // turn day into int and check if odd
            if(Integer.valueOf(day) % 2 != 0){
                points += 6;
            }

            // Add points if time is between 2pm and 4pm
            String tempTime = receipt.getPurchaseTime();
            String time = tempTime.substring(0, 2) + tempTime.substring(tempTime.length() - 2);
            if(Integer.valueOf(time) > 1400 && Integer.valueOf(time) < 1600){
                points += 10;
            }
        }

        // logic for getting receipt points
        ReceiptPoints receiptPoints = new ReceiptPoints();
        receiptPoints.setPoints(points);
        return receiptPoints;
    }
}

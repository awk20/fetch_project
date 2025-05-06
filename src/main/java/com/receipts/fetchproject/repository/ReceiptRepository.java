package com.receipts.fetchproject.repository;

import com.receipts.fetchproject.model.Receipt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Crud Repository is an interface with some built in logic to post get put & delete items in a crud application
@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, String> {

}

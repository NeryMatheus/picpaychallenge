package com.picpaychallenge.module.transaction;

import com.picpaychallenge.module.transaction.dtos.TransactionDTO;
import com.picpaychallenge.module.transaction.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody  TransactionDTO transactionDTO) throws Exception {
        System.out.println("TransactionDTO: " + transactionDTO);
        this.transactionService.saveTransaction(transactionDTO);

        return ResponseEntity.ok().build();
    }

}

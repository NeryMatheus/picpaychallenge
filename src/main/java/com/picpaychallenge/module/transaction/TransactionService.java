package com.picpaychallenge.module.transaction;

import com.picpaychallenge.module.transaction.dtos.TransactionDTO;
import com.picpaychallenge.module.transaction.repository.TransactionRepository;
import com.picpaychallenge.module.user.UserService;
import com.picpaychallenge.module.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public void saveTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender = this.userService.getUserById(transactionDTO.sender());
        User receiver = this.userService.getUserById(transactionDTO.receiver());

        this.userService.ValidateTransaction(sender, receiver, transactionDTO.amount());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.amount()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.amount()));


        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

    }
}

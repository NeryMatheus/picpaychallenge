package com.picpaychallenge.module.transaction;

import com.picpaychallenge.module.transaction.dtos.TransactionDTO;
import com.picpaychallenge.module.transaction.entities.Transaction;
import com.picpaychallenge.module.transaction.repository.TransactionRepository;
import com.picpaychallenge.module.user.UserService;
import com.picpaychallenge.module.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private boolean authorized = true;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public void makeTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender = getUserById(transactionDTO.sender());
        User receiver = getUserById(transactionDTO.receiver());

        validateTransaction(sender, receiver, transactionDTO.amount());

        if (!mockExternalAuthorizer()) {
            throw new Exception("Transaction not authorized");
        }

        saveTransaction(new Transaction(sender, receiver, transactionDTO.amount()));

        updateBalances(sender, receiver, transactionDTO.amount());

        updateUser(sender);
        updateUser(receiver);
    }

    private boolean mockExternalAuthorizer() {
        authorized = !authorized;
        return authorized;
    }

    public List<Transaction> getAllTransactions() {
        return this.transactionRepository.findAll();
    }

    private User getUserById(Long id) throws  Exception{ return this.userService.getUserById(id); }

    private void validateTransaction (User sender, User receiver, BigDecimal amount) throws Exception {
        this.userService.ValidateTransaction(sender, receiver, amount);
    }

    private void saveTransaction(Transaction transaction) { this.transactionRepository.save(transaction); }

    private void updateBalances(User sender, User receiver, BigDecimal amount) {
        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));
        updateUser(sender);
        updateUser(receiver);
    }

    private void updateUser(User user) {
        this.userService.saveUser(user);
    }
}

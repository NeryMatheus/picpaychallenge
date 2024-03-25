package com.picpaychallenge.module.transaction.dtos;

import java.math.BigDecimal;

public record TransactionDTO (Long sender, Long receiver, BigDecimal amount, String timestamp){
}

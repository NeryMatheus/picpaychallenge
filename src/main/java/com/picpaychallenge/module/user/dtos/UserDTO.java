package com.picpaychallenge.module.user.dtos;

import com.picpaychallenge.module.user.enums.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, String email, String password, BigDecimal balance, UserType type) {
}

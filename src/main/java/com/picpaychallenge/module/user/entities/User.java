package com.picpaychallenge.module.user.entities;

import com.picpaychallenge.module.user.dtos.UserDTO;
import com.picpaychallenge.module.user.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String document;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType type;

    public User(UserDTO userDto) {
        this.firstName = userDto.firstName();
        this.lastName = userDto.lastName();
        this.document = userDto.document();
        this.email = userDto.email();
        this.password = userDto.password();
        this.balance = userDto.balance();
        this.type = userDto.type();
    }
}
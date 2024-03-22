package com.picpaychallenge.module.user.repository;

import com.picpaychallenge.module.user.entities.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByDocument(String document);

    @NonNull
    Optional<User> findById(@NonNull Long id);

    @NonNull
    List<User> findAll();

    @NonNull
    User save(@NonNull User user);

}

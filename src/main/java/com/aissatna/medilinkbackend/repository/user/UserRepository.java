package com.aissatna.medilinkbackend.repository.user;

import com.aissatna.medilinkbackend.model.User;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Cacheable(value = "users", key = "#email")
    Optional<User> findByEmail(String email);


    @NotNull
    @Cacheable(value = "users", key = "#id")
    Optional<User> findById(@NotNull Long id);
}
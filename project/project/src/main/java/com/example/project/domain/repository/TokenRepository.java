package com.example.project.domain.repository;

import com.example.project.domain.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    boolean existsByToken(String token);
    Token findByToken(String token);
    Optional<Token> findByUserId(Long id);
}
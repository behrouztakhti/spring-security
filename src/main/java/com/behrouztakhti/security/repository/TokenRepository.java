package com.behrouztakhti.security.repository;

import com.behrouztakhti.security.domain.Tokens;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TokenRepository extends JpaRepository<Tokens, Long> {

    Optional<Tokens> findByToken(String token);
    @Modifying
    @Transactional
    void deleteAllByUserId(Long userId);
}

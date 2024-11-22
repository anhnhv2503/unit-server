package com.anhnhv.unit.server.repository;

import com.anhnhv.unit.server.entities.RefreshToken;
import com.anhnhv.unit.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    @Modifying
    int deleteByUser(User user);
    }

package com.example.realtimestreaming.Repository;

import com.example.realtimestreaming.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByNickname(String nickname);

    Boolean existsByEmail(String email);

    User findByUserId(Long userId);

    User findByEmail(String email);

    User findByNickname(String nickname);
}

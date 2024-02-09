package com.eat.eat_server.domain.user.repository;

import com.eat.eat_server.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    boolean existsByEmail(String email);

//    User findByEmail(String email);

    Optional<User> findByEmail(String email);
}

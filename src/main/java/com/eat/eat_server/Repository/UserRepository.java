package com.eat.eat_server.Repository;

import com.eat.eat_server.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    boolean existsByEmail(String email);

//    User findByEmail(String email);

    Optional<User> findByEmail(String email);
}

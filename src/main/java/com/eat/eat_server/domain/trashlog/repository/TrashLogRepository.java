package com.eat.eat_server.domain.trashlog.repository;

import com.eat.eat_server.domain.trashlog.domain.TrashLog;
import com.eat.eat_server.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


public interface TrashLogRepository extends JpaRepository<TrashLog, Long> {
    boolean existsByCreatedTimeBetweenAndUser(LocalDateTime startDay, LocalDateTime endDay, User user);
    Optional<TrashLog> findByCreatedTimeBetweenAndUser(LocalDateTime startDay, LocalDateTime endDay, User user);
}
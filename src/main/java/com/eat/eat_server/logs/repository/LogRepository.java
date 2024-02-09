package com.eat.eat_server.logs.repository;

import com.eat.eat_server.logs.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LogRepository extends JpaRepository<Log, Long> {
}

package com.eat.eat_server.domain.logs.repository;

import com.eat.eat_server.domain.logs.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findBySubCategoryId(long subCategoryId);
}

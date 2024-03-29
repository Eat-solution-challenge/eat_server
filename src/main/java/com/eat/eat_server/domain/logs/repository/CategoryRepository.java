package com.eat.eat_server.domain.logs.repository;

import com.eat.eat_server.domain.logs.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);
}

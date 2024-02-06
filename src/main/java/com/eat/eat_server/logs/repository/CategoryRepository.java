package com.eat.eat_server.logs.repository;

import com.eat.eat_server.logs.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);
}

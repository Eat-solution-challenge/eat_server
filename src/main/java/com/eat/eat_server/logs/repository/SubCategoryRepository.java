package com.eat.eat_server.logs.repository;

import com.eat.eat_server.logs.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    SubCategory findByName(String name);
    Boolean existsByName(String name);
}

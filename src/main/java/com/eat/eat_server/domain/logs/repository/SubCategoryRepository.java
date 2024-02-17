package com.eat.eat_server.domain.logs.repository;

import com.eat.eat_server.domain.logs.domain.SubCategory;
import com.eat.eat_server.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    SubCategory findByUserAndName(User user, String name);
    boolean existsByName(String name);
    boolean existsByUserAndName(User user, String name);
    List<SubCategory> findByUserAndCategoryId(User user, long categoryId);

    List<SubCategory> findByUser(User user);
}

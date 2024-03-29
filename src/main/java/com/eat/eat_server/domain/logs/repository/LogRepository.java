package com.eat.eat_server.domain.logs.repository;

import com.eat.eat_server.domain.logs.domain.Log;
import com.eat.eat_server.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findBySubCategoryId(long subCategoryId);

    List<Log> findBySubCategoryIdAndUnit(long subCategoryId, String unit);

    @Query("SELECT l FROM Log l WHERE l.menu LIKE %:keyword% AND l.subCategory.user = :user")
    List<Log> findByUserAndMenuKeyword(@Param("user") User user, @Param("keyword") String keyword);

    List<Log> findByCreatedTimeBetweenAndSubCategoryId(LocalDateTime start, LocalDateTime end, long subCategoryId);
  
}

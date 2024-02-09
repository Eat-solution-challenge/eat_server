package com.eat.eat_server.domain.logs.service;

import com.eat.eat_server.domain.logs.domain.SubCategory;
import com.eat.eat_server.domain.logs.dto.SubCategoryResponseDto;
import com.eat.eat_server.domain.logs.repository.SubCategoryRepository;
import com.eat.eat_server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final SubCategoryRepository subCategoryRepository;

    public List<SubCategoryResponseDto> findSubCategories(User user, Long categoryId) {
        List<SubCategory> subCategories = subCategoryRepository.findByUserAndCategoryId(user, categoryId);
        return subCategories.stream()
                .map(SubCategoryResponseDto::from)
                .collect(Collectors.toList());
    }
}

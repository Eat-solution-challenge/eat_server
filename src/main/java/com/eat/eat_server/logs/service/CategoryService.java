package com.eat.eat_server.logs.service;

import com.eat.eat_server.logs.domain.SubCategory;
import com.eat.eat_server.logs.dto.SubCategoryResponseDto;
import com.eat.eat_server.logs.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final SubCategoryRepository subCategoryRepository;

    public List<SubCategoryResponseDto> findSubCategories(Long categoryId) {
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        return subCategories.stream()
                .map(SubCategoryResponseDto::from)
                .collect(Collectors.toList());
    }
}

package com.eat.eat_server.logs.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String name;

    @Builder
    private SubCategory(Category category, String name) {
        this.category = category;
        this.name = name;
    }

    public static SubCategory of(Category category, String name) {
        return SubCategory.builder()
                .category(category)
                .name(name)
                .build();
    }
}

package com.eat.eat_server.domain.logs.domain;

import com.eat.eat_server.domain.user.domain.User;
import com.eat.eat_server.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SubCategory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Builder
    private SubCategory(User user, Category category, String name) {
        this.user = user;
        this.category = category;
        this.name = name;
    }

    public static SubCategory of(User user, Category category, String name) {
        return SubCategory.builder()
                .user(user)
                .category(category)
                .name(name)
                .build();
    }
}

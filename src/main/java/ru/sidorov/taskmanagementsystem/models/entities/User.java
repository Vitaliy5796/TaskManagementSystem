package ru.sidorov.taskmanagementsystem.models.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор пользователя")
    private Integer id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Почта пользователя")
    private String email;

    @Column(nullable = false)
    @Schema(description = "Пароль пользователя")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "link_user_roles", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id",
                    nullable = false, updatable = false)})
    @Schema(description = "Роли пользователя")
    private Set<Role> roles = new HashSet<>(0);
}

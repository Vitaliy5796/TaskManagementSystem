package ru.sidorov.taskmanagementsystem.models.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

/**
 * Представляет пользователя системы.
 * Реализует интерфейс {@link UserDetails} для работы с Spring Security.
 *
 * @author Vitaliy5796
 * @version 1.1
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@ApiModel(description = "Пользователь")
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    /**
     * Уникальный идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Уникальный идентификатор пользователя")
    private Integer id;


    /**
     * Email пользователя, который также используется как логин.
     */
    @Column(unique = true, nullable = false)
    @ApiModelProperty(notes = "Почта пользователя")
    private String email;

    /**
     * Имя пользователя.
     */
    @Column
    private String username;

    /**
     * Пароль пользователя (захешированный).
     */
    @Column(nullable = false)
    @ApiModelProperty(notes = "Пароль пользователя")
    private String password;

    /**
     * Роль пользователя в системе.
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

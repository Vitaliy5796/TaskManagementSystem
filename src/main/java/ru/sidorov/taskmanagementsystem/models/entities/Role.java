package ru.sidorov.taskmanagementsystem.models.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Представляет роль пользователя в системе.
 * Реализует интерфейс {@link GrantedAuthority} для работы с Spring Security.
 *
 * @author Vitaliy5796
 * @version 1.1
 */
@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Роли пользователя")
@Table(name = "roles")
@AllArgsConstructor
@Builder
public class Role implements GrantedAuthority {

    /**
     * Уникальный идентификатор роли.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(notes = "Уникальный идентификатор роли")
    private int id;


    /**
     * Наименование роли (например, ROLE_ADMIN, ROLE_USER).
     */
    @Column(name = "name", nullable = false)
    @ApiModelProperty(notes = "Наименование роли")
    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}

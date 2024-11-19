package ru.sidorov.taskmanagementsystem.models.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "Роли пользователя")
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @ApiModelProperty(notes = "Уникальный идентификатор роли")
    private int id;

    @Column(name = "code", nullable = false, length = 100)
    @ApiModelProperty(notes = "Код роли")
    private String code;

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

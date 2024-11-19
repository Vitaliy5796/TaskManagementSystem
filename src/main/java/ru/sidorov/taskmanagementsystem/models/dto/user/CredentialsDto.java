package ru.sidorov.taskmanagementsystem.models.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "Login / auth credentials")
@Getter
@Setter
@NoArgsConstructor
public class CredentialsDto {

    @ApiModelProperty(notes = "Логин пользователя")
    private String login;

    @ApiModelProperty(notes = "Пароль пользователя")
    private String password;
}

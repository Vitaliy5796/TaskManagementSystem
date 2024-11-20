package ru.sidorov.taskmanagementsystem.models.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(description = "Пользователь для отображения")
@Data
@NoArgsConstructor
public class UserSaveDto {

    @ApiModelProperty(notes = "id пользователя (только для обновления)")
    private Integer id;

    @Email(message = "Email должен быть в корректном формате")
    @NotBlank(message = "Email не может быть пустым")
    @ApiModelProperty(notes = "Почта пользователя")
    private String email;

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 3, max = 50, message = "Имя пользователя должно быть от 3 до 50 символов")
    @ApiModelProperty(notes = "Имя пользователя")
    private String username;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    @ApiModelProperty(notes = "Пароль пользователя")
    private String password;
}

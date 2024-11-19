package ru.sidorov.taskmanagementsystem.models.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Пользователь для отображения")
@Data
@NoArgsConstructor
public class UserDto {

    @ApiModelProperty(notes = "id пользователя")
    private Integer id;
    @ApiModelProperty(notes = "Почта пользователя")
    private String email;
    @ApiModelProperty(notes = "Имя пользователя")
    private String username;
}

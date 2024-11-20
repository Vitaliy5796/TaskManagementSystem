package ru.sidorov.taskmanagementsystem.models.dto.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(description = "Комментарий для отображения")
@Data
@NoArgsConstructor
public class CommentSaveDto {

    @NotBlank(message = "Контент не может быть пустым")
    @Size(max = 200, message = "Контент не может превышать 200 символов.")
    @ApiModelProperty(notes = "Контент комментария")
    private String content;
    @ApiModelProperty(notes = "Задача к которой написан комментарий")
    private TaskDto task;
}

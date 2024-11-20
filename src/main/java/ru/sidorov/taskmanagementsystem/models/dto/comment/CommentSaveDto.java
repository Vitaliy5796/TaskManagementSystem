package ru.sidorov.taskmanagementsystem.models.dto.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;

@ApiModel(description = "Комментарий для отображения")
@Data
@NoArgsConstructor
public class CommentSaveDto {

    @ApiModelProperty(notes = "Контент комментария")
    private String content;
    @ApiModelProperty(notes = "Задача к которой написан комментарий")
    private TaskDto task;
}

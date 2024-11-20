package ru.sidorov.taskmanagementsystem.models.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@ApiModel(description = "Задача для отображения")
@Data
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class TaskDto {

    @ApiModelProperty(notes = "id задачи (только для отображения)")
    private Integer id;
    @ApiModelProperty(notes = "Заголовок задачи")
    private String title;
    @ApiModelProperty(notes = "Описание задачи")
    private String description;
    @ApiModelProperty(notes = "Статус задачи")
    private String status;
    @ApiModelProperty(notes = "Приоритет задачи")
    private String priority;
    @ApiModelProperty(notes = "Испольнитель задачи")
    private UserDto assignee;
    @ApiModelProperty(notes = "Автор задачи")
    private UserDto author;
}

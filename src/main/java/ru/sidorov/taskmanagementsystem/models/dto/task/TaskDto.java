package ru.sidorov.taskmanagementsystem.models.dto.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentDto;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@ApiModel(description = "Задача для отображения")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(NON_NULL)
@Builder
public class TaskDto {

    @ApiModelProperty(notes = "id задачи (только для отображения)")
    private Integer id;

    @NotBlank(message = "Название не может быть пустым")
    @Size(max = 25, message = "Название не может превышать 25 символов.")
    @ApiModelProperty(notes = "Заголовок задачи")
    private String title;

    @NotBlank(message = "Описание не может быть пустым")
    @Size(max = 255, message = "Описание не может превышать 255 символов.")
    @ApiModelProperty(notes = "Описание задачи")
    private String description;

    @Size(max = 12, message = "Статус может быть PENDING, IN_PROGRESS или COMPLETED")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Статус задачи")
    private String status;

    @Size(max = 7, message = "Приоритет может быть HIGH, MEDIUM или LOW")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Приоритет задачи")
    private String priority;

    @ApiModelProperty(notes = "Испольнитель задачи")
    private UserDto assignee;

    @ApiModelProperty(notes = "Автор задачи")
    private UserDto author;

    @ApiModelProperty(notes = "Версия задачи (для контроля изменений)")
    private Integer version = 1;

    @ApiModelProperty(notes = "Дата и время создания задачи (Только для отображения)")
    private LocalDateTime createdAt;

    @ApiModelProperty(notes = "Дата и время последнего обновления задачи (Только для отображения)")
    private LocalDateTime updatedAt;

    @ApiModelProperty(notes = "Комментарии к задаче")
    private List<CommentDto> comments;
}

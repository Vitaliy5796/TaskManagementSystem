package ru.sidorov.taskmanagementsystem.models.dto.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sidorov.taskmanagementsystem.models.dto.user.UserDto;

@ApiModel(description = "Комментарий для отображения")
@Data
@NoArgsConstructor
public class CommentDto {

    @ApiModelProperty(notes = "id комментария")
    private Integer id;
    @ApiModelProperty(notes = "Контент комментария для отображения")
    private String content;
    @ApiModelProperty(notes = "Автор комментария")
    private UserDto author;
}

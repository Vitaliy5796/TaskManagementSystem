package ru.sidorov.taskmanagementsystem.models.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
@ApiModel(description = "Комментарий")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Уникальный идентификатор комментария")
    private Integer id;

    @ApiModelProperty(notes = "Содержание комментария")
    @Column
    private String content;

    @ApiModelProperty(notes = "Автор комментария")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ApiModelProperty(notes = "Задача комментария")
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}

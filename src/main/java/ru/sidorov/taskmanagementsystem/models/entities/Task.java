package ru.sidorov.taskmanagementsystem.models.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sidorov.taskmanagementsystem.models.enums.Priority;
import ru.sidorov.taskmanagementsystem.models.enums.Status;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tasks")
@ApiModel(description = "Задачи")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Уникальный идентификатор задачи")
    private Integer id;

    @Column(nullable = false)
    @ApiModelProperty(notes = "Заголовок задачи")
    private String title;

    @Column(nullable = false)
    @ApiModelProperty(notes = "Описание задачи")
    private String description;

    @ApiModelProperty(notes = "Статус задачи")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ApiModelProperty(notes = "Приоритет задачи")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ApiModelProperty(notes = "Автор задачи")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ApiModelProperty(notes = "Исполнитель задачи")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ApiModelProperty(notes = "Комментарии задачи")
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
}

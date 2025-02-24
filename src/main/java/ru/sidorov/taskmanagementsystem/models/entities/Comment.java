package ru.sidorov.taskmanagementsystem.models.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Представляет комментарий к задаче.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comments")
@ApiModel(description = "Комментарий")
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    /**
     * Уникальный идентификатор комментария.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Уникальный идентификатор комментария")
    private Integer id;


    /**
     * Содержание комментария.
     */
    @ApiModelProperty(notes = "Содержание комментария")
    @Column
    private String content;


    /**
     * Автор комментария.
     */
    @ApiModelProperty(notes = "Автор комментария")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;


    /**
     * Задача, к которой относится комментарий.
     */
    @ApiModelProperty(notes = "Задача комментария")
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    /**
     * Дата и время создания комментария.
     * Заполняется автоматически.
     */
    @CreatedDate
    @Column(updatable = false)
    @ApiModelProperty(notes = "Дата и время создания комментария")
    private LocalDateTime createdAt;
}

package ru.sidorov.taskmanagementsystem.models.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.sidorov.taskmanagementsystem.models.enums.Priority;
import ru.sidorov.taskmanagementsystem.models.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс, представляющий задачу.
 * Хранит основную информацию о задаче, такую как заголовок, описание, статус, приоритет,
 * автор, исполнитель, комментарии, а также метаданные о времени создания и обновления.
 * Используется для управления задачами в системе.
 *
 * @author Vitaliy5796
 * @version 1.1
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tasks")
@ApiModel(description = "Сущность задачи, содержащая основную информацию о ней")
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Task {

    /**
     * Уникальный идентификатор задачи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Уникальный идентификатор задачи")
    private Integer id;

    /**
     * Заголовок задачи.
     */
    @Column(nullable = false)
    @ApiModelProperty(notes = "Заголовок задачи")
    private String title;

    /**
     * Описание задачи.
     */
    @Column(nullable = false)
    @ApiModelProperty(notes = "Описание задачи")
    private String description;

    /**
     * Статус задачи.
     * Возможные значения:
     * - PENDING: Задача запланирована.
     * - IN_PROGRESS: Задача в работе.
     * - COMPLETED: Задача завершена.
     */
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Статус задачи (PENDING, IN_PROGRESS, COMPLETED)")
    private Status status;

    /**
     * Приоритет задачи.
     * Возможные значения:
     * - LOW: Низкий приоритет.
     * - MEDIUM: Средний приоритет.
     * - HIGH: Высокий приоритет.
     */
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Приоритет задачи (LOW, MEDIUM, HIGH)")
    private Priority priority;

    /**
     * Автор задачи.
     * Пользователь, создавший задачу.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @ApiModelProperty(notes = "Автор задачи")
    private User author;

    /**
     * Исполнитель задачи.
     * Пользователь, ответственный за выполнение задачи.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id")
    @ApiModelProperty(notes = "Исполнитель задачи")
    private User assignee;

    /**
     * Список комментариев, связанных с задачей.
     * Комментарии добавлены другими пользователями.
     */
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ApiModelProperty(notes = "Комментарии задачи")
    private List<Comment> comments;

    /**
     * Дата и время создания задачи.
     * Заполняется автоматически при создании.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @ApiModelProperty(notes = "Дата и время создания задачи")
    private LocalDateTime createdAt;

    /**
     * Дата и время последнего обновления задачи.
     * Обновляется автоматически при каждом изменении задачи.
     */
    @LastModifiedDate
    @Column(nullable = false)
    @ApiModelProperty(notes = "Дата и время последнего обновления задачи")
    private LocalDateTime updatedAt;

    /**
     * Версия задачи.
     */
    @ApiModelProperty(notes = "Версия задачи (для контроля изменений)")
    private Integer version = 1;
}

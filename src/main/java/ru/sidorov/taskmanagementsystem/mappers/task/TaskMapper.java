package ru.sidorov.taskmanagementsystem.mappers.task;

import org.mapstruct.*;
import ru.sidorov.taskmanagementsystem.mappers.user.UserMapper;
import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
import ru.sidorov.taskmanagementsystem.models.entities.Task;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TaskMapper {

    /**
     * map {@link TaskDto} to {@link Task}
     *
     * @param task {@link Task} object
     * @return {@link TaskDto}
     */
    TaskDto toTaskDto(Task task);

    /**
     * map {@link TaskDto} to {@link Task}
     *
     * @param taskDto {@link TaskDto} object
     * @return {@link Task}
     */
    Task toTask(TaskDto taskDto);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    @Mapping(target = "comments", ignore = true)
    void updateFromTaskDto(TaskDto taskDto, @MappingTarget Task task);
}

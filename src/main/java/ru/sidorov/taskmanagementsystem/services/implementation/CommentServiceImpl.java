package ru.sidorov.taskmanagementsystem.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sidorov.taskmanagementsystem.mappers.comment.CommentMapper;
import ru.sidorov.taskmanagementsystem.mappers.user.UserMapper;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentDto;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentSaveDto;
import ru.sidorov.taskmanagementsystem.models.entities.Comment;
import ru.sidorov.taskmanagementsystem.models.entities.Task;
import ru.sidorov.taskmanagementsystem.models.entities.User;
import ru.sidorov.taskmanagementsystem.models.exception.NotFoundTaskException;
import ru.sidorov.taskmanagementsystem.models.exception.NotFoundUserException;
import ru.sidorov.taskmanagementsystem.repositories.CommentRepository;
import ru.sidorov.taskmanagementsystem.repositories.TaskRepository;
import ru.sidorov.taskmanagementsystem.services.abstracts.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public CommentDto saveComment(CommentSaveDto commentSaveDto, final User user) {
        if (user == null) {
            throw new NotFoundUserException();
        }

        Task task = taskRepository.findById(commentSaveDto.getTask().getId()).orElseThrow(NotFoundTaskException::new);
        if (!task.getAssignee().getId().equals(user.getId()) && !user.getRole().getName().equals("ROLE_ADMIN")) {
            throw new IllegalArgumentException("Only the admin or task assignee can comment on a task");
        }

        Comment comment = commentMapper.toComment(commentSaveDto);
        comment.setAuthor(user);

        comment.setId(null);

        Comment saveComment = commentRepository.save(comment);

        return commentMapper.toCommentDto(saveComment);
    }

    @Override
    public List<CommentDto> getComments(Integer taskId) {
        return commentRepository.findByTaskId(taskId).stream()
                .map(commentMapper::toCommentDto)
                .toList();
    }
}

package ru.sidorov.taskmanagementsystem.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sidorov.taskmanagementsystem.factories.TestDataFactory;
import ru.sidorov.taskmanagementsystem.mappers.comment.CommentMapper;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentDto;
import ru.sidorov.taskmanagementsystem.models.dto.comment.CommentSaveDto;
import ru.sidorov.taskmanagementsystem.models.entities.Comment;
import ru.sidorov.taskmanagementsystem.models.entities.Task;
import ru.sidorov.taskmanagementsystem.models.entities.User;
import ru.sidorov.taskmanagementsystem.models.exception.NotFoundUserException;
import ru.sidorov.taskmanagementsystem.repositories.CommentRepository;
import ru.sidorov.taskmanagementsystem.repositories.TaskRepository;
import ru.sidorov.taskmanagementsystem.services.implementation.CommentServiceImpl;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private CommentRepository commentRepository;

    private CommentSaveDto commentSaveDto;
    private CommentDto commentDto;
    private Comment comment;
    private Task task;
    private User user;

    @BeforeEach
    void setUp() {
        commentSaveDto = TestDataFactory.createCommentSaveDto();
        commentDto = TestDataFactory.createCommentDto();
        comment = TestDataFactory.createComment();
        task = comment.getTask();
        user = comment.getAuthor();

    }

    @Test
    void testSaveCommentNullUser() {
        NotFoundUserException exception = Assertions.assertThrows(NotFoundUserException.class, () -> commentService.saveComment(commentSaveDto, null));

        Assertions.assertEquals("Пользователь не найден",exception.getMessage());

        Mockito.verify(commentRepository, Mockito.never()).save(Mockito.any(Comment.class));
    }


    @Test
    void testSaveComment() {

        Mockito.when(taskRepository.findById(commentSaveDto.getTask().getId())).thenReturn(Optional.ofNullable(task));
        Mockito.when(commentMapper.toComment(commentSaveDto)).thenReturn(comment);
        Mockito.when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);
        Mockito.when(commentMapper.toCommentDto(Mockito.any(Comment.class))).thenReturn(commentDto);

        CommentDto saveComment = commentService.saveComment(commentSaveDto, user);

        Mockito.verify(commentRepository, Mockito.times(1)).save(comment);
        Assertions.assertEquals(commentDto, saveComment);
    }
}

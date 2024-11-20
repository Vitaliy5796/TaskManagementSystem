//package ru.sidorov.taskmanagementsystem.controllers;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import ru.sidorov.taskmanagementsystem.config.jwt.JwtUtils;
//import ru.sidorov.taskmanagementsystem.models.dto.task.TaskDto;
//import ru.sidorov.taskmanagementsystem.services.abstracts.TaskService;
//
//@SpringBootTest
//public class AdminControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private TaskService taskService;
//    @Mock
//    private JwtUtils jwtUtils;
//    @InjectMocks
//    private AdminController adminController;
//
//    @BeforeEach
//    public void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
//    }
//
//    @Test
//    public void testCreateTask() throws Exception {
//
//        TaskDto taskDto = new TaskDto();
//        taskDto.setTitle("Test Task");
//        taskDto.setDescription("Test Description");
//        Integer assigneeId = 1;
//        String token = "valid-token";
//
//        // Мокирование поведения
//        when(jwtUtils.getTokenFromRequest(any())).thenReturn(token);
//        when(jwtUtils.getUserFromToken(token)).thenReturn("user");
//        when(taskService.save(eq(taskDto), eq("user"), eq(assigneeId)))
//                .thenReturn(taskDto);
//
//        // Выполнение запроса и проверка результата
//        mockMvc.perform(post("/api/task")
//                        .param("assigneeId", assigneeId.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{ \"name\": \"Test Task\", \"description\": \"Test Description\" }"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.name").value("Test Task"))
//                .andExpect(jsonPath("$.data.description").value("Test Description"));
//
//        // Проверка взаимодействия с зависимыми сервисами
//        verify(taskService).save(eq(taskDto), eq("user"), eq(assigneeId));
//    }
//}

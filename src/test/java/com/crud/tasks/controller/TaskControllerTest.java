package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;


    @Test
    void getTasks() throws Exception{
        //Given
        Task task = new Task(1L, "test title", "test content");
        when(dbService.getAllTasks()).thenReturn(List.of(task));
        when(taskMapper.mapToTaskDtoList(List.of(task))).thenReturn(List.of(
                new TaskDto(task.getId(), task.getTitle(), task.getContent())));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("test title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("test content")));
    }

    @Test
    void getTask() throws Exception {
        Task task = new Task(1,"test title", "test content");
        when(dbService.getTaskById(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class)))
                .thenReturn(new TaskDto(task.getId(), task.getTitle(), task.getContent()));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test content")));
    }

    @Test
    void deleteTask() throws Exception{
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void updateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");
        Task task = new Task(1L, "test title", "test content");
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        Gson gson = new Gson();
        String msg = gson.toJson(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(msg))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("test content")));
    }

    @Test
    void createTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");
        Gson gson = new Gson();
        String msg = gson.toJson(taskDto);
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(msg))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }
}
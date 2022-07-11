package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/tasks")
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;

    @GetMapping
    public List<TaskDto> getTasks () {
        List<Task> task = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(task);
    }

    @GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {
        Task task = service.getTaskById(taskId);
        return taskMapper.mapToTaskDto(task);
    }

    @DeleteMapping
    public void deleteTask (Long taskId) {

    }

    @PutMapping
    public TaskDto updateTask (@RequestBody TaskDto task) {
        return new TaskDto(1L, "test title", "test content");
    }

    @PostMapping
    public void createTask (@RequestBody TaskDto task) {

    }
}

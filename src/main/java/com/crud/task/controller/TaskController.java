package com.crud.task.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/tasks")
public class TaskController {
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public List<TaskDto> getTasks () {
        return new ArrayList<>();
    }

    @GetMapping
    public TaskDto getTask(Long taskId) {
        return new TaskDto(1L, "test title", "test content");
    }

    @DeleteMapping
    public void deleteTask (Long taskId) {

    }

    @PutMapping
    public TaskDto updateTask (TaskDto task) {
        return new TaskDto(1L, "test title", "test content");
    }

    @PostMapping
    public void createTask (TaskDto task) {

    }
}

package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DbServiceTest {

    @Autowired
    DbService dbService;

    @Test
    void getAllTasks() {
        //Given
        Task task = new Task(1L, "test title", "test content");

        //When
        dbService.saveTask(task);

        //Then
        Assertions.assertTrue(dbService.getAllTasks().size()!=0);

        //Clean Up
        dbService.deleteAll();
    }

    @Test
    void getTaskById() {
        //Given
        Task task = new Task(1L, "test title", "test content");
        Task savedTask = dbService.saveTask(task);
        Task fromDb = null;

        // When
        try {
            fromDb = dbService.getTaskById(savedTask.getId());
        } catch (TaskNotFoundException e) {
            System.out.println("No task with given ID");
        }
        //Then
        Assertions.assertEquals("test title", fromDb.getTitle());
        Assertions.assertEquals("test content", fromDb.getContent());

        //Clean Up
        dbService.deleteAll();

    }

    @Test
    void saveTask() {
        //Given
        Task task = new Task(1L, "test title", "test content");

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        Assertions.assertEquals("test title", savedTask.getTitle());
        Assertions.assertEquals("test content", savedTask.getContent());

        //Clean Up
        dbService.deleteAll();
    }

    @Test
    void deleteTaskById() {
        //Given
        Task task = new Task(1L, "test title", "test content");
        Task savedTask = dbService.saveTask(task);

        //When
        dbService.deleteTaskById(savedTask.getId());

        //Then
        Assertions.assertTrue(dbService.getAllTasks().size()==0);

        //Clean Up
        dbService.deleteAll();

    }
}
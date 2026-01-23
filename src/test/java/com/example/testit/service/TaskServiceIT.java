package com.example.testit.service;

import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Task;
import com.example.testit.model.User;
import com.example.testit.repository.TaskRepository;
import com.example.testit.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceIT {
    TaskService taskService;
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    @Test
    void test() {
        User user = new User();
        user.setUsername("admin");
        userRepository.save(user);

        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("This is a test task");
        //task.setId(1L);
        task.setAssignedUser(user);

        taskRepository.save(task);

        Assertions.assertTrue(taskRepository.findById(task.getId()).isPresent());
    }

}
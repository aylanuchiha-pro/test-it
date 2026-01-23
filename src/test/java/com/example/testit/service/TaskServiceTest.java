package com.example.testit.service;

import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Task;
import com.example.testit.model.User;
import com.example.testit.repository.TaskRepository;
import com.example.testit.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;


class TaskServiceTest {

    TaskService taskService;

    TaskRepository taskRepository;
    UserRepository userRepository;
    MailService mailService;

    @BeforeEach
    void setUp() {

        userRepository = Mockito.mock(UserRepository.class);
        mailService = Mockito.mock(MailService.class);
        taskRepository = Mockito.mock(TaskRepository.class);
        taskService = new TaskService(taskRepository, userRepository, mailService);

    }

    @Test
    public void test1() {

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(new User("Requester")));
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(new User("Assigned")));
        taskService.createTask("aylan et meriem", "bg", 1L, 2L);

        Mockito.verify(taskRepository).save(Mockito.any(Task.class));

        //Assertions.assertThat(taskRepository.findById(1L)).isPresent();

    }
}
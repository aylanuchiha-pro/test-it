package com.example.testit.repository;

import com.example.testit.model.Task;
import com.example.testit.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskRepositoryTestIT {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void test1() {
        Task task = new Task();
        task.setTitle("Aylan");
        task.setDescription("This is a test task");
        task.setId(1L);

        User user = new User();
        user.setUsername("aylanuchiha");
        task = taskRepository.getById(1L);
        Task savedTask = taskRepository.save(task);

        //Assertions.assertNotNull(savedTask.getId());
        Assertions.assertTrue(savedTask.getId().equals(1L));
        Assertions.assertTrue(savedTask.getTitle().equals("Aylan"));
        Assertions.assertTrue(savedTask.getDescription().equals("This is a test task"));
    }
}
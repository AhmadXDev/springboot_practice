package com.elm.learning2.service;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    public String getAllTasks() {
        return "Tasks from service layer";
    }
}

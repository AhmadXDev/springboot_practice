package com.elm.learning2;

import com.elm.learning2.dto.TaskRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Learning2Application {

    public static void main(String[] args) {
        SpringApplication.run(Learning2Application.class, args);
        TaskRequest t = new TaskRequest();
        t.getDescription();
    }

}

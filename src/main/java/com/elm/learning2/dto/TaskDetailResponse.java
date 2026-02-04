package com.elm.learning2.dto;

import com.elm.learning2.model.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskDetailResponse {

    private final long id;
    private final String title;
    private final String description;
    private final boolean completed;


    public static TaskDetailResponse from(Task task){
        return new TaskDetailResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted()
        );
    }


}

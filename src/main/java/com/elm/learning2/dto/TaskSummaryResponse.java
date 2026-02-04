package com.elm.learning2.dto;

import com.elm.learning2.model.Task;
import lombok.Getter;

@Getter
public class TaskSummaryResponse {

    private final Long id;
    private final String title;
    private final boolean completed;

    public TaskSummaryResponse(Long id, String title, boolean completed){
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public static TaskSummaryResponse from(Task task){
        return new TaskSummaryResponse(
                task.getId(),
                task.getTitle(),
                task.isCompleted()
        );

    }

}

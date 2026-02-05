package com.elm.learning2.model;

import com.elm.learning2.model.base.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean completed;

    protected Task(){
    }

    public Task(Long id, String title, String description, boolean completed){
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public void markCompleted(boolean completed) {
        this.completed = completed;
    }
}

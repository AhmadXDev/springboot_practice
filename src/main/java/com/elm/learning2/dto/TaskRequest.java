package com.elm.learning2.dto;


import jakarta.validation.constraints.NotBlank;

public class TaskRequest {


    @NotBlank
    private String title;
    private String description;

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }


}

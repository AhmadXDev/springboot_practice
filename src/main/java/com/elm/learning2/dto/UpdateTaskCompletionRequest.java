package com.elm.learning2.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UpdateTaskCompletionRequest {

    @NotNull
    private Boolean completed;

}

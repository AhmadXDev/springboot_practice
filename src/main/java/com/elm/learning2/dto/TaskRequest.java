package com.elm.learning2.dto;


import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    @NotBlank
    private String title;
    private String description;

}

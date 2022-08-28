package com.mathiezelat.portfolio.Dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class dtoProject {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String date;
    @NotBlank
    private String url;
    @NotBlank
    private String img;
}

package com.mathiezelat.portfolio.Dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class dtoExperience {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String company;
    @NotBlank
    private String period;
    @NotBlank
    private String position;
    @NotBlank
    private String logo;
}

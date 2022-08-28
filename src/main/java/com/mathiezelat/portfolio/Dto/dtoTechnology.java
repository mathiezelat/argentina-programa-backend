package com.mathiezelat.portfolio.Dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class dtoTechnology {
    @NotBlank
    private String name;
    @NotBlank
    private int percent;
}

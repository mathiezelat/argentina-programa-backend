package com.mathiezelat.portfolio.Dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class dtoEducation {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String institution;
    @NotBlank
    private String period;
    @NotBlank
    private String logo;
}

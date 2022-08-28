package com.mathiezelat.portfolio.Dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class dtoPerson {
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String dateOfBirth;
    @NotBlank
    private String nationality;
    @NotBlank
    private String email;
    @NotBlank
    private String aboutMe;
    @NotBlank
    private String location;
    @NotBlank
    private String occupation;
    @NotBlank
    private String coverImage;
    @NotBlank
    private String profilePicture;
}

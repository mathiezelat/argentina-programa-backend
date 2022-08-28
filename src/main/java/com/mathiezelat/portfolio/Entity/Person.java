package com.mathiezelat.portfolio.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String name;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String lastName;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String dateOfBirth;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String nationality;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String email;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String aboutMe;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String occupation;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String coverImage;

    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String profilePicture;

}

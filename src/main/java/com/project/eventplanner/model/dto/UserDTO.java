package com.project.eventplanner.model.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank(message = "Inserire nome utente")
    private String username;

    @NotBlank(message = "Inserire password")
    private String password;

    public UserDTO() {
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

package com.behrouztakhti.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * This class is used in User-login process as request DTO;
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
public class UserLoginRequestDTO {

    @Schema(description = "username", example = "admin")
    @NotBlank(message = "NotBlank.username")
    private String username;

    @Schema(description = "password", example = "qaz@123")
    @NotBlank(message = "NotBlank.password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

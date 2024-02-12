package com.behrouztakhti.security.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;


/**
 * This class is used in User-signup process as request DTO;
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
public class UserRegisterRequestDTO {

    @Schema(description = "user name", example = "adminName")
    @NotBlank(message = "NotBlank.name")
    private String name;

    @Schema(description = "user family", example = "adminFamily")
    @NotBlank(message = "NotBlank.family")
    private String family;

    @Schema(description = "username", example = "admin")
    @NotBlank(message = "NotBlank.username")
    private String username;

    @Schema(description = "password", example = "qaz@123")
    @NotBlank(message = "NotBlank.password")
    private String password;


    @ArraySchema(schema = @Schema(description = "roles for user", example = "ADMIN"))
    @NotNull(message = "NotNull.type")
    private List<String> roles;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

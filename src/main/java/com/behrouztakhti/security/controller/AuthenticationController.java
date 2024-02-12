package com.behrouztakhti.security.controller;

import com.behrouztakhti.security.service.AuthenticationService;
import com.behrouztakhti.security.dto.UserLoginRequestDTO;
import com.behrouztakhti.security.dto.UserRegisterRequestDTO;
import com.behrouztakhti.security.dto.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * This controller is responsible for user authentication process such as register, login and ...
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService authenticationSrv;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationSrv = authenticationService;
    }



    /**
     * this method is responsible for user registration process.
     * @param  registerRequestDTO A request DTO.
     * @return GenericResponse a generic response of application.
     */
    @Operation(summary = "user registration process")
    @PostMapping(value ="/register")
    public GenericResponse register(@RequestBody @Validated UserRegisterRequestDTO registerRequestDTO){
        return authenticationSrv.register(registerRequestDTO);
    }


    /**
     * this method is responsible for user login process.
     * @param  loginRequestDTO A request DTO.
     * @return GenericResponse a generic response of application.
     */
    @Operation(summary = "user login process")
    @PostMapping(value ="/login")
    public GenericResponse login(@RequestBody @Validated UserLoginRequestDTO loginRequestDTO){
        return authenticationSrv.login(loginRequestDTO);
    }

}

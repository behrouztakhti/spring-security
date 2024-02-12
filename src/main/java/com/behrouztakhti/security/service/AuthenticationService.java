package com.behrouztakhti.security.service;


import com.behrouztakhti.security.dto.UserLoginRequestDTO;
import com.behrouztakhti.security.dto.UserRegisterRequestDTO;
import com.behrouztakhti.security.dto.GenericResponse;


/**
 * This interface is responsible for user authentication process such as register, login and ...
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
public interface AuthenticationService {


    /**
     * this method is responsible for user registration process.
     * @param  registerRequestDTO A request DTO.
     * @return GenericResponse a generic response of application.
     */
    GenericResponse register(UserRegisterRequestDTO registerRequestDTO);


    /**
     * this method is responsible for user login process.
     * @param  loginRequestDTO A request DTO.
     * @return GenericResponse a generic response of application.
     */
    GenericResponse login(UserLoginRequestDTO loginRequestDTO);
}

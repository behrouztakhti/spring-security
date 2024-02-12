package com.behrouztakhti.security.service.impl;

import com.behrouztakhti.security.exception.BadCredentialException;
import com.behrouztakhti.security.exception.RoleNotFoundException;
import com.behrouztakhti.security.exception.UserAlreadyHasAccountException;
import com.behrouztakhti.security.repository.RoleRepository;
import com.behrouztakhti.security.domain.Role;
import com.behrouztakhti.security.dto.UserLoginRequestDTO;
import com.behrouztakhti.security.dto.UserRegisterRequestDTO;
import com.behrouztakhti.security.config.jwt.JwtService;
import com.behrouztakhti.security.domain.Tokens;
import com.behrouztakhti.security.domain.User;
import com.behrouztakhti.security.dto.GenericResponse;
import com.behrouztakhti.security.repository.TokenRepository;
import com.behrouztakhti.security.repository.UserRepository;
import com.behrouztakhti.security.service.AuthenticationService;
import com.behrouztakhti.security.types.ResponseStatusEnum;
import com.behrouztakhti.security.types.TokenType;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * This interface is responsible for user authentication process such as register, login and ...
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 * @see AuthenticationService
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepo;
    private RoleRepository roleRepo;
    private JwtService jwtSrv;
    private TokenRepository tokenRepo;
    private AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepo, RoleRepository roleRepo,
                                     JwtService jwtSrv, TokenRepository tokenRepo, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.jwtSrv = jwtSrv;
        this.tokenRepo = tokenRepo;
        this.roleRepo = roleRepo;
        this.authenticationManager = authenticationManager;
    }


    /**
     * this method is responsible for user registration process.
     * @param  registerRequestDTO A request DTO.
     * @return GenericResponse a generic response of application.
     */
    @Override
    @Transactional
    public GenericResponse register(UserRegisterRequestDTO registerRequestDTO){
        userRepo.findByUsername(registerRequestDTO.getUsername())
                .ifPresent(users -> {
                    throw new UserAlreadyHasAccountException("User Already Has Account");
                });
        List<Role> roles = registerRequestDTO.getRoles().stream()
                .map(item -> roleRepo.findByName(item)
                        .orElseThrow(() -> new RoleNotFoundException("Role not found: " + item)))
                .toList();
        // Create user
        var userForSave = new User();
        BeanUtils.copyProperties(registerRequestDTO, userForSave, "password");
        userForSave.setRoles(roles);
        userForSave.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        var savedUser = userRepo.save(userForSave);
        String jwtToken = jwtSrv.generateToken(savedUser);
        saveUserToken(savedUser, jwtToken);
        return new GenericResponse(HttpStatus.OK.value(), ResponseStatusEnum.SUCCESS.name(), ResponseStatusEnum.SUCCESS.toMessage(), jwtToken);
    }



    /**
     * this method is responsible for user login process.
     * @param  loginRequestDTO A request DTO.
     * @return GenericResponse a generic response of application.
     */
    @Override
    public GenericResponse login(UserLoginRequestDTO loginRequestDTO) {
        authenticate(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        var user = userRepo.findByUsername(loginRequestDTO.getUsername()).orElseThrow();
        tokenRepo.deleteAllByUserId(user.getId());
        var jwtToken = jwtSrv.generateToken(user);
        saveUserToken(user, jwtToken);
        return new GenericResponse(HttpStatus.OK.value(), ResponseStatusEnum.SUCCESS.name(), ResponseStatusEnum.SUCCESS.toMessage(), jwtToken);
    }


    private void authenticate(String mobileNumber, String password) {
        try {
            Authentication result = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mobileNumber, password));
            SecurityContextHolder.getContext().setAuthentication(result);
        }catch (BadCredentialsException e) {
            throw new BadCredentialException("Wrong username or password");
        }
    }


    /**
     * this private method is called in  both signup and login method and is responsible for save UserToken.
     * @param  user HttpServletRequest.
     * @param  jwtToken A request DTO.
     */
    private void saveUserToken(User user, String jwtToken){
        var tokenForSave = new Tokens();
        tokenForSave.setUser(user);
        tokenForSave.setToken(jwtToken);
        tokenForSave.setTokenType(TokenType.BEARER);
        tokenRepo.save(tokenForSave);
    }
}

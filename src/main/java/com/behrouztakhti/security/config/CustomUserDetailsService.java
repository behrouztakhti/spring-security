package com.behrouztakhti.security.config;

import com.behrouztakhti.security.domain.Role;
import com.behrouztakhti.security.domain.User;
import com.behrouztakhti.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * UserDetailsService is used by DaoAuthenticationProvider for retrieving a username and other attributes for authenticating with username.
 * I've customized it and bind it to UserRepository.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 * @see ApplicationConfig
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;


    /**
     * this method gives username and fetch user(if exists) from database and returns UserDetails.
     * @param  username the username of user that we give it from client.
     * @return systemUser
     * @see SecurityConfig
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User userInDB;
            Optional<User> retVal = userRepo.findByUsername(username);
            if (retVal.isPresent()){
                userInDB = retVal.get();
            }else {
                throw new UsernameNotFoundException("user not found !");
            }
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;
            SecurityUser securityUser = new SecurityUser(
                    username,
                    userInDB.getPassword(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(userInDB));
            securityUser.setId(userInDB.getId());
            securityUser.setName(userInDB.getName());
            securityUser.setFamily(userInDB.getFamily());
            securityUser.setFullName(userInDB.getName().concat(" ").concat(userInDB.getFamily()));
            securityUser.setUsername(userInDB.getUsername());
            return securityUser;
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("error", e);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    private List<SimpleGrantedAuthority> getAuthorities(User user) {
        var authorities = new ArrayList<SimpleGrantedAuthority>();
        for (Role role: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            authorities.addAll(role.getPermissions().stream().map(p -> new SimpleGrantedAuthority(p.getName())).toList());}
        return authorities;
    }


}

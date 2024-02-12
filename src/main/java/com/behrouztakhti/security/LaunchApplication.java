package com.behrouztakhti.security;

import com.behrouztakhti.security.repository.PermissionRepository;
import com.behrouztakhti.security.repository.RoleRepository;
import com.behrouztakhti.security.service.AuthenticationService;
import com.behrouztakhti.security.domain.Permission;
import com.behrouztakhti.security.domain.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;

/**
 * This class is responsible for application startup.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
@SpringBootApplication()
public class LaunchApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaunchApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LaunchApplication.class, args);
        LOGGER.info("spring-security application started successfully !");
    }


    /**
     * We use an CommandLineRunner to load our initial data on server start.
     * @author behrouz.takhti@gmail.com
     * @param permissionRepo PermissionRepository
     * @param roleRepo RoleRepository
     * @param authenticationSrv AuthenticationService
     */
    @Bean
    public CommandLineRunner commandLineRunner(PermissionRepository permissionRepo, RoleRepository roleRepo,
                                               AuthenticationService authenticationSrv){
        return args -> {
            Permission ADMIN_CREATE = permissionRepo.save(new Permission("ADMIN_CREATE","privilege 'create' for admin"));
            Permission ADMIN_READ = permissionRepo.save(new Permission("ADMIN_READ","privilege 'read' for admin"));
            Permission ADMIN_UPDATE = permissionRepo.save(new Permission("ADMIN_UPDATE","privilege 'update' for admin"));
            Permission ADMIN_DELETE = permissionRepo.save(new Permission("ADMIN_DELETE","privilege 'delete' for admin"));

            Permission MANAGER_CREATE = permissionRepo.save(new Permission("MANAGER_CREATE","privilege 'create' for manager"));
            Permission MANAGER_READ = permissionRepo.save(new Permission("MANAGER_READ","privilege 'read' for manager"));
            Permission MANAGER_UPDATE = permissionRepo.save(new Permission("MANAGER_UPDATE","privilege 'update' for manager"));
            Permission MANAGER_DELETE = permissionRepo.save(new Permission("MANAGER_DELETE","privilege 'delete' for manager"));

            roleRepo.save(new Role("ADMIN", "ADMIN role",
                    Arrays.asList(ADMIN_CREATE, ADMIN_READ, ADMIN_UPDATE, ADMIN_DELETE
                    )));
            roleRepo.save(new Role("MANAGER", "MANAGER role",
                    Arrays.asList(MANAGER_CREATE, MANAGER_READ, MANAGER_UPDATE, MANAGER_DELETE)
            ));
        };
    }





}

package com.behrouztakhti.security.service.impl;

import com.behrouztakhti.security.service.ManagerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


@Service
public class ManagerServiceImpl implements ManagerService {

    /**
     * Only users who have the privilege MANAGER_CREATE are able to execute this method.
     * @author behrouz.takhti@gmail.com
     */
    @Override
    @PreAuthorize("hasAuthority('MANAGER_CREATE')")
    public String add() {
        return "ROLE_ADMIN,ROLE_MANAGER::MANAGER_CREATE";
    }


    /**
     * Only users who have the privilege MANAGER_READ are able to execute this method.
     * @author behrouz.takhti@gmail.com
     */
    @Override
    @PreAuthorize("hasAuthority('MANAGER_READ')")
    public String read() {
        return "ROLE_ADMIN,ROLE_MANAGER::MANAGER_READ";
    }


    /**
     * Only users who have the privilege MANAGER_UPDATE are able to execute this method.
     * @author behrouz.takhti@gmail.com
     */
    @Override
    @PreAuthorize("hasAuthority('MANAGER_UPDATE')")
    public String update() {
        return "ROLE_ADMIN,ROLE_MANAGER::MANAGER_UPDATE";
    }


    /**
     * Only users who have the privilege MANAGER_DELETE are able to execute this method.
     * @author behrouz.takhti@gmail.com
     */
    @Override
    @PreAuthorize("hasAuthority('MANAGER_DELETE')")
    public String delete() {
        return "ROLE_ADMIN,ROLE_MANAGER::MANAGER_DELETE";
    }

}

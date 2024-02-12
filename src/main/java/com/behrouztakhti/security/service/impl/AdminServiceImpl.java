package com.behrouztakhti.security.service.impl;

import com.behrouztakhti.security.service.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    /**
     * Only users who have the privilege ADMIN_CREATE are able to execute this method.
     * @author behrouz.takhti@gmail.com
     */
    @Override
    @PreAuthorize("hasAuthority('ADMIN_CREATE')")
    public String add() {
        return "ROLE_ADMIN::ADMIN_CREATE";
    }


    /**
     * Only users who have the privilege ADMIN_READ are able to execute this method.
     * @author behrouz.takhti@gmail.com
     */
    @Override
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    public String read() {
        return "ROLE_ADMIN::ADMIN_READ";
    }


    /**
     * Only users who have the privilege ADMIN_UPDATE are able to execute this method.
     * @author behrouz.takhti@gmail.com
     */
    @Override
    @PreAuthorize("hasAuthority('ADMIN_UPDATE')")
    public String update() {
        return "ROLE_ADMIN::ADMIN_UPDATE";
    }


    /**
     * Only users who have the privilege ADMIN_DELETE are able to execute this method.
     * @author behrouz.takhti@gmail.com
     */
    @Override
    @PreAuthorize("hasAuthority('ADMIN_DELETE')")
    public String delete() {
        return "ROLE_ADMIN :: ADMIN_DELETE";
    }
}

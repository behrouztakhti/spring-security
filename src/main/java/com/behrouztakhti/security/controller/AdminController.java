package com.behrouztakhti.security.controller;


import com.behrouztakhti.security.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Only users who have the role ROLE_ADMIN are able to execute this class's endpoints.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    /**
     * Only users who have the role ROLE_ADMIN are able to execute this endpoint.
     * @author behrouz.takhti@gmail.com
     */
    @GetMapping(value ="/add")
    public String add() throws Exception{
        return adminService.add();
    }

    /**
     * Only users who have the role ROLE_ADMIN are able to execute this endpoint.
     * @author behrouz.takhti@gmail.com
     */
    @GetMapping(value ="/read")
    public String read() throws Exception{
        return adminService.read();
    }

    /**
     * Only users who have the role ROLE_ADMIN are able to execute this endpoint.
     * @author behrouz.takhti@gmail.com
     */
    @GetMapping(value ="/update")
    public String update() throws Exception{
        return adminService.update();
    }

    /**
     * Only users who have the role ROLE_ADMIN are able to execute this endpoint.
     * @author behrouz.takhti@gmail.com
     */
    @GetMapping(value ="/delete")
    public String delete() throws Exception{
        return adminService.delete();
    }
}

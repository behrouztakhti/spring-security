package com.behrouztakhti.security.controller;


import com.behrouztakhti.security.service.ManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * (according to the roleHierarchy): Only users who have one of ROLE_ADMIN or ROLE_MANAGER roles can execute
 * the endpoints of this class's endpoints.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }


    /**
     * (according to the roleHierarchy): Only users who have one of ROLE_ADMIN or ROLE_MANAGER roles can execute this endpoint.
     * @author behrouz.takhti@gmail.com
     */
    @GetMapping(value ="/add")
    public String add() throws Exception{
        return managerService.add();
    }

    /**
     * (according to the roleHierarchy): Only users who have one of ROLE_ADMIN or ROLE_MANAGER roles can execute this endpoint.
     * @author behrouz.takhti@gmail.com
     */
    @GetMapping(value ="/read")
    public String read() throws Exception{
        return managerService.read();
    }

    /**
     * (according to the roleHierarchy): Only users who have one of ROLE_ADMIN or ROLE_MANAGER roles can execute this endpoint.
     * @author behrouz.takhti@gmail.com
     */
    @GetMapping(value ="/update")
    public String update() throws Exception{
        return managerService.update();
    }

    /**
     * (according to the roleHierarchy): Only users who have one of ROLE_ADMIN or ROLE_MANAGER roles can execute this endpoint.
     * @author behrouz.takhti@gmail.com
     */
    @GetMapping(value ="/delete")
    public String delete() throws Exception{
        return managerService.delete();
    }
}

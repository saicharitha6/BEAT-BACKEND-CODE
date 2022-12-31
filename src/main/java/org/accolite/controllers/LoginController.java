package org.accolite.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.accolite.buisnesslogic.Access;
import org.accolite.buisnesslogic.RolesGroupComponent;
import org.accolite.buisnesslogic.LoginComponent;
import org.accolite.pojo.LoginDetails;
import org.accolite.pojo.SessionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.ObjectFactory;

@RestController
@Slf4j
@RequestMapping(value = PathConstants.ApiPath)
public class LoginController {
    @Autowired
    private LoginComponent loginComponent;
    @Autowired
    private RolesGroupComponent rolesGroupComponent;
    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @PostMapping(value = PathConstants.loginPath, consumes = "application/json", produces = "plain/text")
    private ResponseEntity<String> login(@RequestBody LoginDetails loginDetailsFromClient) {
        HttpSession session = httpSessionFactory.getObject();
        boolean isValidOrInvalidUser = loginComponent.checkLoginDetailsFromClient(loginDetailsFromClient, session);
        if(isValidOrInvalidUser){
            if(rolesGroupComponent.setAccess(session)){
                return ResponseEntity.ok().body("User logged in = "+((SessionDetails) session.getAttribute("sessionDetailsInSession")).toString());
            }
            else {
                return ResponseEntity.internalServerError().body("Access is unable to set for this session");
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = PathConstants.accessPath,produces = "plain/text")
    private  ResponseEntity<String> access(){
        HttpSession session = httpSessionFactory.getObject();
        Access access = Access.getInstance();
        if(access.access == null){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok().body(access.access);
        }
    }
}

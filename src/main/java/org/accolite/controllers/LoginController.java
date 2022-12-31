package org.accolite.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.accolite.buisnesslogic.Access;
import org.accolite.buisnesslogic.RolesGroupComponent;
import org.accolite.buisnesslogic.LoginComponent;
import org.accolite.pojo.LoginDetails;
import org.accolite.pojo.SessionDetails;
import org.accolite.util.JwtUtil;
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
    RolesGroupComponent rolesGroupComponent;

    @Autowired
    private JwtUtil jwtUtil;
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
    private  ResponseEntity<String> access(@RequestHeader("jwt") String token){
        HttpSession session = httpSessionFactory.getObject();
        SessionDetails sessionDetails = (SessionDetails) session.getAttribute("sessionDetailsInSession");
        try{
            if(jwtUtil.validateToken(token,String.valueOf(sessionDetails.getEmpId()))){
                Access access = Access.getInstance();
                if(access.access == null){
                    return ResponseEntity.notFound().build();
                }
                else {
                    return ResponseEntity.ok().body(access.access);
                }
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
}

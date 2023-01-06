package org.accolite.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.accolite.buisnesslogic.Access;
import org.accolite.buisnesslogic.RolesGroupComponent;
import org.accolite.buisnesslogic.LoginComponent;
import org.accolite.pojo.LoginDetails;
import org.accolite.pojo.LoginResponse;
import org.accolite.pojo.SessionDetails;
import org.accolite.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.ObjectFactory;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
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

    long editorId;

    @PostMapping(value = PathConstants.loginPath, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<LoginResponse> login(@RequestBody LoginDetails loginDetailsFromClient) {
        HttpSession session = httpSessionFactory.getObject();
        boolean isValidOrInvalidUser = loginComponent.checkLoginDetailsFromClient(loginDetailsFromClient, session);
        if(isValidOrInvalidUser){
            if(rolesGroupComponent.setAccess(session)){
                log.debug("User logged in = "+((SessionDetails) session.getAttribute("sessionDetailsInSession")));
                SessionDetails sessionDetails = (SessionDetails)session.getAttribute("sessionDetailsInSession");
                LoginResponse loginResponse = new LoginResponse();
                Access access = Access.getInstance();
                loginResponse.setJwt(sessionDetails.getJwt());
                loginResponse.setEmpId(sessionDetails.getEmpId());
                loginResponse.setAccessId(access.access);
                editorId =  sessionDetails.getEmpId();
                return  ResponseEntity.ok().body(loginResponse);
//                return ResponseEntity.ok().body("User logged in = "+((SessionDetails) session.getAttribute("sessionDetailsInSession")).toString());
            }
            else {
                log.debug("Access is unable to set for this session");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else {
            log.debug("Invalid user details");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    public long getCurrentLoggedInEmpId()
    {
        return editorId;
    }

    @GetMapping(value = PathConstants.accessPath,produces = MediaType.TEXT_PLAIN_VALUE)
    private  ResponseEntity<String> access(@RequestHeader("jwt") String token){
        HttpSession session = httpSessionFactory.getObject();
        SessionDetails sessionDetails = (SessionDetails) session.getAttribute("sessionDetailsInSession");
        try{
            if(jwtUtil.validateToken(token,String.valueOf(sessionDetails.getEmpId()))){
                Access access = Access.getInstance();
                if(access.access == null){
                    log.debug("Access is not defined in current session");
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Access is not defined in current session");
                }
                else {
                    log.debug("Access is retrieved successfully");
                    return ResponseEntity.ok().body(access.access);
                }
            }
            else {
                log.debug("invalid jwt token");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid jwt token");
            }
        }catch (Exception ex){
            log.debug("jjwt throw exception"+ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("jjwt throw exception"+ex);
        }
    }
}

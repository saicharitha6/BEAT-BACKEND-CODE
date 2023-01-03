package org.accolite.buisnesslogic;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.Login;
import org.accolite.pojo.LoginDetails;
import org.accolite.db.services.impl.LoginService;
import org.accolite.pojo.SessionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;


@Slf4j
@Component
public class LoginComponent {
    @Autowired
    private LoginService loginService;
    public boolean checkLoginDetailsFromClient(LoginDetails loginDetailsFromClient, HttpSession session){
        Optional<Login> loginRecordFromDbObj = Optional.ofNullable(loginService.getLoginRecordFromDb(loginDetailsFromClient.getEmail()));
        if(loginRecordFromDbObj.isPresent()){
            Login loginRecordFromDb = loginRecordFromDbObj.get();
            if(loginDetailsFromClient.getEmail().equals(loginRecordFromDb.getEmail()) &&
                    loginDetailsFromClient.getPassword().equals(loginRecordFromDb.getPassword())) {
                SessionDetails sessionDetails = new SessionDetails();
                sessionDetails.setEmpId( loginRecordFromDb.getEmpId());
                sessionDetails.setUserName(loginRecordFromDb.getUsername());
                sessionDetails.setAccessId(loginRecordFromDb.getAccessId());
                SessionDetails sessionDetailsInSession = (SessionDetails) session.getAttribute("sessionDetailsInSession");
                if( sessionDetailsInSession == null){
                    session.setAttribute("sessionDetailsInSession",sessionDetails);
                }
                return true;
            }
            else {
                return false;
            }
        }
        return  false;
    }
}

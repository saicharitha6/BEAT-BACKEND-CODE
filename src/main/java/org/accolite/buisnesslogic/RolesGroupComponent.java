package org.accolite.buisnesslogic;

import jakarta.servlet.http.HttpSession;
import org.accolite.db.entities.RolesGroup;
import org.accolite.db.services.RoleGroupService;
import org.accolite.pojo.SessionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RolesGroupComponent {
    @Autowired
    RoleGroupService roleGroupService;
    public boolean setAccess(HttpSession session){
        Access access = Access.getInstance();
        SessionDetails sessionDetailsInSession = (SessionDetails) session.getAttribute("sessionDetailsInSession");
        if(sessionDetailsInSession == null){
            return false;
        }else{
            RolesGroup rolesGroupRecordFromDb = roleGroupService.getRoleGroupsRecordFromDb(sessionDetailsInSession.getAccessId());
            access.access = rolesGroupRecordFromDb.getAccess();
            return true;
        }
    }

}



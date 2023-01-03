package org.accolite.db.services.impl;


import lombok.extern.slf4j.Slf4j;
import org.accolite.db.entities.RolesGroup;
import org.accolite.db.repo.LoginRepository;
import org.accolite.db.repo.RolesGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.util.Optional;

@Slf4j
@Service
@SessionScope
public class RoleGroupService {
    @Autowired
    private RolesGroupRepository rolesGroupRepo;
    @Autowired
    private LoginRepository loginRepository;

    public RolesGroup getRoleGroupsRecordFromDb( long id){
        Optional<RolesGroup> dbRecordObj = rolesGroupRepo.findById(id);
        return dbRecordObj.orElse(null);
    }
}

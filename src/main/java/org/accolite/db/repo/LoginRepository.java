package org.accolite.db.repo;

import org.accolite.db.entities.EmployeeHistory;
import org.accolite.db.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long>{
    Login findByEmail(String email);
    Optional<Login> findByEmpId(long id);
}

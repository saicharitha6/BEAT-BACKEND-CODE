package org.accolite.db.repo;

import org.accolite.db.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long>{
    Login findByEmail(String email);
}

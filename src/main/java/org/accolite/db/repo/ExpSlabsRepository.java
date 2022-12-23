package org.accolite.db.repo;

import org.accolite.db.entities.ExpSlabs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpSlabsRepository extends JpaRepository<ExpSlabs,Long>{

}

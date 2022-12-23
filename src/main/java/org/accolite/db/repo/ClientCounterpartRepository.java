package org.accolite.db.repo;

import org.accolite.db.entities.ClientCounterpart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCounterpartRepository extends JpaRepository<ClientCounterpart,Long>{
}

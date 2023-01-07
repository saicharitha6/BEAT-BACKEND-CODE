package org.accolite.db.repo;

import org.accolite.db.entities.SlabCharges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlabChargesRepository extends JpaRepository<SlabCharges,Long> {
    List<SlabCharges> findAllByOrgId(long id);
}

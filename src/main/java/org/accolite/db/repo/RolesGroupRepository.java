package org.accolite.db.repo;

import org.accolite.db.entities.RolesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolesGroupRepository extends JpaRepository<RolesGroup,Long> {
}

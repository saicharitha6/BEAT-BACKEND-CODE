package org.accolite.db.repo;

import org.accolite.db.entities.DayType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayTypeRepository extends JpaRepository<DayType,Long> {
}

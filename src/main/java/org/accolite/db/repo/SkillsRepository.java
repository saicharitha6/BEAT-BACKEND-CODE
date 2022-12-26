package org.accolite.db.repo;

import org.accolite.db.entities.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SkillsRepository extends JpaRepository<Skills,Long> {
}

package org.accolite.db.repo;

import org.accolite.db.entities.SkillsAndExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsAndExperienceRepository extends JpaRepository<SkillsAndExperience,Long> {
}

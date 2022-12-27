package org.accolite.db.repo;

import org.accolite.db.entities.TaskProjectMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskProjectMappingRepository extends JpaRepository<TaskProjectMapping,Long> {
}

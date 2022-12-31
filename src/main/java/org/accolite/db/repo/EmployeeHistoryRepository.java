package org.accolite.db.repo;

import org.accolite.db.entities.EmployeeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeHistoryRepository extends JpaRepository<EmployeeHistory,Long>{
    Optional<EmployeeHistory> findByEmpIdAndStatus(long id, boolean status);
    Optional<EmployeeHistory> findByOrganizationIdAndStatus(long organizationId, boolean status);

    Optional<EmployeeHistory> findByOrganizationId(long id);
}

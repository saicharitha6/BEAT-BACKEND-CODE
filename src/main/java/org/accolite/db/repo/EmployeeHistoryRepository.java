package org.accolite.db.repo;

import org.accolite.db.entities.Employee;
import org.accolite.db.entities.EmployeeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeHistoryRepository extends JpaRepository<EmployeeHistory,Long>{
    Optional<EmployeeHistory> findByEmpIdAndStatus(long id, boolean status);
    List<EmployeeHistory> findAllByEmpId(long id);
}

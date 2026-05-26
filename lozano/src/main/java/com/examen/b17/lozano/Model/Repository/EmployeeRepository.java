package com.examen.b17.lozano.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examen.b17.lozano.Model.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}

package com.examen.b17.lozano.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.examen.b17.lozano.Model.Entity.Employee;
import com.examen.b17.lozano.Model.Repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository)
    {
        this.employeeRepository=employeeRepository;
    }
    public List<Employee> getAllEmployees()
    {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Optional<Employee> findById(Integer par_id){
        return employeeRepository.findById(par_id);
    }
    public Employee save(Employee par_employee){
        return employeeRepository.save(par_employee);
    }
    public void delete(Employee par_employee){
        employeeRepository.delete(par_employee);
    }
    
}

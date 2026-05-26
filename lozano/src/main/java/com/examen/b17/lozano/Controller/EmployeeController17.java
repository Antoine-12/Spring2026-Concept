package com.examen.b17.lozano.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examen.b17.lozano.Model.Entity.Employee;
import com.examen.b17.lozano.Service.EmployeeService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController17 {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("listEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("addEmployees")
    public Employee createEmployee(@RequestBody Employee par_Employee) {
        if (par_Employee.getId() != null && par_Employee.getId() > 0
                && employeeService.findById(par_Employee.getId()).isPresent()) {
            return null;
        } else {
            par_Employee.setId(null);
            return employeeService.save(par_Employee);
        }
    }

    @PutMapping("updateEmployees/{par_id}")
    public Employee updateEmployee(@PathVariable("par_id") Integer employeeId, @RequestBody Employee updateEmployee) {
        Optional<Employee> employeeOptional = employeeService.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setNombre(updateEmployee.getNombre());
            employee.setPuesto(updateEmployee.getPuesto());
            employee.setSalario(updateEmployee.getSalario());
            return employeeService.save(employee);
        } else {
            return null;
        }
    }

    @PatchMapping("update_parcial_employee/{par_id}")
    public Employee partialUpdateEmployee(@PathVariable Integer par_id, @RequestBody Map<String, Object> updates) {
        Optional<Employee> employeeOptional = employeeService.findById(par_id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();

            updates.forEach((key, value) -> {
                switch (key) {
                    case "nombre":
                        employee.setNombre((String) value);
                        break;
                    case "puesto":
                        employee.setPuesto((String) value);
                        break;
                    case "salario":
                        employee.setSalario((Double) value);
                        break;
                }
            });
            return employeeService.save(employee);
        } else {
            return null;
        }
    }

    @DeleteMapping("delete_employees/{id}")
    public void deleteEmployee(@PathVariable("id") Integer employeeId) {
        Optional<Employee> employeeOptional = employeeService.findById(employeeId);
        employeeOptional.ifPresent(employeeService::delete);
    }

}


package com.example.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.repository = employeeRepository;
    }

    // Create a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Get employee by ID
    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable String employeeId) {
        return repository.findByEmployeeId(employeeId)
                .orElse(null);
    }

    // Update employee details
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(
            @PathVariable String employeeId,
            @RequestBody Employee employee) {

        Employee existingEmployee = repository
                .findByEmployeeId(employeeId)
                .orElse(null);

        if (existingEmployee == null) {
            return null;
        }

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        return repository.save(existingEmployee);
    }

    // Delete employee by ID
    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        repository.deleteByEmployeeId(employeeId);
        return "Employee deleted successfully";
    }
}

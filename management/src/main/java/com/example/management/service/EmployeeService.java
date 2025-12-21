package com.example.management.service;

import com.example.management.model.Employee;
import com.example.management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository =employeeRepository;
    }
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    public Employee updateEmployee(Long id, Employee updatedEmployee){
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existing.setName(updatedEmployee.getName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setDepartment(updatedEmployee.getDepartment());
        existing.setSalary(updatedEmployee.getSalary());
        return employeeRepository.save(existing);
    }
    public List<Employee>findByDepartment(String department){
        return employeeRepository.findByDepartment(department);

    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
}

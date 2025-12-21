package com.example.management.controller;

import com.example.management.model.Employee;
import com.example.management.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final EmployeeService employeeService;

    public AdminController(EmployeeService employeeService){
        this.employeeService= employeeService;
    }
    @GetMapping("/dashboard")
    public String adminDashboard(){
        return "Admin Access only";
    }
    @GetMapping("/search")
    public List<Employee> searchByDepartment(
            @RequestParam String department
    ){
        return employeeService.findByDepartment(department);
    }
}

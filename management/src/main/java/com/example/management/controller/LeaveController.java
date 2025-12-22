package com.example.management.controller;

import com.example.management.model.Employee;
import com.example.management.model.LeaveRequest;
import com.example.management.model.LeaveStatus;
import com.example.management.service.EmployeeService;
import com.example.management.service.LeaveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {
    private final LeaveService leaveService;
    private final EmployeeService employeeService;

    public LeaveController(LeaveService leaveService, EmployeeService employeeService){
        this.leaveService=leaveService;
        this.employeeService=employeeService;
    }
    //employee applies for leave
    @PostMapping("/apply/{employeeId}")
    public LeaveRequest applyLeave(@PathVariable Long employeeId, @RequestBody LeaveRequest leave){
        Employee employee=employeeService.getEmployeeById(employeeId);
        leave.setEmployee(employee);
        return leaveService.applyLeave(leave);

    }
    //get all leaves for an employee
    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getEmployeeLeave(@PathVariable Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return leaveService.getLeavesByEmployee(employee);
    }

    //admin approve or reject leav
    @PutMapping("/admin/update/{leaveId}")
    public LeaveRequest updateLeaveStatus(@PathVariable Long leaveId, @RequestParam LeaveStatus status){
        return leaveService.updateLeaveStatus(leaveId,status);

    }
    //admin gets all leave requests
    @GetMapping("/admin/all")
    public List<LeaveRequest>getAllLeaves(){
        return leaveService.getAllLeaves();
    }
}

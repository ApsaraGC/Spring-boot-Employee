package com.example.management.service;

import com.example.management.model.Employee;
import com.example.management.model.LeaveRequest;
import com.example.management.model.LeaveStatus;
import com.example.management.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {
    private final LeaveRequestRepository leaveRepository;

    public LeaveService(LeaveRequestRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    //employee applies for leave
    public LeaveRequest applyLeave(LeaveRequest leave) {
        leave.setStatus(LeaveStatus.PENDING);
        return leaveRepository.save(leave);
    }

    //get leave request for an employee
    public List<LeaveRequest> getLeavesByEmployee(Employee employee) {
        return leaveRepository.findByEmployee(employee);

    }

    //admin approves or reject leave
    public LeaveRequest updateLeaveStatus(Long leaveId, LeaveStatus status) {
        LeaveRequest leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus(status);
        return leaveRepository.save(leave);

    }

    //get all leave request(admin)
    public List<LeaveRequest> getAllLeaves() {
        return leaveRepository.findAll();
    }
}



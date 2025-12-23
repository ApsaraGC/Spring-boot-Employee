package com.example.management.service.impl;

import com.example.management.model.Attendance;
import com.example.management.model.Employee;
import com.example.management.repository.AttendanceRepository;
import com.example.management.service.AttendanceService;
import com.example.management.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeService employeeService;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository,
                                 EmployeeService employeeService){
        this.attendanceRepository=attendanceRepository;
        this.employeeService=employeeService;
    }
    public Attendance clockIn(Long employeeId){
        Employee employee=employeeService.getEmployeeById(employeeId);
        LocalDate today=LocalDate.now();
        if (attendanceRepository.findByEmployeeAndDate(employee,today).isPresent()){
            throw new RuntimeException("Employee already clocked in today");
                    }
        Attendance attendance=new Attendance();
        attendance.setEmployee(employee);
        attendance.setDate(today);
        attendance.setClockInTime(LocalDateTime.now());

        return attendanceRepository.save(attendance);
    }
    public Attendance clockOut(Long employeeId){
        Employee employee=employeeService.getEmployeeById(employeeId);
        LocalDate today=LocalDate.now();

        Attendance attendance=attendanceRepository
                .findByEmployeeAndDate(employee, today)
                .orElseThrow(()->new RuntimeException("Clock-in first"));

        if (attendance.getClockOutTime()!=null){
            throw new RuntimeException("Already clocked out");

        }
        attendance.setClockOutTime(LocalDateTime.now());
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAttendanceByEmployee(Long employeeId) {
        Employee employee=employeeService.getEmployeeById(employeeId);

        return attendanceRepository.findByEmployee(employee);

    }

    @Override
    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date);

    }
}

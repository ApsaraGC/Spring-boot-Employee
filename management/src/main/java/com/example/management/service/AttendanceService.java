package com.example.management.service;

import com.example.management.model.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    Attendance clockIn(Long employeeId);
    Attendance clockOut(Long employeeId);

    List<Attendance>getAttendanceByEmployee(Long employeeId);
    List<Attendance>getAttendanceByDate(LocalDate date);
}

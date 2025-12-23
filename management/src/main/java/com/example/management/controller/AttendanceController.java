package com.example.management.controller;

import com.example.management.model.Attendance;
import com.example.management.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService=attendanceService;
    }

    //employee clock in
    @PostMapping("/clock-in/{employeeId}")
    public Attendance clockIn(@PathVariable Long employeeId){
        return attendanceService.clockIn(employeeId);
    }
    //employee clock-out
    @PostMapping("/clock-out/{employeeId}")
    public Attendance clockOut(@PathVariable Long employeeId ){
        return attendanceService.clockOut(employeeId);
    }
    //get attendance by employee
    @GetMapping("/employee/{employeeId}")
    public List<Attendance>getAttendanceByEmployee(@PathVariable Long employeeId){
        return attendanceService.getAttendanceByEmployee(employeeId);

    }
    //admin get attendance by date
    @GetMapping("/date")
    public List<Attendance>getAttendanceByDate(@RequestParam String date){
        return attendanceService.getAttendanceByDate(LocalDate.parse(date));
    }
}

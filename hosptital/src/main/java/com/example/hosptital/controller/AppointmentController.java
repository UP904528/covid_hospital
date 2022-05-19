package com.example.hosptital.controller;

import com.example.hosptital.exception.ResourceNotFoundException;
import com.example.hosptital.model.Appointment;
import com.example.hosptital.model.Doctor;
import com.example.hosptital.model.Patient;
import com.example.hosptital.repository.AppointmentRepository;
import com.example.hosptital.repository.DoctorRepository;
import com.example.hosptital.repository.PatientRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/v1/")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    // create appointment rest api
    @PostMapping("/appointments")
    public Appointment create(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @PutMapping("/appointments/{appointmentId}/doctors/{doctorId}")
    Appointment assignDoctorToAppointment(
            @PathVariable Long appointmentId,
            @PathVariable Long doctorId
    ) {
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        Doctor doctors = doctorRepository.findById(doctorId).get();
        appointment.assignDoctor(doctors);
        return appointmentRepository.save(appointment);
    }

    @PutMapping("/appointments/{appointmentId}/patients/{patientId}")
    Appointment assignPatientToAppointment(
            @PathVariable Long appointmentId,
            @PathVariable Long patientId
    ) {
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        Patient patients = patientRepository.findById(patientId).get();
        appointment.assignPatient(patients);
        return appointmentRepository.save(appointment);
    }




}
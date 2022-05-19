package com.example.hosptital.controller;

import com.example.hosptital.exception.ResourceNotFoundException;
import com.example.hosptital.model.Doctor;
import com.example.hosptital.repository.DoctorRepository;
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
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    //get all Doctors
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // create Doctor rest api
    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // get Doctor by id rest api
    @GetMapping("/doctors/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor does not exist with the following id :" + id));
        return ResponseEntity.ok(doctor);
    }

    // update Doctor rest api
    @PutMapping("/doctors/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails){

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor does not exist with the following id :" + id));
        doctor.setFirstName(doctorDetails.getFirstName());
        doctor.setLastName(doctorDetails.getLastName());
        doctor.setDob(doctorDetails.getDob());
        doctor.setGender(doctorDetails.getGender());
        doctor.setAddress(doctor.getAddress());
        doctor.setPhone(doctorDetails.getPhone());
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return ResponseEntity.ok(updatedDoctor);

    }
    // delete Doctor rest api
    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor does not exist with the following id :" + id));
        doctorRepository.delete(doctor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

package com.example.hosptital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.print.Doc;
import java.util.List;

@Entity
@Table(name = "Appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "app_date")
    private String appDate;

    @Column(name = "app_time")
    private String appTime;

    @Column(name = "room_num")
    private int roomNum;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="doctor_id", referencedColumnName = "id")
    private Doctor doctors;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="patient_id", referencedColumnName = "id")
    private Patient patients;


    public Appointment(){

    }

    public Appointment(String appDate, String appTime, int roomNum, Doctor doctors) {
        this.appDate = appDate;
        this.appTime = appTime;
        this.roomNum = roomNum;
        this.doctors = doctors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppTime() {
        return appTime;
    }

    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public Doctor getDoctors() {
        return doctors;
    }

    public void assignDoctor(Doctor doctors) {
        this.doctors = doctors;
    }

    public Patient getPatients() {
        return patients;
    }

    public void assignPatient(Patient patients) {
        this.patients = patients;
    }
}



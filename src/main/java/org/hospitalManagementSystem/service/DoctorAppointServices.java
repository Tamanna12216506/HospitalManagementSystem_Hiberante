package org.hospitalManagementSystem.service;

import jakarta.persistence.EntityManager;
import org.hospitalManagementSystem.entity.Appointment;
import org.hospitalManagementSystem.entity.Doctor;
import org.hospitalManagementSystem.entity.Patient;
import org.hospitalManagementSystem.util.JPAUtil;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class DoctorAppointServices  {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();

        em.getTransaction().begin();

        // Create Doctor
        Doctor doc = new Doctor();
        doc.setName("Dr. Saniya");
        doc.setSpecialization("General Physician");
        doc.setLicenseNo("LIC-SAN-111");

        // Create Patient
        Patient p = new Patient();
        p.setName("Aayushi");
        p.setDob(LocalDate.of(2003, 5, 12));
        p.setBloodGroup("O+");
        p.setPhone("7777777777");

        // Create Appointment
        Appointment ap = new Appointment();
        ap.setAppointDate(LocalDateTime.now().plusDays(1));
        ap.setStatus("SCHEDULED");
        ap.setReason("Fever");
        ap.setDoctor(doc);
        ap.setPatient(p);

        // Persist graph
        em.persist(p);     // patient first
        em.persist(doc);   // doctor
        em.persist(ap);    // appointment

        em.getTransaction().commit();

        Long apId = ap.getId();
        System.out.println("Created Appointment: " + ap);

        // READ
        Appointment fetched = em.find(Appointment.class, apId);
        System.out.println("Fetched Appointment: " + fetched);

        // UPDATE
        em.getTransaction().begin();
        fetched.setStatus("COMPLETED");
        fetched.setReason("Fever + body ache");
        em.getTransaction().commit();
        System.out.println("Updated Appointment status/reason.");

        // DELETE
        em.getTransaction().begin();
        Appointment del = em.find(Appointment.class, apId);
        em.remove(del);
        em.getTransaction().commit();
        System.out.println("Deleted Appointment.");

        em.close();
        JPAUtil.getEmf().close();
    }
}
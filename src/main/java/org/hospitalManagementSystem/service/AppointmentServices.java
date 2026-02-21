package org.hospitalManagementSystem.service;

import jakarta.persistence.EntityManager;
import org.hospitalManagementSystem.entity.Appointment;
import org.hospitalManagementSystem.entity.Doctor;
import org.hospitalManagementSystem.entity.Patient;
import org.hospitalManagementSystem.entity.Prescription;
import org.hospitalManagementSystem.util.JPAUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class  AppointmentServices{
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();

        em.getTransaction().begin();

        Doctor doc = new Doctor();
        doc.setName("Dr. Meera");
        doc.setSpecialization("Dermatology");
        doc.setLicenseNo("LIC-MEE-009");

        Patient pat = new Patient();
        pat.setName("Neel");
        pat.setDob(LocalDate.of(2002, 2, 2));
        pat.setBloodGroup("A+");
        pat.setPhone("6666666666");

        Appointment ap = new Appointment();
        ap.setAppointDate(LocalDateTime.now());
        ap.setStatus("COMPLETED");
        ap.setReason("Skin allergy");
        ap.setDoctor(doc);
        ap.setPatient(pat);

        Prescription rx = new Prescription();
        rx.setMedicines("Cetirizine, Calamine lotion");
        rx.setDosage("1 tab at night, lotion 2 times/day");
        rx.setIssuedDate(LocalDate.now());

        ap.setPrescription(rx); // Uni 1:1 owned by Appointment

        em.persist(pat);
        em.persist(doc);
        em.persist(ap);

        em.getTransaction().commit();

        Long apId = ap.getId();
        System.out.println("Created Appointment with Prescription, apId=" + apId);

        // READ
        Appointment fetched = em.find(Appointment.class, apId);
        System.out.println("Fetched Appointment: " + fetched);
        System.out.println("Fetched Prescription: " + fetched.getPrescription());

        // UPDATE Prescription
        em.getTransaction().begin();
        fetched.getPrescription().setDosage("1 tab at night for 5 days, lotion 2 times/day");
        em.getTransaction().commit();
        System.out.println("Updated Prescription dosage.");

        // DELETE appointment (cascades delete prescription)
        em.getTransaction().begin();
        Appointment del = em.find(Appointment.class, apId);
        em.remove(del);
        em.getTransaction().commit();
        System.out.println("Deleted Appointment (Prescription cascaded).");

        em.close();
        JPAUtil.getEmf().close();
    }
}
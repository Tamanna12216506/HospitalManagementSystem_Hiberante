package org.hospitalManagementSystem.service;

import jakarta.persistence.EntityManager;
import org.hospitalManagementSystem.entity.MedicalRecord;
import org.hospitalManagementSystem.entity.Patient;
import org.hospitalManagementSystem.util.JPAUtil;

import java.time.LocalDate;

public class PatientServices{
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();

        // CREATE
        em.getTransaction().begin();

        MedicalRecord mr = new MedicalRecord();
        mr.setRecordDate(LocalDate.now());
        mr.setDiagnosis("Seasonal Flu");
        mr.setNotes("Rest + hydration");

        Patient p = new Patient();
        p.setName("Tamanna");
        p.setDob(LocalDate.of(2004, 1, 10));
        p.setBloodGroup("B+");
        p.setPhone("9999999999");
        p.setMedicalRecord(mr);

        em.persist(p);
        em.getTransaction().commit();

        Long patientId = p.getId();
        System.out.println("Created Patient: " + p);

        // READ
        Patient fetched = em.find(Patient.class, patientId);
        System.out.println("Fetched Patient: " + fetched);
        // medicalRecord is LAZY, access inside open session:
        System.out.println("Fetched MedicalRecord Summary: " +
                fetched.getMedicalRecord().getSummary());

        // UPDATE
        em.getTransaction().begin();
        fetched.setPhone("8888888888");
        fetched.getMedicalRecord().setNotes("Rest + hydration + paracetamol");
        em.getTransaction().commit();
        System.out.println("Updated Patient phone + MR notes.");

        // DELETE (deleting Patient should cascade delete MedicalRecord)
        em.getTransaction().begin();
        Patient toDelete = em.find(Patient.class, patientId);
        em.remove(toDelete);
        em.getTransaction().commit();
        System.out.println("Deleted Patient (MedicalRecord cascaded).");

        em.close();
        JPAUtil.getEmf().close();
    }
}

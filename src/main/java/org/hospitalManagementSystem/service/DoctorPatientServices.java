package org.hospitalManagementSystem.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hospitalManagementSystem.entity.Doctor;
import org.hospitalManagementSystem.entity.Patient;
import org.hospitalManagementSystem.util.JPAUtil;

import java.time.LocalDate;

public class DoctorPatientServices {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEmf().createEntityManager();

        try {
            em.getTransaction().begin();

            Doctor mehta = new Doctor();
            mehta.setName("Dr. Mehta");
            mehta.setSpecialization("Cardiology");
            mehta.setLicenseNo("LIC-CAR-101");

            Doctor singh = new Doctor();
            singh.setName("Dr. Singh");
            singh.setSpecialization("Neurology");
            singh.setLicenseNo("LIC-NEU-202");

            Patient ali = new Patient();
            ali.setName("Ali");
            ali.setDob(LocalDate.of(2001, 9, 9));
            ali.setBloodGroup("B+");
            ali.setPhone("5555555555");

            Patient priya = new Patient();
            priya.setName("Priya");
            priya.setDob(LocalDate.of(2002, 1, 15));
            priya.setBloodGroup("A+");
            priya.setPhone("4444444444");

            Patient raj = new Patient();
            raj.setName("Raj");
            raj.setDob(LocalDate.of(2000, 6, 20));
            raj.setBloodGroup("O+");
            raj.setPhone("3333333333");

            mehta.addPatient(ali);
            singh.addPatient(ali);
            mehta.addPatient(priya);
            singh.addPatient(raj);

            em.persist(mehta);
            em.persist(singh);

            em.getTransaction().commit();

//            System.out.println("STEP 4 DONE: Created doctors/patients and links (expected 4 join rows).");
//
//            TypedQuery<Doctor> q1 = em.createQuery(
//                    "SELECT d FROM Doctor d JOIN FETCH d.patients WHERE d.name = :n",
//                    Doctor.class
//            );
//            q1.setParameter("n", "Dr. Mehta");
//            Doctor mehtaFetched = q1.getSingleResult();
//
//            System.out.println("\nSTEP 5: Patients of Dr. Mehta:");
//            mehtaFetched.getPatients().forEach(System.out::println);
//
//            TypedQuery<Patient> q2 = em.createQuery(
//                    "SELECT p FROM Patient p JOIN FETCH p.doctors WHERE p.name = :pn",
//                    Patient.class
//            );
//            q2.setParameter("pn", "Ali");
//            Patient aliFetched = q2.getSingleResult();
//
//            System.out.println("\nSTEP 5: Doctors treating Ali:");
//            aliFetched.getDoctors().forEach(System.out::println);
//
//            em.getTransaction().begin();
//
//            Doctor mehta2 = em.createQuery(
//                    "SELECT d FROM Doctor d JOIN FETCH d.patients WHERE d.name = :n",
//                    Doctor.class
//            ).setParameter("n", "Dr. Mehta").getSingleResult();
//
//            Patient ali2 = em.createQuery(
//                    "SELECT p FROM Patient p WHERE p.name = :pn",
//                    Patient.class
//            ).setParameter("pn", "Ali").getSingleResult();
//
//            mehta2.removePatient(ali2);
//
//            em.getTransaction().commit();
//            System.out.println("\nSTEP 6 DONE: Discharged Ali from Dr. Mehta (join row removed, records remain).");
//
//            // 1) Fetch doctor WITHOUT JOIN FETCH (patients remain LAZY)
//            Doctor lazyDoctor = em.createQuery(
//                    "SELECT d FROM Doctor d WHERE d.name = :n",
//                    Doctor.class
//            ).setParameter("n", "Dr. Singh").getSingleResult();
//
//            // 2) Close EntityManager
//            em.close();
//
//            // 3) Access lazy collection after session close -> should throw LazyInitializationException
//            System.out.println("\nSTEP 8 DEMO: Now accessing lazyDoctor.getPatients() after EntityManager close...");
//            System.out.println("Patients of Dr. Singh count = " + lazyDoctor.getPatients().size());

        } finally {
            if (em.isOpen()) em.close();
            JPAUtil.getEmf().close();
        }
    }
}
package org.hospitalManagementSystem.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hospitalManagementSystem.entity.Department;
import org.hospitalManagementSystem.entity.Doctor;
import org.hospitalManagementSystem.util.JPAUtil;

import java.util.List;

public class DepartmentServices{
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();

        // CREATE Department + Doctors
        em.getTransaction().begin();

        Department d = new Department();
        d.setName("Cardiology");
        d.setLocation("Block A");
        d.setHeadDoctorName("Dr. Meera");

        Doctor doc1 = new Doctor();
        doc1.setName("Dr. Arjun");
        doc1.setSpecialization("Heart Surgeon");
        doc1.setLicenseNo("LIC-ARJ-001");

        Doctor doc2 = new Doctor();
        doc2.setName("Dr. Ravi");
        doc2.setSpecialization("Cardio Specialist");
        doc2.setLicenseNo("LIC-RAV-002");

        d.addDoctor(doc1);
        d.addDoctor(doc2);

        em.persist(d); // cascades doctors
        em.getTransaction().commit();

        System.out.println("Created Department: " + d);

        // READ + JPQL: get doctors by department name
        TypedQuery<Doctor> q = em.createQuery(
                "SELECT doc FROM Doctor doc WHERE doc.department.name = :deptName",
                Doctor.class
        );
        q.setParameter("deptName", "Cardiology");
        List<Doctor> doctors = q.getResultList();
        System.out.println("Doctors in Cardiology:");
        doctors.forEach(System.out::println);

        // UPDATE
        em.getTransaction().begin();
        Department fetched = em.find(Department.class, d.getId());
        fetched.setLocation("Block C");
        em.getTransaction().commit();
        System.out.println("Updated department location.");

        // DELETE one doctor from department
        em.getTransaction().begin();
        Department dep = em.find(Department.class, d.getId());
        Doctor removeDoc = dep.getDoctors().get(0);
        dep.removeDoctor(removeDoc); // orphanRemoval => deletes doctor row
        em.getTransaction().commit();
        System.out.println("Removed one doctor (orphanRemoval).");

        em.close();
        JPAUtil.getEmf().close();
    }
}
package org.hospitalManagementSystem.entity;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization;

    @Column(unique = true)
    private String licenseNo;

    // N:1 Doctor -> Department
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // Bi M:N Doctor <-> Patient
    @ManyToMany(mappedBy = "doctors")
    private Set<Patient> patients = new HashSet<>();

    // (We keep it as OneToMany because UML shows doctor has appointments list :contentReference[oaicite:4]{index=4})
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor() {}

    // helper methods
    public void addPatient(Patient p) {
        if (p == null) return;
        patients.add(p);
        p.getDoctors().add(this);
    }

    public void removePatient(Patient p) {
        if (p == null) return;
        patients.remove(p);
        p.getDoctors().remove(this);
    }

    public void addAppointment(Appointment a) {
        if (a == null) return;
        appointments.add(a);
        a.setDoctor(this);
    }

    public void removeAppointment(Appointment a) {
        if (a == null) return;
        appointments.remove(a);
        a.setDoctor(null);
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getLicenseNo() { return licenseNo; }
    public void setLicenseNo(String licenseNo) { this.licenseNo = licenseNo; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Set<Patient> getPatients() { return patients; }
    public void setPatients(Set<Patient> patients) { this.patients = patients; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }

    @Override
    public String toString() {
        return "Doctor{id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", licenseNo='" + licenseNo + '\'' +
                '}';
    }
}

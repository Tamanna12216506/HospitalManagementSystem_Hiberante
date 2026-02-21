package org.hospitalManagementSystem.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate dob;
    private String bloodGroup;
    private String phone;

    // Uni 1:1 Patient -> MedicalRecord (FK in patients table: med_record_id) :contentReference[oaicite:2]{index=2}
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "med_record_id")
    private MedicalRecord medicalRecord;

    // Bi M:N Patient <-> Doctor via patient_doctors :contentReference[oaicite:3]{index=3}
    @ManyToMany
    @JoinTable(
            name = "patient_doctors",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<Doctor> doctors = new HashSet<>();

    public Patient() {}

    public int getAge() {
        if (dob == null) return 0;
        return Period.between(dob, LocalDate.now()).getYears();
    }

    // helper methods (keep both sides in sync)
    public void addDoctor(Doctor d) {
        if (d == null) return;
        doctors.add(d);
        d.getPatients().add(this);
    }

    public void removeDoctor(Doctor d) {
        if (d == null) return;
        doctors.remove(d);
        d.getPatients().remove(this);
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public MedicalRecord getMedicalRecord() { return medicalRecord; }
    public void setMedicalRecord(MedicalRecord medicalRecord) { this.medicalRecord = medicalRecord; }

    public Set<Doctor> getDoctors() { return doctors; }
    public void setDoctors(Set<Doctor> doctors) { this.doctors = doctors; }

    @Override
    public String toString() {
        return "Patient{id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + getAge() +
                '}';
    }
}

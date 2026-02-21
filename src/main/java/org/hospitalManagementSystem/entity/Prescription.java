package org.hospitalManagementSystem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String medicines;

    @Column(length = 255)
    private String dosage;

    private LocalDate issuedDate;

    public Prescription() {}

    public boolean isActive() {
        if (issuedDate == null) return false;
        // simple rule: active for 30 days
        return issuedDate.isAfter(LocalDate.now().minusDays(30));
    }

    public Long getId() { return id; }

    public String getMedicines() { return medicines; }
    public void setMedicines(String medicines) { this.medicines = medicines; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public LocalDate getIssuedDate() { return issuedDate; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }

    @Override
    public String toString() {
        return "Prescription{id=" + id +
                ", medicines='" + medicines + '\'' +
                ", dosage='" + dosage + '\'' +
                ", issuedDate=" + issuedDate +
                ", active=" + isActive() +
                '}';
    }
}

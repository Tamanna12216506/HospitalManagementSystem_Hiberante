package org.hospitalManagementSystem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate recordDate;

    @Column(length = 255)
    private String diagnosis;

    @Column(length = 1000)
    private String notes;

    public MedicalRecord() {}

    public String getSummary() {
        return "MedicalRecord{id=" + id +
                ", recordDate=" + recordDate +
                ", diagnosis='" + diagnosis + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    public Long getId() { return id; }

    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return getSummary();
    }
}
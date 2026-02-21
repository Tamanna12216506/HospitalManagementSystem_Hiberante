package org.hospitalManagementSystem.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String headDoctorName;

    // Bi 1:N Department <-> Doctor (Department is parent)
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Doctor> doctors = new ArrayList<>();

    public Department() {}

    public void addDoctor(Doctor d) {
        if (d == null) return;
        doctors.add(d);
        d.setDepartment(this);
    }

    public void removeDoctor(Doctor d) {
        if (d == null) return;
        doctors.remove(d);
        d.setDepartment(null);
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getHeadDoctorName() { return headDoctorName; }
    public void setHeadDoctorName(String headDoctorName) { this.headDoctorName = headDoctorName; }

    public List<Doctor> getDoctors() { return doctors; }
    public void setDoctors(List<Doctor> doctors) { this.doctors = doctors; }

    @Override
    public String toString() {
        return "Department{id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", headDoctorName='" + headDoctorName + '\'' +
                ", doctorsCount=" + (doctors == null ? 0 : doctors.size()) +
                '}';
    }
}

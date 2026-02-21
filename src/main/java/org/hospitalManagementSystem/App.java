package org.hospitalManagementSystem;

import org.hospitalManagementSystem.service.*;

/**
 * Hello world!
 *
 */
public class App 
{

        public static void main(String[] args) {

            System.out.println("Starting Hospital ERP Application");


//            PatientServices.main(args); // Patient + MedicalRecord
//            DepartmentServices.main(args); // Department + Doctor
//            DoctorAppointServices.main(args); // Doctor + Appointment
//            AppointmentServices.main(args); // Appointment + Prescription
            DoctorPatientServices.main(args);    // Doctor + Patient

            System.out.println("Application Finished");
        }
    }


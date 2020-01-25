package com.cpe.backend.Bed.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.net.URLDecoder;
import com.cpe.backend.Bed.entity.Bed;
import com.cpe.backend.Appointment.entity.Department;
import com.cpe.backend.Bed.entity.Nurse;
import com.cpe.backend.RegisterPatient.entity.Patient;
import com.cpe.backend.Bed.entity.Reservation;
import com.cpe.backend.Bed.repository.BedRepository;
import com.cpe.backend.Appointment.repository.DepartmentRepository;
import com.cpe.backend.Bed.repository.NurseRepository;
import com.cpe.backend.RegisterPatient.repository.PatientRepository;
import com.cpe.backend.Bed.repository.ReservationRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ReservationController {
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private BedRepository bedRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private NurseRepository nurseRepository;
    @Autowired
    private PatientRepository patientRepository;

    ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservation")
    public Collection<Reservation> reservations() {
        return reservationRepository.findAll().stream().collect(Collectors.toList());
    }

    @RequestMapping(value = "/reservationPost/{patient_id}/{nurse_id}/{bed_id}/{department_id}", method = RequestMethod.POST)
    public Reservation newReservation(Reservation newReservation,
            @PathVariable long bed_id,
            @PathVariable long department_id, 
            @PathVariable long nurse_id,
            @PathVariable long patient_id,
            @RequestBody Map<String, String> Body) {

            Bed bed = bedRepository.findById(bed_id);
            Department department = departmentRepository.findById(department_id);
            Nurse nurse = nurseRepository.findById(nurse_id);
            Patient patient = patientRepository.findById(patient_id);
            
            System.out.println(bed.getName());

        newReservation.setBed(bed);
        newReservation.setDepartment(department);
        newReservation.setNote(Body.get("note"));


    String date1 = Body.get("checkDate");
    char[] letters = date1.toCharArray();
    for(int i = 0 ; i < date1.length() ; i ++){
        if(letters[i] == '-'){
            letters[i] = '/';
        }
    }
    String date3 = new String(letters);
    System.out.println(date3);
    String date11 = "10/06/1997";

    try{
        Date date2 = new SimpleDateFormat("yyyy/MM/dd").parse(date3.toString());
        newReservation.setReservDate(date2);
    }catch(Exception e){
        System.out.println(e);
    }
	
        newReservation.setPatient(patient);
        newReservation.setNurse(nurse);

    return reservationRepository.save(newReservation);     
    }

    @GetMapping("/reservationByPatient/{patient_id}")
    public Collection<Object[]> getResevationByPatientID(@PathVariable("patient_id") Long patient_id){
        return reservationRepository.findByPatientID(patient_id);
    }

    @GetMapping("getDate/{id}")
    public int getDate(@PathVariable Long id) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        Optional<Reservation> rev = reservationRepository.findById(id);
        Date dayStart = rev.get().getReservDate();
        String formatter = new SimpleDateFormat("dd-MM-yyyy").format(date);
        Date dayNow;

        try {
            dayNow = myFormat.parse(formatter);
            long diff = dayNow.getTime() - dayStart.getTime();
            System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
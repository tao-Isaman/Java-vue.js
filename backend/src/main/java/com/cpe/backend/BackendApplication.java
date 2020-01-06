package com.cpe.backend;

import java.text.SimpleDateFormat;
import java.util.stream.Stream;
import java.util.Date;

import com.cpe.backend.Appointment.entity.Department;
import com.cpe.backend.Appointment.entity.TypeCause;
import com.cpe.backend.Appointment.entity.TypeGoing;
import com.cpe.backend.Appointment.repository.DepartmentRepository;
import com.cpe.backend.Appointment.repository.TypeCauseRepository;
import com.cpe.backend.Appointment.repository.TypeGoingRepository;
import com.cpe.backend.RegisterPatient.entity.Benefit;
import com.cpe.backend.RegisterPatient.entity.Gender;
import com.cpe.backend.RegisterPatient.entity.Patient;
import com.cpe.backend.RegisterPatient.entity.Province;
import com.cpe.backend.RegisterPatient.entity.User;
import com.cpe.backend.RegisterPatient.repository.BenefitRepository;
import com.cpe.backend.RegisterPatient.repository.GenderRepository;
import com.cpe.backend.RegisterPatient.repository.PatientRepository;
import com.cpe.backend.RegisterPatient.repository.ProvinceRepository;
import com.cpe.backend.RegisterPatient.repository.UserRepository;
import com.cpe.backend.Examination.entity.Doctor;
import com.cpe.backend.Examination.entity.Duration;
import com.cpe.backend.Examination.entity.ExaminationSystem;
import com.cpe.backend.Examination.entity.State;
import com.cpe.backend.Examination.repository.DoctorRepository;
import com.cpe.backend.Examination.repository.DurationRepository;
import com.cpe.backend.Examination.repository.ExaminationRepository;
import com.cpe.backend.Examination.repository.StateRepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	ApplicationRunner init(UserRepository userRepo,BenefitRepository repository,
						   GenderRepository genderRepository,ProvinceRepository provinceRepository,
						   TypeCauseRepository typeCauseRepository, DepartmentRepository departmentRepository,
						   StateRepository s,DoctorRepository dr,DurationRepository du,ExaminationRepository examinationRepository,
						   TypeGoingRepository typeGoingRepository) {
		return args -> {
			User user1 = new User();
			user1.setId("1111");
			user1.setPassword("1111");
			user1.setJob("หมอ");
			userRepo.save(user1);

			User user2 = new User();
			user2.setId("2222");
			user2.setPassword("2222");
			user2.setJob("พยาบาล");
			userRepo.save(user2);

			User user3 = new User();
			user3.setId("3333");
			user3.setPassword("3333");
			user3.setJob("เวชรเบียน");
			userRepo.save(user3);

			Gender gender1 = new Gender();
			gender1.setName("ชาย");
			gender1.setGenderDescription("ผู้ชาย");
			genderRepository.save(gender1);

			Gender gender2 = new Gender();
			gender2.setName("หญิง");
			gender2.setGenderDescription("ผู้หญิง");
			genderRepository.save(gender2);

			Stream.of("ชัยภูมิ", "นคราชสีมา", "บุรรีรัมย์", "สุรินทร์").forEach(name -> {
				Province provinces = new Province(); // สร้าง Object Customer
				provinces.setName(name); // set ชื่อ (name) ให้ Object ชื่อ Customer
				provinces.setDescription("จังหวัด" +name +" เป็น 1 ในเขต นครชัยบุรินทร์ ");
				provinceRepository.save(provinces); // บันทึก Objcet ชื่อ Customer
			});


			Benefit benefit1 = new Benefit();
			benefit1.setName("สิทธิ 30 บาท");
			benefit1.setBeneDescriptstion("รักษาได้บางโรค จ่ายเงินเพียง 30 บาทเท่านั้น");
			benefit1.setDicount(20);
			repository.save(benefit1);

			Benefit benefit2 = new Benefit();
			benefit2.setName("สิทธิข้าราชการ");
			benefit2.setBeneDescriptstion("รักษาได้บางโรค ต้องเป็นข้าราชการหรือมีครอบครัวเป็นข้าราชการเท่านั้น");
			benefit2.setDicount(20);
			repository.save(benefit2);

			Benefit benefit3 = new Benefit();
			benefit3.setName("สิทธิผู้สูงอายุ");
			benefit3.setBeneDescriptstion("รักษาได้บางโรค ต้องเป็นผู้มีอายุมากกว่าหรือเท่ากับ 60 ปีเท่านั้น");
			benefit3.setDicount(20);
			repository.save(benefit3);

			Benefit benefit4 = new Benefit();
			benefit4.setName("สิทธิประกันสังคม");
			benefit4.setBeneDescriptstion("รักษาได้บางโรค ต้องจ่ายเบี้ยประกันสังคมทุกปี");
			benefit4.setDicount(20);
			repository.save(benefit4);

			Benefit benefit5 = new Benefit();
			benefit5.setName("สิทธินักศึกษา");
			benefit5.setBeneDescriptstion("รักษาได้บางโรคต้องเป็นนักศึกษามหาวิทยาลัยเทคโนโลยีสุรนารีเท่านั้น");
			benefit5.setDicount(20);
			repository.save(benefit5);

			Stream.of("ติดตามอาการ","นอนโรงพยาบาล").forEach(typecauseSelect ->{
				TypeCause typeCause2 = new TypeCause();
				typeCause2.setTypecauseSelect(typecauseSelect);
				typeCauseRepository.save(typeCause2);
			});

			Stream.of("แผนกวิสัญญี","แผนกศัลยกรรม","แผนกกุมารเวชกรรม","แผนกเวชศาสตร์ฟื้นฟู","แผนกอายุรกรรม","แผนกจักษุ","แผนกหู คอ จมูก","แผนกเภสัชกรรม","แผนกจิตเวช","แผนกรังสีกรรม","แผนกผู้ป่วยใน").forEach(departmentlSelect ->{
				Department department = new Department();
				department.setDepartmentlSelect(departmentlSelect);
				departmentRepository.save(department);
			});

			Stream.of("รถยนต์ส่วนบุคคล","รถโรงพยาบาล","รถจักรยานยนต์" , "เดิน").forEach(typegoingSelect ->{
				TypeGoing typeGoing = new TypeGoing();
				typeGoing.setTypegoingSelect(typegoingSelect);
				typeGoingRepository.save(typeGoing);
			});
			Stream.of("ดีขึ้น", "แย่ลง", "คงที่").forEach(name -> {
				State state = new State();
				state.setName(name);
				s.save(state);
			});

			Stream.of("นพ. เอ", "นพ. บี", "พญ. ซี").forEach(name -> {
				Doctor doctor = new Doctor();
				doctor.setDname(name);
				dr.save(doctor);
			});
			Stream.of("เช้า", "เที่ยง", "เย็น", "ดึก").forEach(name -> {
				Duration duration = new Duration();
				duration.setName(name);
				du.save(duration);
			});



		};
	}

}

package org.han.webtest.config;

import org.han.webtest.model.ClassScheduleModel;
import org.han.webtest.model.MembershipPlanModel;
import org.han.webtest.model.UserModel;
import org.han.webtest.repository.ClassScheduleRepository;
import org.han.webtest.repository.MembershipPlanRepository;
import org.han.webtest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class DataSeeder implements CommandLineRunner {

    private final MembershipPlanRepository planRepository;
    private final ClassScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(MembershipPlanRepository planRepository,
                      ClassScheduleRepository scheduleRepository,
                      UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.planRepository = planRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByEmail("admin@rogergym.com").isEmpty()) {
            UserModel admin = new UserModel();
            admin.setUsername("Super Admin");
            admin.setEmail("admin@rogergym.com");
            admin.setPassword(passwordEncoder.encode("rahasiaadmin123"));
            admin.setRole("ADMIN");

            userRepository.save(admin);
        }

        if (planRepository.count() == 0) {
            MembershipPlanModel basic = new MembershipPlanModel(
                    "Basic",
                    150000.0,
                    "Akses gym reguler, 1x konsultasi pelatih, Locker umum"
            );
            MembershipPlanModel premium = new MembershipPlanModel(
                    "Premium",
                    300000.0,
                    "Akses gym penuh, 4x sesi personal trainer, Akses kelas group, Locker pribadi"
            );
            MembershipPlanModel elite = new MembershipPlanModel(
                    "Elite",
                    500000.0,
                    "Akses 24 jam, 8x sesi personal trainer, Body assessment bulanan, Free merchandise"
            );

            planRepository.save(basic);
            planRepository.save(premium);
            planRepository.save(elite);
        }

        if (scheduleRepository.count() == 0) {
            ClassScheduleModel zumba = new ClassScheduleModel(
                    "Group Class - Zumba",
                    "Senin",
                    LocalTime.of(15, 0),  // Jam 15:00
                    LocalTime.of(16, 30), // Jam 16:30
                    "Coach Sarah"
            );

            ClassScheduleModel yoga = new ClassScheduleModel(
                    "Group Class - Yoga",
                    "Rabu",
                    LocalTime.of(16, 0),  // Jam 16:00
                    LocalTime.of(17, 30), // Jam 17:30
                    "Coach Budi"
            );

            ClassScheduleModel hiit = new ClassScheduleModel(
                    "Group Class - HIIT",
                    "Jumat",
                    LocalTime.of(18, 0),  // Jam 18:00
                    LocalTime.of(19, 0),  // Jam 19:00
                    "Coach Alex"
            );

            ClassScheduleModel pt1 = new ClassScheduleModel(
                    "Personal Training",
                    "Selasa",
                    LocalTime.of(13, 0),  // Jam 13:00
                    LocalTime.of(15, 0),  // Jam 15:00
                    "Coach Michael"
            );

            scheduleRepository.save(zumba);
            scheduleRepository.save(yoga);
            scheduleRepository.save(hiit);
            scheduleRepository.save(pt1);
        }
    }
}
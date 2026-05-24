package org.han.webtest.service;

import org.han.webtest.model.ClassBookingModel;
import org.han.webtest.model.ClassScheduleModel;
import org.han.webtest.model.UserModel;
import org.han.webtest.repository.ClassBookingRepository;
import org.han.webtest.repository.ClassScheduleRepository;
import org.han.webtest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClassService {

    private final ClassScheduleRepository scheduleRepository;
    private final ClassBookingRepository bookingRepository;
    private final UserRepository userRepository;

    public ClassService(ClassScheduleRepository scheduleRepository, ClassBookingRepository bookingRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public List<ClassScheduleModel> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<ClassScheduleModel> getSchedulesByDay(String dayOfWeek) {
        return scheduleRepository.findByDayOfWeek(dayOfWeek);
    }

    public ClassBookingModel bookClass(Long userId, Long scheduleId, LocalDate bookingDate) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ClassScheduleModel schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        ClassBookingModel booking = new ClassBookingModel(user, schedule, bookingDate, "PENDING");
        return bookingRepository.save(booking);
    }

    public List<ClassBookingModel> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
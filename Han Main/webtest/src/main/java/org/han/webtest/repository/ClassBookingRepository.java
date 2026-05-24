package org.han.webtest.repository;

import org.han.webtest.model.ClassBookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassBookingRepository extends JpaRepository<ClassBookingModel, Long> {

    List<ClassBookingModel> findByUserId(Long userId);

}
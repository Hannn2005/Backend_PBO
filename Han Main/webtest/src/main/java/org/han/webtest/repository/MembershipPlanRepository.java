package org.han.webtest.repository;

import org.han.webtest.model.MembershipPlanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipPlanRepository extends JpaRepository<MembershipPlanModel, Long> {
}
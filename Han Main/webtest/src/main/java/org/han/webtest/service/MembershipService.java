package org.han.webtest.service;

import org.han.webtest.model.MembershipPlanModel;
import org.han.webtest.model.UserMembershipModel;
import org.han.webtest.model.UserModel;
import org.han.webtest.repository.MembershipPlanRepository;
import org.han.webtest.repository.UserMembershipRepository;
import org.han.webtest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MembershipService {

    private final MembershipPlanRepository planRepository;
    private final UserMembershipRepository userMembershipRepository;
    private final UserRepository userRepository;

    public MembershipService(MembershipPlanRepository planRepository, UserMembershipRepository userMembershipRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userMembershipRepository = userMembershipRepository;
        this.userRepository = userRepository;
    }

    public List<MembershipPlanModel> getAllPlans() {
        return planRepository.findAll();
    }

    public UserMembershipModel subscribeUser(Long userId, Long planId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MembershipPlanModel plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(1);

        UserMembershipModel membership = new UserMembershipModel(user, plan, startDate, endDate, "ACTIVE");
        return userMembershipRepository.save(membership);
    }

    public List<UserMembershipModel> getUserMemberships(Long userId) {
        return userMembershipRepository.findByUserId(userId);
    }
}
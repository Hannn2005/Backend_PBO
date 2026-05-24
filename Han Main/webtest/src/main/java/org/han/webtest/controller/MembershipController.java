package org.han.webtest.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.han.webtest.model.MembershipPlanModel;
import org.han.webtest.model.UserMembershipModel;
import org.han.webtest.model.UserModel;
import org.han.webtest.service.MembershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping("/plans")
    public List<MembershipPlanModel> getAllPlans() {
        return membershipService.getAllPlans();
    }

    @PostMapping("/subscribe/{planId}")
    public ResponseEntity<UserMembershipModel> subscribeToPlan(@PathVariable Long planId, HttpServletRequest req) {
        UserModel user = (UserModel) req.getAttribute("user");
        UserMembershipModel subscription = membershipService.subscribeUser(user.getId(), planId);
        return ResponseEntity.ok(subscription);
    }

    @GetMapping("/my-history")
    public ResponseEntity<List<UserMembershipModel>> getMyMemberships(HttpServletRequest req) {
        UserModel user = (UserModel) req.getAttribute("user");
        List<UserMembershipModel> history = membershipService.getUserMemberships(user.getId());
        return ResponseEntity.ok(history);
    }
}
package org.han.webtest.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.han.webtest.DTO.UserDashboardResponse;
import org.han.webtest.model.UserModel;
import org.han.webtest.repository.UserRepository;
import org.han.webtest.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin (origins = "http://localhost:5173")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping
    public List<UserDashboardResponse> getAllUser(HttpServletRequest req) {
        UserModel user = (UserModel) req.getAttribute("user");

        if (!user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Unauthorized");
        }

        return adminService.getAllUser();
    }
}

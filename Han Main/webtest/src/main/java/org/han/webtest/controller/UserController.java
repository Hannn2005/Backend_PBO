package org.han.webtest.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.han.webtest.DTO.UserDashboardResponse;
import org.han.webtest.DTO.UserLoginRequest;
import org.han.webtest.model.UserModel;
import org.han.webtest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/signup")
    public UserModel createUser(@RequestBody UserModel user){
        return userService.createUsers(user.getUsername(),user.getEmail(),user.getPassword());
    }

    @PostMapping("/login")
    public UserDashboardResponse userLogin(@RequestBody UserLoginRequest req, HttpServletResponse res){
        return  userService.userLogin(req,res);
    }


    @GetMapping("/dashboard")
    public UserDashboardResponse getUserDashboard(HttpServletRequest req){
        UserModel user = (UserModel) req.getAttribute("user");
        return userService.getUserDashboard(user);
    }


    @PostMapping("/logout")
    public ResponseEntity<String> userLogout(HttpServletResponse response){
        Cookie cookie = new Cookie("token",null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // true kalau HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("LOGOUT SUCCESS");
    }






}

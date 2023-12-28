package com.aptech.spring01.controllers;

import com.aptech.spring01.models.User;
import com.aptech.spring01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ModelAndView home(ModelAndView model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addObject("user", userDetails.getAuthorities());
        User user = userService.findUserByEmail(userDetails.getUsername());
        if (user != null) {
            model.addObject("user", user);
        }

        model.setViewName("home");
        return model;
    }
}

package com.aptech.spring01.controllers.admin;

import com.aptech.spring01.models.Role;
import com.aptech.spring01.models.User;
import com.aptech.spring01.service.RoleService;
import com.aptech.spring01.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("")
    public ModelAndView index(ModelAndView model) {
        model.addObject("users", userService.getAll());
        model.setViewName("admin/index");
        return model;
    }

    @GetMapping("/create")
    public ModelAndView create(ModelAndView model) {
        model.addObject("user", new User());
        model.addObject("roles", roleService.getAll());
        model.setViewName("admin/user/form");
        return model;
    }

    @PostMapping("/create")
    public String createUser(Model model, @Valid User user, BindingResult result) {

        User existingUser = userService.findUserByEmail(user.getEmail());

        if (existingUser != null && existingUser.getEmail() != null
                && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            List<Role> roles = roleService.getAll();
            model.addAttribute("roles", roles);
            model.addAttribute("user", user);
            return "admin/user/form";
        }

        userService.create(user);

        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(ModelAndView model, @PathVariable String id) {
        model.addObject("user", userService.findUserById(Integer.parseInt(id)));
        model.addObject("roles", roleService.getAll());
        model.setViewName("admin/user/edit");
        return model;
    }

    @PostMapping("/edit/{id}")
    public String update(Model model,  @PathVariable String id, @Valid User user, BindingResult result) {
        User existingUser = userService.findUserById(Integer.parseInt(id));

        if (existingUser != null && !user.getEmail().equals(existingUser.getEmail())) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            List<Role> roles = roleService.getAll();
            model.addAttribute("roles", roles);
            model.addAttribute("user", user);
            return "admin/user/edit";
        }

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setRoles(user.getRoles());
        existingUser.setProfile(user.getProfile());

        userService.update(existingUser);

        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        userService.delete(Integer.parseInt(id));
        return "redirect:/admin";
    }
}

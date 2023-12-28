package com.aptech.spring01.controllers.admin;

import com.aptech.spring01.models.Category;
import com.aptech.spring01.models.Role;
import com.aptech.spring01.models.User;
import com.aptech.spring01.service.CategoryService;
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

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ModelAndView index(ModelAndView model) {
        model.addObject("categories", categoryService.getAll());
        model.setViewName("admin/category/index");
        return model;
    }

    @GetMapping("/create")
    public ModelAndView add(ModelAndView model) {
        model.addObject("category", new Category());
        model.setViewName("admin/category/form");
        return model;
    }

    @PostMapping("/create")
    public String create(Model model, @Valid Category category, BindingResult rs) {
        Category existed = categoryService.findCategoryByName(category.getName());
        if (rs.hasErrors() || existed != null) {
            model.addAttribute("category", category);
            return "admin/category/form";
        }
        categoryService.create(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(ModelAndView model, @PathVariable String id) {
        model.addObject("category", categoryService.detail(Integer.parseInt(id)));
        model.setViewName("admin/category/edit");
        return model;
    }

    @PostMapping("/edit/{id}")
    public String update(Model model, @PathVariable String id, @Valid Category category, BindingResult rs) {
        Category detail = categoryService.detail(Integer.parseInt(id));

        if (rs.hasErrors() || detail == null) {
            model.addAttribute("category", category);
            return "admin/category/edit";
        }

        Category existed = categoryService.findCategoryByName(category.getName());
        if (existed != null && existed.getId() != detail.getId()) {
            model.addAttribute("category", category);
            return "admin/category/edit";
        }
        detail.setName(category.getName());
        detail.setTag(category.getTag());
        categoryService.update(detail);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        categoryService.delete(Integer.parseInt(id));
        return "redirect:/admin/categories";
    }

}

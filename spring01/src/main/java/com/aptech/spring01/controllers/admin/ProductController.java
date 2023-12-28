package com.aptech.spring01.controllers.admin;

import com.aptech.spring01.models.Category;
import com.aptech.spring01.models.Product;
import com.aptech.spring01.service.CategoryService;
import com.aptech.spring01.service.ProductService;
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

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("")
    public ModelAndView index(ModelAndView model) {
        model.addObject("products", productService.getAll());
        model.setViewName("admin/product/index");
        return model;
    }

    @GetMapping("/create")
    public ModelAndView add(ModelAndView model) {
        model.addObject("categories", categoryService.getAll());
        model.addObject("product", new Product());
        model.setViewName("admin/product/form");
        return model;
    }

    @PostMapping("/create")
    public String create(Model model, @Valid Product product, BindingResult rs) {
        if (rs.hasErrors()) {
            return "admin/product/form";
        }
        productService.create(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(ModelAndView model, @PathVariable String id) {
        model.addObject("categories", categoryService.getAll());
        model.addObject("product", productService.findById(Integer.parseInt(id)));
        model.setViewName("admin/product/edit");
        return model;
    }

    @PostMapping("/edit/{id}")
    public String update(Model model, @PathVariable String id, @Valid Product product, BindingResult rs) {
        if (rs.hasErrors()) {
            model.addAttribute("categories",  categoryService.getAll());
            model.addAttribute("product", product);
            return "admin/product/edit";
        }

        productService.update(Integer.parseInt(id), product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        productService.delete(Integer.parseInt(id));
        return "redirect:/admin/products";
    }

}

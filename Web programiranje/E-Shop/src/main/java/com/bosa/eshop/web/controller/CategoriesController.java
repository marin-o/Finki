package com.bosa.eshop.web.controller;

import com.bosa.eshop.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoriesController {
    private final CategoryService categoryService;

    @GetMapping()
    public String getCategoriesPage( Model model){
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("bodyContent", "categories");

        return "master-template";
    }
}

package com.bosa.eshop.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping
    public String getHomePage( Model model, HttpServletRequest req){
        model.addAttribute("bodyContent","home");
        Integer userViews = (Integer) req.getServletContext().getAttribute("userViews");
        req.getServletContext().setAttribute("userViews",++userViews);
        model.addAttribute("userViews", req.getServletContext().getAttribute("userViews"));
        return "master-template";
    }
}

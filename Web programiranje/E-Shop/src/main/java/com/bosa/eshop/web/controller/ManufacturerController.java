package com.bosa.eshop.web.controller;

import com.bosa.eshop.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manufacturers")
@AllArgsConstructor
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @GetMapping()
    public String getManufacturersPage( Model model ){
        model.addAttribute("manufacturers",manufacturerService.findAll());
        model.addAttribute("bodyContent", "manufacturers");
        return "master-template";
    }
}

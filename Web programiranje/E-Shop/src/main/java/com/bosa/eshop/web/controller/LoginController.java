package com.bosa.eshop.web.controller;

import com.bosa.eshop.model.User;
import com.bosa.eshop.model.exception.InvalidUserCredentialsException;
import com.bosa.eshop.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final AuthService authService;

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent","login");
        return "master-template";
    }

    @PostMapping
    public String login( HttpServletRequest request, Model model) {
        User user;
        try{
            user = authService.login(request.getParameter("username"),
                    request.getParameter("password"));

        }
        catch ( InvalidUserCredentialsException e ){
            model.addAttribute("bodyContent","login");
            model.addAttribute("hasError", true);
            model.addAttribute("theError",e.getMessage());
            return "master-template";
        }
        request.getSession().setAttribute("user",user);
        return "redirect:/home";
    }
}

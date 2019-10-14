package com.demo.springmvc.controller;

import com.demo.springmvc.model.User;
import com.demo.springmvc.service.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

  private UserDetailsServiceImpl userDetailsService;

  public AuthController(UserDetailsServiceImpl userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @GetMapping("/login")
  public String login(){
    return "auth/login";
  }

  @GetMapping("/register")
  public String register(Model model){
     model.addAttribute("user",new User());
     return "auth/register";
  }
  @PostMapping("/register")
  public String processRegister( User user, RedirectAttributes redirectAttributes){
    userDetailsService.register(user);
    redirectAttributes.addFlashAttribute("register",true);
    return "redirect:/products";
  }
}

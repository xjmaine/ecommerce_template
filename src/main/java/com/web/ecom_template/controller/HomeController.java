package com.web.ecom_template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping("/view")
    public String viewEmail() {
                return "emails/email-content";
    }

}

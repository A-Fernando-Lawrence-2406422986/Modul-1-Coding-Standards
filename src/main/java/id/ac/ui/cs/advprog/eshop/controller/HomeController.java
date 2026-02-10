package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String createHomePage() {
        // This string must match the exact name of your HTML file
        // (without .html extension).
        // Since your file is named 'HomePage.html', we return "HomePage".
        return "HomePage";
    }
}
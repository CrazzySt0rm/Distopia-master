package noidea.Dystopia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@CrossOrigin(origins = "http://localhost:8081")
public class LoginController {

    //login-controller
    @GetMapping("/login-form")
    public String getLoginForm() {
        return "login-form";
    }
}

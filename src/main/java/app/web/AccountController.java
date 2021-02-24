package app.web;

import app.dto.ForgotPasswordDTO;
import app.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {

    @GetMapping("/login")
    public String login(Model model) {

        return "account/login";
    }
    @GetMapping("/forgotpassword")
    public String forgotpasword(ForgotPasswordDTO forgotPasswordDTO) {

        return "account/forgotpassword";
    }
}

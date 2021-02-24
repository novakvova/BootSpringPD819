package app.web;

import app.dto.ForgotPasswordDTO;
import app.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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

    @PostMapping("/forgotpaswordsend")
    public String forgotpaswordsend(@Valid ForgotPasswordDTO forgotPasswordDTO, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "account/forgotpassword";
        }


        return "account/forgotpaswordsend";
    }
}

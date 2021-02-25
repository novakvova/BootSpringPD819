package app.web;

import app.dto.ForgotPasswordDTO;
import app.entities.User;
import app.repositories.UserRepository;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AccountController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AccountController(UserService userService,
                             UserRepository userRepository) {
        this.userService = userService;
        this.userRepository=userRepository;
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "account/login";
    }
    @GetMapping("/forgotpassword")
    public String forgotpasword(ForgotPasswordDTO forgotPasswordDTO) {

        return "account/forgotpassword";
    }

    @PostMapping("/forgotpaswordsend")
    public String forgotpaswordsend(final HttpServletRequest request,
                                    @Valid ForgotPasswordDTO forgotPasswordDTO,
                                    BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "account/forgotpassword";
        }
        //Send message email
        User user = userRepository.findByEmail(forgotPasswordDTO.getEmail());
        if(user==null)
        {
            model.addAttribute("error", "Дана пошта відсутня");
            return "forgotPassword";
        }
        String domain = getAppUrl(request);
        userService.resetPasswordSendEmail(user, domain);

        return "account/forgotpaswordsend";
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}

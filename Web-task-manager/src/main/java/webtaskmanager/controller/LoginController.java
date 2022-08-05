package webtaskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import webtaskmanager.model.User;
import webtaskmanager.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login-fail")
    public String loginFail(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("errorMessage", "Accessing the wrong information!!");
        return "login";
    }

    @GetMapping("/login")
    public String loginGet(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/register")
    public String registerPost(Model model, @Valid @ModelAttribute User user, BindingResult rs) {
        if(rs.hasErrors()) {
            FieldError fieldError = rs.getFieldError();
            model.addAttribute("exist", fieldError.getDefaultMessage());
            return "login";
        }
        List<User> list = userService.findAllUsers();
        for (User u : list) {
            if (user.getUsername().equalsIgnoreCase(u.getUsername())) {
                model.addAttribute("exist", "User already exist!!");
                return "login";
            }
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.insertUser(user);
        return "login";
    }

}

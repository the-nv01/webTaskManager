package webtaskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webtaskmanager.model.User;
import webtaskmanager.service.UserServiceimpl;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private UserServiceimpl userServiceimpl;

    @GetMapping("/login-fail")
    public String loginFail(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("errorMessage", "Sai thong tin dang nhap!");
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
    public String registerPost(@ModelAttribute User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userServiceimpl.createUser(user);
        return "login";
    }
}

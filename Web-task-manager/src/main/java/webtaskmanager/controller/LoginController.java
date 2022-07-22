package webtaskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webtaskmanager.model.User;
import webtaskmanager.service.UserServiceimpl;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private UserServiceimpl userServiceimpl;

    @GetMapping("/login")
    public String loginGet(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

    @PostMapping("/login")
    public String loginPost(Model model, @ModelAttribute(name="user") User user) {
        if(userServiceimpl.checkUser(user) == true){
            return "redirect:/tasks/views/page";
        }
        return "index";
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

    @PostMapping("/register")
    public String registerPost(Model model, @ModelAttribute(name="user") User user) {
        userServiceimpl.createUser(user);
        return "index";
    }
}

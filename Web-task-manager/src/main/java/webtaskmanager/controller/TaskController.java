package webtaskmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webtaskmanager.model.Task;
import webtaskmanager.service.TaskService;

import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/views/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Task> page = taskService.findAll(pageable);
        model.addAttribute("page", page);
        return "listTask";
    }
}

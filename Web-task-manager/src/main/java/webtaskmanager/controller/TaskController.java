package webtaskmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import webtaskmanager.model.Search;
import webtaskmanager.model.Task;
import webtaskmanager.service.TaskServiceimpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskServiceimpl taskServiceimpl;

    @GetMapping("")
    public String liskTask(Model model, @RequestParam("p") Optional<Integer> p, HttpServletRequest request){
        String action = request.getParameter("action")==null ? "" : request.getParameter("action");
        String actionView = request.getParameter("action")==null ? "All" : request.getParameter("action");
        String pSearch = request.getParameter("pSearch")==null ? "" : request.getParameter("pSearch");
        if(action.equals("All")) {action = "";}
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Task> page = taskServiceimpl.search(pSearch, action, pageable);
        model.addAttribute("page", page);
        model.addAttribute("pSearch", pSearch);
        model.addAttribute("action", action);
        model.addAttribute("actionView", actionView);
        return "listTask";
    }

    @GetMapping("/edit/{id}")
    public String editTaskGet(Model model, @PathVariable int id) {
        Task t = taskServiceimpl.getTaskByCode(id).get();
        model.addAttribute("task", t);
        return "editTask";
    }

    @PostMapping("/edit/{id}")
    public String editTaskPost(@PathVariable int id,
                               @Valid @ModelAttribute Task task, BindingResult rs) {
        if (rs.hasErrors()) return "editTask";
        taskServiceimpl.editTask(id, task);
        return "redirect:/tasks";
    }

    @GetMapping("/create")
    public String createTaskGet(Model model) {
        model.addAttribute("task", new Task());
        return "createTask";
    }

    @PostMapping("/create")
    public String createTaskPost(@Valid @ModelAttribute Task task, BindingResult rs) {
        if (rs.hasErrors()) {
            return "createTask";
        }
        taskServiceimpl.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/detail/{id}")
    public String detailTaskGet(Model model, @PathVariable int id) {
        Task t = taskServiceimpl.getTaskByCode(id).get();
        model.addAttribute("task", t);
        return "detailTask";
    }

    @GetMapping("/delete/{id}")
    public String deleteTaskGet(@PathVariable int id) {
        taskServiceimpl.deleteTask(taskServiceimpl.getTaskByCode(id).get());
        return "redirect:/tasks";
    }
}

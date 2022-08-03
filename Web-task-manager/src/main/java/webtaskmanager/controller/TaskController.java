package webtaskmanager.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webtaskmanager.model.Task;
import webtaskmanager.service.TaskServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskServiceimpl;

    @GetMapping("")
    public String liskTask(Model model, @RequestParam Optional<Integer> p, HttpServletRequest request){
        String action = request.getParameter("action")==null ? "" : request.getParameter("action");
        String actionView = request.getParameter("action")==null ? "All" : request.getParameter("action");
        String pSearch = request.getParameter("pSearch")==null ? "" : request.getParameter("pSearch");
        if(action.equals("All")) {action = "";}
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        List<Task> listTask = taskServiceimpl.findAllByPage(pSearch, action, pageable);
        Page<Task> page = new PageImpl<>(listTask, pageable, taskServiceimpl.countTasks());
        model.addAttribute("page", page);
        model.addAttribute("pSearch", pSearch);
        model.addAttribute("action", action);
        model.addAttribute("actionView", actionView);
        return "listTask";
    }

    @GetMapping("/edit/{id}")
    public String editTaskGet(Model model, @PathVariable int id) {
        Task t = taskServiceimpl.findById(id);
        model.addAttribute("task", t);
        return "editTask";
    }

    @PostMapping("/edit/{id}")
    public String editTaskPost(@PathVariable int id,
                               @Valid @ModelAttribute Task task, BindingResult rs) {
        if (rs.hasErrors()) return "editTask";
        taskServiceimpl.updateTask(id, task);
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
        taskServiceimpl.insertTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/detail/{id}")
    public String detailTaskGet(Model model, @PathVariable int id) {
        Task t = taskServiceimpl.findById(id);
        model.addAttribute("task", t);
        return "detailTask";
    }

    @GetMapping("/delete/{id}")
    public String deleteTaskGet(@PathVariable int id) {
        taskServiceimpl.deleteTask(id);
        return "redirect:/tasks";
    }
}

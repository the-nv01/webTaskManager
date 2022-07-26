package webtaskmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webtaskmanager.model.Search;
import webtaskmanager.model.Task;
import webtaskmanager.service.TaskServiceimpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskServiceimpl taskServiceimpl;

    @ResponseBody
    @GetMapping("")
    public String search(Model model, @RequestParam("p") Optional<Integer> p, HttpServletRequest request){
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
}

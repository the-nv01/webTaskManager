package webtaskmanager.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import webtaskmanager.model.Task;
import webtaskmanager.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private String success = null;

    @GetMapping("")
    public String liskTask(Model model, @RequestParam Optional<Integer> p, HttpServletRequest request){
        String action = request.getParameter("action")==null ? "" : request.getParameter("action");
        String actionView = request.getParameter("action")==null ? "All" : request.getParameter("action");
        String pSearch = request.getParameter("pSearch")==null ? "" : request.getParameter("pSearch");
        if(action.equals("All")) {action = "";}
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        List<Task> listTask = taskService.findAllByPage(pSearch, action, pageable);
        int countTask = taskService.countTasks(pSearch, action);
        Page<Task> page = new PageImpl<>(listTask, pageable, countTask);
        model.addAttribute("page", page);
        model.addAttribute("pSearch", pSearch);
        model.addAttribute("action", action);
        model.addAttribute("actionView", actionView);
        model.addAttribute("success", success);
        success = null;
        return "listTask";
    }

    @GetMapping("/edit/{id}")
    public String editTaskGet(Model model, @PathVariable int id) {
        Task t = taskService.findById(id);
        model.addAttribute("task", t);
        return "editTask";
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editTaskPost(ModelMap model,
                                     @Valid @ModelAttribute Task task, BindingResult rs) {
        if (rs.hasErrors() ) {
            List<FieldError> fieldErrors = rs.getFieldErrors();
            model.addAttribute("errors", fieldErrors);
            return new ModelAndView("editTask");
        }
        success = "Success!";
        taskService.updateTask(task);
        return new ModelAndView("redirect:/tasks", model);
    }

    @GetMapping("/create")
    public String createTaskGet(Model model) {
        model.addAttribute("task", new Task());
        return "createTask";
    }

    @PostMapping("/create")
    public String createTaskPost(Model model, @Valid @ModelAttribute Task task, BindingResult rs) {
        if (rs.hasErrors() ) {
            List<FieldError> fieldErrors = rs.getFieldErrors();
            model.addAttribute("errors", fieldErrors);
            return "createTask";
        }
        success = "Success!";
        taskService.insertTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/detail/{id}")
    public String detailTaskGet(Model model, @PathVariable int id) {
        Task t = taskService.findById(id);
        model.addAttribute("task", t);
        return "detailTask";
    }

    @GetMapping("/delete/{id}")
    public String deleteTaskGet(@PathVariable int id) {
        taskService.deleteTask(id);
        success = "Success!";
        return "redirect:/tasks";
    }

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Task> listTasks = taskService.findAllTasks();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Task ID", "Title", "Description", "Action"};
        String[] nameMapping = {"id", "title", "description", "action"};

        csvWriter.writeHeader(csvHeader);

        for (Task task : listTasks) {
            csvWriter.write(task, nameMapping);
        }

        csvWriter.close();

    }
}

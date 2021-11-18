package com.mastery.java.task.rest;

import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private ModelAndView modelAndView;

    @PostMapping
    public ResponseEntity<String> hireEmployee(@RequestBody EmployeeDto employeeDto) {
        try{
            employeeService.save(employeeDto);
            return ResponseEntity.ok("Employee hired");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Can't hire this employee");
        }
    }

    @DeleteMapping()
    public ResponseEntity<String> fireEmployee (@RequestParam Long id){
        if(employeeService.getById(id) == null){
            return ResponseEntity.badRequest().body("Can't fire employee");
        }
        employeeService.delete(id);
        return ResponseEntity.ok("Employee has been fired");
    }

    @GetMapping()
    public ModelAndView getEmployeeById(@RequestParam(name = "id") Long id){
        modelAndView = new ModelAndView();
        try{
            modelAndView.addObject("employee",employeeService.getById(id));
            modelAndView.setViewName("EmployeePage");
            return modelAndView;
        }
        catch(Exception e){
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }

    @GetMapping(path = "/all")
    public ModelAndView getAllEmployees(){
        modelAndView = new ModelAndView();
        try{
            modelAndView.addObject("employees",employeeService.getAll());
            modelAndView.setViewName("EmployeeList");
            return modelAndView;
        }
        catch(Exception e){
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }
}

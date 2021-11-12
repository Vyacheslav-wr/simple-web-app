package com.mastery.java.task.rest;

import com.mastery.java.task.dto.EmployeeDto;
import com.mastery.java.task.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

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

    @GetMapping(path = "/fire")
    public ResponseEntity<String> fireEmployee (@RequestParam Long id){
        try{
            employeeService.delete(id);
            return ResponseEntity.ok("Employee has been fired");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Can't fire this employee");
        }
    }

    @GetMapping(path = "/get")
    public ResponseEntity getEmployeeById(@RequestParam Long id){
        try{
            return ResponseEntity.ok( employeeService.getById(id));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("Such employee doesn't exist");
        }
    }

    @GetMapping
    public ResponseEntity getAllEmployees(){
        try{
            return ResponseEntity.ok(employeeService.getAll());
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("There is no employees");
        }
    }
}

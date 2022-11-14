package dev.fdifrison.tutorial.controller;

import dev.fdifrison.tutorial.entity.Department;
import dev.fdifrison.tutorial.service.DepartmentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentServiceImpl service;

    public DepartmentController(DepartmentServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public Department save(@RequestBody Department department) {
        return service.save(department);
    }

    @GetMapping
    public List<Department> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable Long id) {
        return service.getDepartment(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartmentById(@PathVariable Long id) {
        service.deleteDepartmentById(id);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        return service.updateDepartment(department, id);
    }

    @GetMapping("/name/{name}")
    public Department findDepartmentByName(@PathVariable String name) {
        return service.findDepartmentByName(name);
    }

}

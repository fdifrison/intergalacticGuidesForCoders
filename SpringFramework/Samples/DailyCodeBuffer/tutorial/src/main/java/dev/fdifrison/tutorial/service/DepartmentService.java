package dev.fdifrison.tutorial.service;

import dev.fdifrison.tutorial.entity.Department;

import java.util.List;

public interface DepartmentService {
    public Department save(Department department);

    public List<Department> findAll();

    public Department getDepartment(Long id);

    public void deleteDepartmentById(Long id);

    public Department updateDepartment(Department department, Long id);

    public Department findDepartmentByName(String name);

}

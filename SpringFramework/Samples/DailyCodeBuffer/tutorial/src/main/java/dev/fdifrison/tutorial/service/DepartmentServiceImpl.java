package dev.fdifrison.tutorial.service;

import dev.fdifrison.tutorial.entity.Department;
import dev.fdifrison.tutorial.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    static boolean isNullOrEmpty(String s) {
        return s == null || s.isBlank();
    }

    @Override
    public Department save(Department department) {
        return repository.save(department);
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public Department getDepartment(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Department updateDepartment(Department department, Long id) {
        Department departmentFromDb = repository.findById(id).orElse(null);
        if (departmentFromDb != null) {
            // check if the updated values are not null or empty
            if (isNullOrEmpty(department.getName())) departmentFromDb.setName(department.getName());
            if (isNullOrEmpty(department.getAddress())) departmentFromDb.setAddress(department.getAddress());
            if (isNullOrEmpty(department.getCode())) departmentFromDb.setCode(department.getCode());
            return repository.save(departmentFromDb);
        }
        return null;
    }

    @Override
    public Department findDepartmentByName(String name) {
        return repository.findAll().stream().filter(
                department -> department.getName().equals(name)).findFirst().orElse(null);
    }
}

package com.jobster.website.services;

import com.jobster.website.exeptions.NotFoundException;
import com.jobster.website.models.Employee;
import com.jobster.website.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Employee findById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee with id: " + id + " not found"));
    }

    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public boolean exist(long id) {
       return employeeRepository.existsById(id);
    }

    @Transactional
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }

}

package bank.service.impl;

import bank.entity.Employee;
import bank.repository.*;
import bank.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static bank.util.Utils.getNullPropertyNames;
import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    public final EmployeeRepository employeeRepository;
    public final BankRepository bankRepository;

    public Employee createEmployee(Employee employee) {
        bankRepository.incrementNumberEmployeesByBankId(employee.getBankId());
        System.out.println(employee);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() { return employeeRepository.findAll(); }

    public Optional<Employee> getEmployee(Long id) { return employeeRepository.findById(id); }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> Employee = employeeRepository.findById(id);

        if (Employee.isPresent()) {
            Employee existingIntern = Employee.get();
            String[] ignore = getNullPropertyNames(existingIntern);
            copyProperties(employeeDetails, existingIntern, getNullPropertyNames(employeeDetails));
            System.out.println(Arrays.toString(ignore));

            existingIntern.setId(employeeDetails.getId());
            return employeeRepository.save(existingIntern);
        }

        return null;
    }

    public void deleteEmployee(Long id) { employeeRepository.deleteById(id); }

    public void deleteAllEmployees() { employeeRepository.deleteAll(); }
}
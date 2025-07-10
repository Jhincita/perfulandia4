package duoc.perfulandia.service;

import duoc.perfulandia.model.User;
import duoc.perfulandia.model.Customer;
import duoc.perfulandia.model.Employee;
import duoc.perfulandia.repo.CustomerRepo;
import duoc.perfulandia.repo.EmployeeRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final CustomerRepo customerRepo;
    private final EmployeeRepo employeeRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(CustomerRepo customerRepo, EmployeeRepo employeeRepo) {
        this.customerRepo = customerRepo;
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<User> login(String email, String rawPassword) {
        //customer login
        Optional<Customer> customerOpt = customerRepo.findByEmail(email);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (passwordEncoder.matches(rawPassword, customer.getPassword())) {
                return Optional.of(customer);
            }
        }

        //employee login
        Optional<Employee> employeeOpt = employeeRepo.findByEmail(email);
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            if (passwordEncoder.matches(rawPassword, employee.getPassword())) {
                return Optional.of(employee);
            }
        }

        // sinb match
        return Optional.empty();
    }
}

package duoc.perfulandia.service;
import duoc.perfulandia.model.*;
import duoc.perfulandia.model.Repo.EmployeeRepo;
import duoc.perfulandia.model.Repo.RoleRepo;
import duoc.perfulandia.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired private EmployeeRepo employeeRepo;
    @Autowired private RoleRepo roleRepo;

 // create
    public Employee create(Employee e) {
        return employeeRepo.save(e);
    }

//read all
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }
// read by id
    public Optional<Employee> findById(Long id) {
        return employeeRepo.findById(id);
    }
// update
    public Employee update(Employee e) {
        return employeeRepo.save(e);
    }
//delete
    public void delete(Long id) {
        employeeRepo.deleteById(id);
    }

    // assign roles por rolid y empleadoID
    public Employee assignRole(Long employeeId, Long roleId) {
        Employee emp = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado de ID: " + employeeId));
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado de ID: " + roleId));
        emp.setRole(role);
        return employeeRepo.save(emp);
    }
}

package duoc.perfulandia.controller;
import duoc.perfulandia.model.Employee;
import duoc.perfulandia.model.Role;
import duoc.perfulandia.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee create(@RequestBody Employee e) {
        return employeeService.create(e);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping
    public Employee update(@RequestBody Employee e) {
        return employeeService.update(e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/role/{roleId}")
    public Employee assignRole(@PathVariable Long id, @PathVariable Long roleId) {
        return employeeService.assignRole(id, roleId);
    }
}

package org.mapping.springboottesting.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapping.springboottesting.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit Test for save employee operator
    @DisplayName("JUnit Test for save employee operator")
    @Test
    public void giveEmployeeObject_whenSave_thenReturnSaveEmployee() {

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe")
                .build();

        // when - action or behavior then we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output or result
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    // JUnit Test delete employee operator
    @Test
    public void giveEmployeeObject_whenDelete_thenReturnDeleteEmployee() {
        // given - precondition or setup
        Employee employee = Employee.builder()
               .firstName("John")
               .lastName("Doe")
               .email("john.doe")
               .build();
        employeeRepository.save(employee);

        // when - action or behavior then we are going to test
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> deletedEmployee = employeeRepository.findById(employee.getId());

        // then - verify the output or result
        Assertions.assertThat(deletedEmployee).isEmpty();
    }

    // JUnit test for get all employees operator
    @Test
    public void giveEmployeeList_whenFindAll_thenEmployeesList() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        // when - action or behavior then we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output or result
        Assertions.assertThat(employeeList).isNotNull();
        Assertions.assertThat(employeeList.size()).isEqualTo(2);
    }

    // JUnit test for get employee by email operator
    @DisplayName("JUnit test for get employee by email operator")
    @Test
    public void giveEmployeeEmail_whenFindByEmail_thenReturnEmailObject() {
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe")
                .build();
        employeeRepository.save(employee);

        // when - action or behavior then we are going to test
        Optional<Employee> employeeByEmail = employeeRepository.findByEmail(employee.getEmail());

        // then - verify the output or result
        Assertions.assertThat(employeeByEmail).isNotNull();
        Assertions.assertThat(employeeByEmail.get().getEmail()).isEqualTo(employee.getEmail());
    }

    // JUnit test for update employee operator
    @DisplayName("JUnit test for update employee operator")
    @Test
    public void giveEmployeeObject_whenUpdateEmployee_thenReturnUpdateEmployee() {
        // given - precondition or setup
        Employee employee = Employee.builder()
               .firstName("John")
               .lastName("Doe")
               .email("john.doe")
               .build();
        employeeRepository.save(employee);

        // when - action or behavior then we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setFirstName("Jane");
        savedEmployee.setEmail("jane@gmail.com");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output or result
        Assertions.assertThat(updatedEmployee).isNotNull();
        Assertions.assertThat(updatedEmployee.getFirstName()).isEqualTo("Jane");
        Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("jane@gmail.com");
    }

    // JUnit test for get employee by id operator
    @DisplayName("JUnit test for get employee by id operator")
    @Test
    public void giveEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        // given - precondition or setup
        Employee employee = Employee.builder()
               .firstName("John")
               .lastName("Doe")
               .email("john.doe")
               .build();
        employeeRepository.save(employee);

        // when - action or behavior then we are going to test
        Employee employeeById = employeeRepository.findById(employee.getId()).get();

        // then - verify the output or result
        Assertions.assertThat(employeeById).isNotNull();
    }
}

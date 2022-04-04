package com.belf33.spring.rest;

import com.belf33.spring.rest.entity.Employee;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/SpringRest/api/employees";

    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> response =
                restTemplate.exchange(BASE_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});

        List<Employee> allEmployees = response.getBody();
        return allEmployees;
    }

    public Employee getEmployee(int id) {
        Employee employee = restTemplate.getForObject(BASE_URL + "/" + id, Employee.class);
        return employee;
    }

    public void saveOrUpdateEmployee(Employee employee) {
        int id = employee.getId();

        if (id == 0) {
            ResponseEntity<String> entity = restTemplate.postForEntity(BASE_URL, employee, String.class);
            System.out.println("New employee was added to DB");
            System.out.println(entity.getBody());
        } else {
            restTemplate.put(BASE_URL, employee);
            System.out.println("Employee with id " + id + " was updated");
        }
    }

    public void deleteEmployee(int id) {
        restTemplate.delete(BASE_URL + "/" + id);
        System.out.println("Employee with id " + id + " was deleted");

    }

}

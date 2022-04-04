package com.belf33.spring.rest;

import com.belf33.spring.rest.configuration.MyConfig;
import com.belf33.spring.rest.entity.Employee;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);
        List<Employee> employeeList = communication.getAllEmployees();
        System.out.println(employeeList);

        Employee employee = new Employee("maria", "belaia", "qa", 1500);
        communication.saveOrUpdateEmployee(employee);;

    }
}

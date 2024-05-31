package org.example.mockproject_052024_group1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class MockProject052024Group1Application {

    public static void main(String[] args) {
        SpringApplication.run(MockProject052024Group1Application.class, args);
    }

}

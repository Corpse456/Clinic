package by.gp.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"by.gp.clinic"})
public class ClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicApplication.class, args);
    }

}

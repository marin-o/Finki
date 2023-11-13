package mk.finki.ukim.mk.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Lab2AApplication {
    public static void main( String[] args ) {
        SpringApplication.run(Lab2AApplication.class, args);
    }
}

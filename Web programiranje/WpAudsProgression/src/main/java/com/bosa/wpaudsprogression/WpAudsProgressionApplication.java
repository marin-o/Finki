package com.bosa.wpaudsprogression;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WpAudsProgressionApplication {

    public static void main( String[] args ) {
        SpringApplication.run(WpAudsProgressionApplication.class, args);
    }

}

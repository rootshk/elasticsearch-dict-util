package top.roothk.ikdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class IkDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IkDemoApplication.class, args);
    }
}

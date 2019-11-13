package pl.ejaksla.onlinejudge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableAspectJAutoProxy
public class OnlinejudgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinejudgeApplication.class, args);
    }

}


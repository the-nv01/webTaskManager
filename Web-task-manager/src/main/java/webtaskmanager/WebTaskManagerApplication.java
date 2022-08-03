package webtaskmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("webtaskmanager.mapper")
public class WebTaskManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebTaskManagerApplication.class, args);
    }

}

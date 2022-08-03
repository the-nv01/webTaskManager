package webtaskmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("webtaskmanager.mapper")
public class WebTaskManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebTaskManagerApplication.class, args);
    }

}

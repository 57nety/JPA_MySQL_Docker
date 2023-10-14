package edu.application.mysql;

import edu.application.mysql.row.RowService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class MysqlApplication {

    private final RowService rowService;

    public static void main(String[] args) {
        SpringApplication.run(MysqlApplication.class, args);
    }

    @PostConstruct
    public void init(){
        this.rowService.create("test1");
        this.rowService.create("test2");
        this.rowService.create("test3");
        this.rowService.create("test4");
        this.rowService.delete(Arrays.asList(1L, 2L));
        this.rowService.getByIds(Arrays.asList(3L, 4L))
                .forEach(row -> log.info("Row#{}: {}", row.getId(), row.getTitle()));
    }
}

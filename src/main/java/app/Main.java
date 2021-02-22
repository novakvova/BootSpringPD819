package app;

import app.seeder.SeederDb;
import app.storage.StorageProperties;
import app.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService, SeederDb seederDb) {
        return (args) -> {
            //storageService.deleteAll();
            try {
                seederDb.SeedAllTabels();
                storageService.init();
            }
            catch(Exception ex) {
                System.out.println("----propblem cteate folder--------");
            }
        };
    }

}

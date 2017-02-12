package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private HelloRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String s = saveInDbAndReturn("some message");
        System.out.println(s);
    }

    private String saveInDbAndReturn(String message) {
        repository.deleteAll();
        repository.save(new Hello(message));
        Hello fromDb = repository.findByMessage(message);
        return fromDb.getMessage() + "from DB!";
    }

}
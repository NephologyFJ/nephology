package nephology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private HelloRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        this.repository.save(new Hello("Hello1"));
        this.repository.save(new Hello("Hello2"));
        this.repository.save(new Hello("Hello3"));
        this.repository.save(new Hello("Hello4"));
    }

    private String saveInDbAndReturn(String message) {
        repository.save(new Hello(message));
        Hello fromDb = repository.findByMessage(message);
        return fromDb.getMessage() + "from DB!";
    }

}
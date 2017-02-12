package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses=HelloRepository.class)
public class Application implements CommandLineRunner {

    @Autowired
    private HelloRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        Hello hello1 = new Hello();
        Hello hello2 = new Hello();

        hello1.setMessage("message1");
        hello2.setMessage("message2");
        repository.save(hello1);
        repository.save(hello2);

        System.out.println("Objects found with findAll():");
        System.out.println("-------------------------------");
        for (Hello customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        System.out.println("Hello found with findByMessage('message2'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByMessage("message2"));

    }

}
package nephology;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private HelloRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        logger.debug("This is DEBUG from logger");
        logger.info("This is INFO from logger");
        logger.error("This is ERROR from logger");
        logger.warn("This is WARN from logger");
        logger.trace("This is TRACE from logger");

        this.repository.save(new Hello("User1","Hello1"));
        this.repository.save(new Hello("User2","Hello2"));
        this.repository.save(new Hello("User3","Hello3"));
        this.repository.save(new Hello("User4","Hello4"));
    }

    private String saveInDbAndReturn(String userName, String message) {
        repository.save(new Hello(userName,message));
        Hello fromDb = repository.findByMessage(message);
        return fromDb.getMessage() + "from DB!";
    }

}
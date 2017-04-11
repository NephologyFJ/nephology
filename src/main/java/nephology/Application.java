package nephology;

import java.util.List;

import com.amazonaws.services.ec2.model.Instance;
import nephology.aws.ec2.EC2InstanceDetails;
import nephology.aws.ec2.domain.AwsEC2InstanceDetailsData;
import nephology.properties.CustomPropertyReader;
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
    private HelloRepository helloRepository;

    @Autowired
    private AwsEC2InstanceDetailsDataRepository awsRepository;

    @Autowired
    private CustomPropertyReader cpr;

    @Autowired
    private EC2InstanceDetails ec2Details;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        helloRepository.deleteAll();
        logger.debug("This is DEBUG from logger");
        logger.info("This is INFO from logger");
        logger.error("This is ERROR from logger");
        logger.warn("This is WARN from logger");
        logger.trace("This is TRACE from logger");
        logger.info("************");
        logger.info(cpr.getSample());
        logger.info("************");

        this.helloRepository.save(new Hello("Hello1"));
        this.helloRepository.save(new Hello("Hello2"));
        this.helloRepository.save(new Hello("Hello3"));
        this.helloRepository.save(new Hello("Hello4"));

        retrieveAwsInstancesAndSaveInDb();
    }

    private String saveInDbAndReturn(String message) {
        helloRepository.save(new Hello(message));
        Hello fromDb = helloRepository.findByMessage(message);
        return fromDb.getMessage() + "from DB!";
    }

    private void retrieveAwsInstancesAndSaveInDb() {
        awsRepository.deleteAll();
        List<Instance> allInstances = ec2Details.getAllInstances();

        for (Instance retrievedInstance : allInstances) {
            AwsEC2InstanceDetailsData convertedInstance = new AwsEC2InstanceDetailsData(retrievedInstance);
            awsRepository.save(convertedInstance);
        }
    }

}
package org.nephology;

import java.util.List;

import com.amazonaws.services.ec2.model.Instance;
import org.nephology.aws.ec2.EC2InstanceDetails;
import org.nephology.aws.ec2.domain.AwsEC2InstanceDetailsData;
import org.nephology.properties.CustomPropertyReader;
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

        this.helloRepository.save(new Hello("User1","Hello1"));
        this.helloRepository.save(new Hello("User2","Hello2"));
        this.helloRepository.save(new Hello("User3","Hello3"));
        this.helloRepository.save(new Hello("User4","Hello4"));

        retrieveAwsInstancesAndSaveInDb();
    }

    private String saveInDbAndReturn(String userName, String message) {
        helloRepository.save(new Hello(userName,message));
        Hello fromDb = helloRepository.findByMessage(message);
        return fromDb.getMessage() + "from DB!";
    }

    private void retrieveAwsInstancesAndSaveInDb() {
        awsRepository.deleteAll();
        List<Instance> allInstances = ec2Details.getAllInstances();

        for (Instance retrievedInstance : allInstances) {
            AwsEC2InstanceDetailsData convertedInstance = new AwsEC2InstanceDetailsData();
            convertedInstance.setImageId(retrievedInstance.getImageId());
            convertedInstance.setInstanceId(retrievedInstance.getInstanceId());
            convertedInstance.setKeyName(retrievedInstance.getKeyName());
            convertedInstance.setInstanceType(retrievedInstance.getInstanceType());
            convertedInstance.setPrivateIpAddress(retrievedInstance.getPrivateIpAddress());
            convertedInstance.setPublicIpAddress(retrievedInstance.getPublicIpAddress());
            convertedInstance.setSubnetId(retrievedInstance.getSubnetId());
            awsRepository.save(convertedInstance);
        }
    }

}
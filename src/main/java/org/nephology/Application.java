package org.nephology;

import java.util.List;
import java.util.Locale;

import com.amazonaws.services.ec2.model.Instance;
import org.nephology.aws.ec2.EC2Service;
import org.nephology.aws.ec2.domain.AwsEC2InstanceDetailsConverter;
import org.nephology.aws.ec2.domain.AwsEC2InstanceDetailsDataRepository;
import org.nephology.azure.computemanagement.AzureManagementService;
import org.nephology.azure.computemanagement.domain.AzureVirtualMachineData;
import org.nephology.azure.computemanagement.domain.AzureVirtualMachineDataRepository;
import org.nephology.properties.CustomPropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private HelloRepository helloRepository;

    @Autowired
    private AwsEC2InstanceDetailsDataRepository awsRepository;

    @Autowired
    private AzureVirtualMachineDataRepository azureRepository;

    @Autowired
    private CustomPropertyReader cpr;

    @Autowired
    private EC2Service ec2Service;

    @Autowired
    private AzureManagementService azureService;

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
        retrieveAzureInstancesAndSaveInDb();
    }

    private String saveInDbAndReturn(String userName, String message) {
        helloRepository.save(new Hello(userName,message));
        Hello fromDb = helloRepository.findByMessage(message);
        return fromDb.getMessage() + "from DB!";
    }

    private void retrieveAwsInstancesAndSaveInDb() {
        awsRepository.deleteAll();
        List<Instance> allInstances = ec2Service.getAllInstances();
        for (Instance retrievedInstance : allInstances) {
            awsRepository.save(AwsEC2InstanceDetailsConverter.convert(retrievedInstance));
        }
    }

    private void retrieveAzureInstancesAndSaveInDb() {
        azureRepository.deleteAll();
        final List<AzureVirtualMachineData> azureVirtualMachineDatas = azureService.listAllVirtualMachines();
        for (AzureVirtualMachineData retrievedInstance : azureVirtualMachineDatas) {
            azureRepository.save(retrievedInstance);
        }
    }
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
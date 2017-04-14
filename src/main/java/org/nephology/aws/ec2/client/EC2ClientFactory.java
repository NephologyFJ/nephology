package org.nephology.aws.ec2.client;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import org.nephology.properties.CustomPropertyReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by PLGrubskiM on 2017-04-14.
 */
@Service
public class EC2ClientFactory {

    @Autowired
    private CustomPropertyReader cpr;

    protected AmazonEC2 getClasspathClient(String region) {
        AWSCredentialsProvider credentials = getClasspathCredentials();
        AmazonEC2 client;
        if (cpr.isUseAwsProxy()) {
            client = AmazonEC2ClientBuilder
                    .standard()
                    .withRegion(region)
                    .withCredentials(credentials)
                    .withClientConfiguration(getClientConfiguration())
                    .build();
        } else {
            client = AmazonEC2ClientBuilder
                    .standard()
                    .withRegion(region)
                    .withCredentials(credentials)
                    .build();
        }
        return client;
    }

    protected AmazonEC2 getStaticClient(String region, String accessKey, String secretKey) {
        AmazonEC2 client;
        BasicAWSCredentials credentials = getBasicAWSCredentials(accessKey, secretKey);
        if (cpr.isUseAwsProxy()) {
            client = AmazonEC2ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(region)
                    .withClientConfiguration(getClientConfiguration())
                    .build();
        } else {
            client = AmazonEC2ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(region)
                    .build();
        }

        return client;
    }

    protected BasicAWSCredentials getBasicAWSCredentials(String accessKey, String secretKey) {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    protected ClasspathPropertiesFileCredentialsProvider getClasspathCredentials() {
        return new ClasspathPropertiesFileCredentialsProvider();
    }

    protected ClientConfiguration getClientConfiguration() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProxyHost(cpr.getProxyHost());
        clientConfiguration.setProxyPort(cpr.getProxyPort());
        return clientConfiguration;
    }
}

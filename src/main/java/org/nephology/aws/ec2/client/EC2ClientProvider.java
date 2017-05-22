package org.nephology.aws.ec2.client;

import com.amazonaws.services.ec2.AmazonEC2;
import org.nephology.Application;
import org.nephology.properties.CustomPropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PLGrubskiM on 2017-04-06.
 */
@Component
public class EC2ClientProvider {

    private final Logger logger = LoggerFactory.getLogger(Application.class);

    private static final String REGION_DEFAULT = "us-west-2";

    @Autowired
    private EC2ClientFactory factory;

    private String accessKey;
    private String secretKey;
    private String region;
    private boolean withProxy;

    /**
     * @return suitable AmazonEC2 client based on available credentials.
     * If accessKey and secret are set, they are used.
     * If not, these are taken from AwsCredentials.properties file.
     */
    public AmazonEC2 getClient() {
        if (region == null || region.isEmpty()) {
            logger.info("EC2ClientProvider.getClient():AWS region not provided, falling back to the default value: " + REGION_DEFAULT);
            region = REGION_DEFAULT;
        }
        return factory.getStaticClient(region, accessKey, secretKey);
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isWithProxy() {
        return withProxy;
    }

    public void setWithProxy(boolean withProxy) {
        this.withProxy = withProxy;
    }

}

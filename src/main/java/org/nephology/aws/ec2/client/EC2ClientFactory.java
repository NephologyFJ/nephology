package org.nephology.aws.ec2.client;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import org.nephology.aws.ec2.exception.EC2Exception;
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

    protected AmazonEC2 getStaticClient(String region, String accessKey, String secretKey) throws EC2Exception {
        AmazonEC2 client;
        BasicAWSCredentials credentials = getBasicAWSCredentials(accessKey, secretKey);
        if (cpr.isUseAwsProxy()) {
            client = AmazonEC2ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(region)
                    .withClientConfiguration(getClientConfigurationWithProxy())
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

    protected ClientConfiguration getClientConfigurationWithProxy() throws EC2Exception {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        final String awsProxyHost = cpr.getAwsProxyHost();
        final int awsProxyPort = cpr.getAwsProxyPort();
        if (awsProxyHost == null || awsProxyHost.isEmpty()) {
            throw new EC2Exception("AWS client was selected to use proxy, but the proxy host was not specified");
        }
        if (awsProxyPort == 0) {
            throw new EC2Exception("AWS client was selected to use proxy, but the proxy port was not specified");
        }
        clientConfiguration.setProxyHost(awsProxyHost);
        clientConfiguration.setProxyPort(awsProxyPort);
        return clientConfiguration;
    }
}

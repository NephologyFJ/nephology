package nephology.aws.ec2;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;

/**
 * Created by PLGrubskiM on 2017-04-06.
 */
public class EC2ClientProvider {

    private static final String REGION_DEFAULT = "us-west-2";

    private String accessKey;
    private String secretKey;
    private String region;

    public EC2ClientProvider() {
        this.region = REGION_DEFAULT;
    }

    public EC2ClientProvider(String region) {
        this.region = region;
    }

    public EC2ClientProvider(String accessKey, String secretKey) {
        this.region = REGION_DEFAULT;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public EC2ClientProvider(String accessKey, String secretKey, String region) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.region = region;
    }

    /**
     * @return suitable AmazonEC2 client based on available credentials.
     * If accessKey and secret are provided, they are used.
     * If not, these are taken from AwsCredentials.properties file.
     */
    protected AmazonEC2 getClient() {
        if (credentialsEmpty()) {
            return getClasspathClient();
        } else {
            return getStaticClient();
        }
    }

    protected AmazonEC2 getClasspathClient() {
        AWSCredentialsProvider credentials = getClasspathCredentials();
        return AmazonEC2ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(credentials)
                .build();
    }

    protected AmazonEC2 getStaticClient() {
        BasicAWSCredentials credentials = getBasicAWSCredentials();
        AmazonEC2 client = AmazonEC2ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        return client;
    }

    protected BasicAWSCredentials getBasicAWSCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    protected ClasspathPropertiesFileCredentialsProvider getClasspathCredentials() {
        return new ClasspathPropertiesFileCredentialsProvider();
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

    private boolean credentialsEmpty() {
        if (accessKey == null || secretKey == null) {
            return true;
        }
        if (accessKey.length() <= 0 && secretKey.length() <= 0) {
            return true;
        }
        return false;
    }
}

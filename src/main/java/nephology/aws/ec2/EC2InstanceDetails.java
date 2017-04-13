package nephology.aws.ec2;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PLGrubskiM on 2017-04-03.
 */
public class EC2InstanceDetails {

    private final Logger logger = LoggerFactory.getLogger(EC2InstanceDetails.class);

    private static final String REGION_DEFAULT = "us-west-2";

    private AmazonEC2 ec2;
    private String region;

    public EC2InstanceDetails() {
        this(REGION_DEFAULT);
    }

    public EC2InstanceDetails(String region) {
        this.region = region;
    }

    public List<Instance> getAllInstances() {
        ec2 = getAmazonEC2DefaultClient();
        boolean done = false;
        List<Instance> instanceList = new ArrayList<Instance>();
        while (!done) {
            DescribeInstancesRequest request = new DescribeInstancesRequest();
            DescribeInstancesResult response = ec2.describeInstances(request);

            for (Reservation reservation : response.getReservations()) {
                for (Instance instance : reservation.getInstances()) {
                    instanceList.add(instance);
                }
            }

            request.setNextToken(response.getNextToken());

            if (response.getNextToken() == null) {
                done = true;
            }
        }
        if (instanceList.isEmpty()) {
            logger.info("No instances found with getAllInstances().");
        }

        return instanceList;
    }

    protected AmazonEC2 getAmazonEC2DefaultClient() {
        AWSCredentialsProvider credentials = getClasspathCredentials();
        AmazonEC2 client = getStandardClientWithRegion(credentials);
        return client;
    }

    protected AmazonEC2 getStandardClientWithRegion(AWSCredentialsProvider credentials) {
        return AmazonEC2ClientBuilder.standard().withRegion(region).withCredentials(credentials).build();
    }

    protected ClasspathPropertiesFileCredentialsProvider getClasspathCredentials() {
        return new ClasspathPropertiesFileCredentialsProvider();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}

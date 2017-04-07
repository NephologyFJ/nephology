package nephology.aws.ec2;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
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

    private EC2ClientProvider ec2ClientProvider;

    public EC2InstanceDetails(EC2ClientProvider ec2ClientProvider) {
        this.ec2ClientProvider = ec2ClientProvider;
    }

    public List<Instance> getAllInstances() {
        final AmazonEC2 ec2 = ec2ClientProvider.getClient();
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

}

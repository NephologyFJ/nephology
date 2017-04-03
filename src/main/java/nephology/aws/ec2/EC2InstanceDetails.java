package nephology.aws.ec2;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

/**
 * Created by PLGrubskiM on 2017-04-03.
 */
public class EC2InstanceDetails {
    final AmazonEC2 ec2 = getAmazonEC2DefaultClient();

    boolean done = false;

    protected void getInstances() {

        while (!done) {
            DescribeInstancesRequest request = new DescribeInstancesRequest();
            DescribeInstancesResult response = ec2.describeInstances(request);

            for (Reservation reservation : response.getReservations()) {
                for (Instance instance : reservation.getInstances()) {
                    System.out.printf(
                            "Found reservation with id %s, " +
                                    "AMI %s, " +
                                    "type %s, " +
                                    "state %s " +
                                    "and monitoring state %s",
                            instance.getInstanceId(),
                            instance.getImageId(),
                            instance.getInstanceType(),
                            instance.getState().getName(),
                            instance.getMonitoring().getState());
                }
            }

            request.setNextToken(response.getNextToken());

            if (response.getNextToken() == null) {
                done = true;
            }
        }
    }

    protected AmazonEC2 getAmazonEC2DefaultClient() {
        AWSCredentialsProvider credentials = new ClasspathPropertiesFileCredentialsProvider();
        AmazonEC2 client = AmazonEC2ClientBuilder.standard().withRegion("us-west-2").withCredentials(credentials).build();
        return client;
    }
}

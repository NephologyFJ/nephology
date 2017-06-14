package org.nephology.aws.ec2;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import org.nephology.aws.ec2.client.EC2ClientProvider;
import org.nephology.aws.ec2.exception.EC2Exception;
import org.nephology.properties.CustomPropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PLGrubskiM on 2017-04-03.
 */
@Component
public class EC2Service {

    private final Logger logger = LoggerFactory.getLogger(EC2Service.class);

    @Autowired
    private EC2ClientProvider ec2ClientProvider;

    @Autowired
    private CustomPropertyReader cpr;

    public EC2Service() {
    }

    public List<Instance> getAllInstances() throws EC2Exception {

        prepareAwsEC2Client();

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

    private void prepareAwsEC2Client() throws EC2Exception {

        final String awsKey = cpr.getAwsKey();
        final String awsSecret = cpr.getAwsSecret();
        final String region = cpr.getAwsRegion();

        if (!awsKey.isEmpty() && !awsSecret.isEmpty()) {
            ec2ClientProvider.setAccessKey(awsKey);
            ec2ClientProvider.setSecretKey(awsSecret);
        } else if (awsKey.isEmpty()) {
            throw new EC2Exception("AWS Key was not provided.");
        } else {
            throw new EC2Exception("AWS Secret was not provided.");
        }
        if (!region.isEmpty()) {
            logger.info("connecting to AWS service with region: " + region);
            ec2ClientProvider.setRegion(region);
        }

    }

}

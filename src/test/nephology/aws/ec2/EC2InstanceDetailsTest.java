package nephology.aws.ec2;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by PLGrubskiM on 2017-04-03.
 */
public class EC2InstanceDetailsTest {

    EC2InstanceDetails ec2Details;

    private int numberOfRetrievedReservations = 1;
    private int numberOfInstancesInEachReservation = 1;

    @Before
    public void setUp() {

        ec2Details = new EC2InstanceDetails() {

            AmazonEC2 mockEC2;

            @Override
            protected AmazonEC2 getAmazonEC2DefaultClient() {
                mockEC2 = mock(AmazonEC2.class);
                DescribeInstancesResult instancesMock = mock(DescribeInstancesResult.class);
                List<Reservation> mockReservations = createReservationsMock();
                when(instancesMock.getReservations()).thenReturn(mockReservations);
                when(mockEC2.describeInstances(any(DescribeInstancesRequest.class)))
                        .thenReturn(instancesMock);
                return mockEC2;
            }
        };
    }

    @Test
    public void getAllInstancesTest() {
        // given
        numberOfRetrievedReservations = 10;
        numberOfInstancesInEachReservation = 10;
        // when
        List<Instance> allInstances = ec2Details.getAllInstances();
        // then
        // no exceptions
        assertTrue(allInstances.size() == 100);
    }

    @Test
    public void getAllInstancesTest_empty() {
        // given
        numberOfRetrievedReservations = 5;
        numberOfInstancesInEachReservation = 0;
        // when
        List<Instance> allInstances = ec2Details.getAllInstances();
        // then
        // no exceptions
        assertTrue(allInstances.isEmpty());
    }

    @Test
    public void getAmazonEC2DefaultClientTest() {
        // given
        ec2Details = new EC2InstanceDetails();
        // when
        AmazonEC2 amazonEC2DefaultClient = ec2Details.getAmazonEC2DefaultClient();
        // then
        assertTrue(amazonEC2DefaultClient != null);
    }

    @Test
    public void defaultRegionTest() {
        // given
        ec2Details = new EC2InstanceDetails();
        // when
        final String defaultRegion = ec2Details.getRegion();
        final String secondRegion = "newRegion";
        ec2Details.setRegion(secondRegion);
        final String regionAfterChange = ec2Details.getRegion();
        // then
        assertTrue(defaultRegion.equals("us-west-2"));
        assertTrue(regionAfterChange.equals("newRegion"));
    }

    private List<Reservation> createReservationsMock() {

        List<Reservation> reservations = new ArrayList<Reservation>();

        for (int i = 0; i < numberOfRetrievedReservations; i++) {
            Reservation reservation = mock(Reservation.class);
            List<Instance> mockInstances = createInstancesMock();
            when(reservation.getInstances()).thenReturn(mockInstances);
            reservations.add(reservation);
        }
        return reservations;
    }

    private List<Instance> createInstancesMock() {
        List<Instance> instances = new ArrayList<Instance>();
        for (int i = 0; i < numberOfInstancesInEachReservation; i++) {
            Instance instance = mock(Instance.class);
            instances.add(instance);
        }
        return instances;
    }

}

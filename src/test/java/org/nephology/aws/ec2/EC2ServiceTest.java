package org.nephology.aws.ec2;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.nephology.aws.ec2.client.EC2ClientProvider;
import org.nephology.aws.ec2.exception.EC2Exception;
import org.nephology.properties.CustomPropertyReader;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

/**
 * Created by PLGrubskiM on 2017-06-08.
 */
public class EC2ServiceTest {

    @InjectMocks
    private EC2Service ec2Service;

    @Mock
    private CustomPropertyReader cpr;

    @Mock
    private EC2ClientProvider ec2ClientProvider;

    // Mocks (not autowired)
    AmazonEC2 mockClient;
    DescribeInstancesResult mockResponse;
    List<Reservation> mockReservationsList;
    Reservation mockReservation;
    List<Instance> mockInstances;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(cpr.getAwsKey()).thenReturn("someKey");
        when(cpr.getAwsSecret()).thenReturn("someSecret");
        when(cpr.getAwsRegion()).thenReturn("someRegion");
    }

    @Test
    public void getAllInstancesTest() throws EC2Exception {
        // given
        prepareMocks();
        // when
        final List<Instance> allInstances = ec2Service.getAllInstances();
        // then
        Assert.assertTrue(allInstances.size() == 1);
    }

    @Test(expected = EC2Exception.class)
    public void getAllInstancesTest_emptyKey() throws EC2Exception {
        // given
        prepareMocks();
        when(cpr.getAwsKey()).thenReturn("");
        // when
        final List<Instance> allInstances = ec2Service.getAllInstances();
        // then
        Assert.assertTrue(allInstances.size() == 1);
    }

    @Test(expected = EC2Exception.class)
    public void getAllInstancesTest_emptySecret() throws EC2Exception {
        // given
        prepareMocks();
        when(cpr.getAwsSecret()).thenReturn("");
        // when
        final List<Instance> allInstances = ec2Service.getAllInstances();
        // then
        Assert.assertTrue(allInstances.size() == 1);
    }

    private void prepareMocks() throws EC2Exception {
        mockClient = mock(AmazonEC2.class);
        mockResponse = mock(DescribeInstancesResult.class);
        mockReservationsList = new ArrayList<Reservation>();
        mockReservation = mock(Reservation.class);
        mockInstances = new ArrayList<Instance>();
        mockInstances.add(new Instance());
        when(mockReservation.getInstances()).thenReturn(mockInstances);
        mockReservationsList.add(mockReservation);
        when(mockResponse.getReservations()).thenReturn(mockReservationsList);
        when(mockClient.describeInstances(any(DescribeInstancesRequest.class))).thenReturn(mockResponse);
        when(ec2ClientProvider.getClient()).thenReturn(mockClient);
    }
}

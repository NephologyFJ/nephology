package org.nephology.aws.ec2.client;

import com.amazonaws.services.ec2.AmazonEC2;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.nephology.aws.ec2.exception.EC2Exception;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * Created by PLGrubskiM on 2017-06-09.
 */
public class EC2ClientProviderTest {

    @InjectMocks
    EC2ClientProvider ec2ClientProvider;

    @Mock
    EC2ClientFactory ec2ClientFactory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getClientTest_defaultRegion() throws EC2Exception {
        // given
        // when
        ec2ClientProvider.getClient();
        // then
        assertTrue(ec2ClientProvider.getRegion().equals("us-west-2"));
    }

    @Test
    public void getClientTest_providedRegion() throws EC2Exception {
        // given
        ec2ClientProvider.setRegion("mockedRegion");
        // when
        final AmazonEC2 client = ec2ClientProvider.getClient();
        // then
        verify(ec2ClientFactory, times(1)).getStaticClient(any(String.class), any(String.class), any(String.class));
    }
}

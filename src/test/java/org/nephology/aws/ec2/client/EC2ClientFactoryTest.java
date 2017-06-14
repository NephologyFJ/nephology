package org.nephology.aws.ec2.client;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.nephology.aws.ec2.exception.EC2Exception;
import org.nephology.properties.CustomPropertyReader;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by PLGrubskiM on 2017-06-09.
 */
public class EC2ClientFactoryTest {

    @Spy
    @InjectMocks
    private EC2ClientFactory ec2ClientFactory;

    @Mock
    CustomPropertyReader cpr;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = SdkClientException.class)
    public void getStaticClientTest_invalidRegion() throws EC2Exception {
        // given
        // when
        String region = "mockedRegion";
        String accessKey = "mockedAccessKey";
        String secretKey = "mockedSecretKey";
        ec2ClientFactory.getStaticClient(region, accessKey, secretKey);
        // then
    }

    @Test
    public void getStaticClientTest_withProxy() throws EC2Exception {
        // given
        when(cpr.isUseAwsProxy()).thenReturn(true);
        when(cpr.getAwsProxyHost()).thenReturn("mockedHost");
        when(cpr.getAwsProxyPort()).thenReturn(80);
        // when
        String region = "us-west-2";
        String accessKey = "mockedAccessKey";
        String secretKey = "mockedSecretKey";
        ec2ClientFactory.getStaticClient(region, accessKey, secretKey);
        // then
        verify(ec2ClientFactory, times(1)).getClientConfigurationWithProxy();
    }

    @Test(expected = EC2Exception.class)
    public void getStaticClientTest_withProxy_noHost() throws EC2Exception {
        // given
        when(cpr.isUseAwsProxy()).thenReturn(true);
        when(cpr.getAwsProxyPort()).thenReturn(80);
        // when
        String region = "us-west-2";
        String accessKey = "mockedAccessKey";
        String secretKey = "mockedSecretKey";
        ec2ClientFactory.getStaticClient(region, accessKey, secretKey);
        // then
    }

    @Test(expected = EC2Exception.class)
    public void getStaticClientTest_withProxy_noPort() throws EC2Exception {
        // given
        when(cpr.isUseAwsProxy()).thenReturn(true);
        when(cpr.getAwsProxyHost()).thenReturn("mockedHost");
        // when
        String region = "us-west-2";
        String accessKey = "mockedAccessKey";
        String secretKey = "mockedSecretKey";
        ec2ClientFactory.getStaticClient(region, accessKey, secretKey);
        // then
        verify(ec2ClientFactory, times(1)).getClientConfigurationWithProxy();
    }

    @Test
    public void getStaticClientTest_noProxy() throws EC2Exception {
        // given
        when(cpr.isUseAwsProxy()).thenReturn(false);
        when(cpr.getAwsProxyHost()).thenReturn("mockedHost");
        when(cpr.getAwsProxyPort()).thenReturn(80);
        // when
        String region = "us-west-2";
        String accessKey = "mockedAccessKey";
        String secretKey = "mockedSecretKey";
        ec2ClientFactory.getStaticClient(region, accessKey, secretKey);
        // then
        verify(ec2ClientFactory, times(1)).getClientWithoutProxy(any(String.class), any(BasicAWSCredentials.class));
    }
}

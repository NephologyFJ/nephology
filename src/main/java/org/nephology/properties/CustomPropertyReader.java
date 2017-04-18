package org.nephology.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by PLGrubskiM on 2017-03-26.
 */
@Component
public class CustomPropertyReader {

    /**
     * Example property
     */
    @Value("${custom.sample:default}")
    private String sample;

    /**
     * Properties for AWS configuration
     */

    /**
     * Use proxy when creating AWS client
     */
    @Value("${aws.useProxy:false}")
    private boolean useAwsProxy;

    /**
     * Proxy host for AWS client
     */
    @Value("${aws.proxyHost:}")
    private String awsProxyHost;

    /**
     * Proxy port for AWS client
     */
    @Value("${aws.proxyPort:}")
    private int awsProxyPort;

    /**
     * Key for AWS access
     */
    @Value("${aws.key:}")
    private String awsKey;

    /**
     * Secret for AWS access
     */
    @Value("${aws.secret:}")
    private String awsSecret;

    /**
     * Region for AWS access
     */
    @Value("${aws.region:}")
    private String awsRegion;

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public boolean isUseAwsProxy() {
        return useAwsProxy;
    }

    public void setUseAwsProxy(boolean useAwsProxy) {
        this.useAwsProxy = useAwsProxy;
    }

    public String getAwsProxyHost() {
        return awsProxyHost;
    }

    public void setAwsProxyHost(String awsProxyHost) {
        this.awsProxyHost = awsProxyHost;
    }

    public int getAwsProxyPort() {
        return awsProxyPort;
    }

    public void setAwsProxyPort(int awsProxyPort) {
        this.awsProxyPort = awsProxyPort;
    }

    public String getAwsKey() {
        return awsKey;
    }

    public void setAwsKey(String awsKey) {
        this.awsKey = awsKey;
    }

    public String getAwsSecret() {
        return awsSecret;
    }

    public void setAwsSecret(String awsSecret) {
        this.awsSecret = awsSecret;
    }

    public String getAwsRegion() {
        return awsRegion;
    }

    public void setAwsRegion(String awsRegion) {
        this.awsRegion = awsRegion;
    }
}

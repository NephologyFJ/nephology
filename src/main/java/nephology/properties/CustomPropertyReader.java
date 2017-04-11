package nephology.properties;

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
    @Value("${custom.sample}")
    private String sample;

    /**
     * Properties for AWS configuration
     */

    /**
     * Use proxy when creating AWS client
     */
    @Value("${aws.useAwsProxy}")
    private boolean useAwsProxy;

    /**
     * Proxy host for AWS client
     */
    @Value("${aws.proxyHost:}")
    private String proxyHost;

    /**
     * Proxy port for AWS client
     */
    @Value("${aws.proxyPort:0}")
    private int proxyPort;

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

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }
}

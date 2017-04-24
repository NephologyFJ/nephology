package org.nephology.azure.computemanagement.client;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.naming.ServiceUnavailableException;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.management.configuration.ManagementConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by PLGrubskiM on 2017-04-24.
 */
@Component
public class AzureClientProvider {

    private final Logger logger = LoggerFactory.getLogger(AzureClientProvider.class);

    private String tenantId;
    private String clientId;
    private String clientSecret;
    private String proxyHost;
    private String proxyPort;
    private String subscriptionId;

    private static final String AZURE_LOGIN_URL = "https://login.microsoftonline.com/";
    private static final String AZURE_MGMT_URL = "https://management.core.windows.net/";


    public AuthenticationResult getAuthentication() throws MalformedURLException,
            InterruptedException, ExecutionException,
            ServiceUnavailableException {
        AuthenticationResult result = null;
        ExecutorService service = Executors.newFixedThreadPool(1);
        try {
            AuthenticationContext context = new AuthenticationContext(AZURE_LOGIN_URL + tenantId, false, service);
            context.setProxy(createProxy());
            ClientCredential clientCredential = new ClientCredential(clientId, clientSecret);
            result = context.acquireToken(AZURE_MGMT_URL, clientCredential, null).get();
        } catch (Exception e) {
            logger.error("Exception during Azure authentication: " + e);
        } finally {
            service.shutdown();
        }

        if (result == null) {
            throw new ServiceUnavailableException(
                    "Authentication result was null");
        }
        return result;
    }

    private Configuration createConfiguration() {
        AuthenticationResult result = null;
        Configuration config = null;
        try {
            result = getAuthentication();
            config = ManagementConfiguration.configure(null, new URI(
                    AZURE_MGMT_URL), subscriptionId, result
                    .getAccessToken());

            if (useAzureProxy()) {
                config.setProperty(Configuration.PROPERTY_HTTP_PROXY_HOST,
                        proxyHost);
                config.setProperty(Configuration.PROPERTY_HTTP_PROXY_PORT,
                        Integer.parseInt(proxyPort));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == null) {
            logger.error("Azure authentication was null");
        }
        if (config == null) {
            logger.error("Azure configuration was null");
        }
        return config;
    }

    private boolean useAzureProxy() {
        return true;
    }

    private Proxy createProxy() {
        Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyHost,
                Integer.parseInt(proxyPort)));
        return proxy;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}

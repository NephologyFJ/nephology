package org.nephology.azure.computemanagement.client;

import com.microsoft.azure.AzureEnvironment;
import com.microsoft.azure.credentials.ApplicationTokenCredentials;
import com.microsoft.azure.management.Azure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by PLGrubskiM on 2017-04-24.
 */
@Component
public class AzureClientProvider {

    private final Logger logger = LoggerFactory.getLogger(AzureClientProvider.class);

    private String client;
    private String tenant;
    private String secret;
    private String subscriptionId;

    public Azure getClient() {
        validateParameters();
        ApplicationTokenCredentials credentials = new ApplicationTokenCredentials(client, tenant, secret, AzureEnvironment.AZURE);
        return Azure.authenticate(credentials).withSubscription(subscriptionId);
    }

    private void validateParameters() {
        if (client == null || tenant == null || secret == null || subscriptionId == null
                || client.isEmpty() || tenant.isEmpty() || secret.isEmpty() || subscriptionId.isEmpty()) {
            logger.warn("At least one of the required Azure parameters was not provided: 'client', 'tenant','secret','subscriptionId'");
        }
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}

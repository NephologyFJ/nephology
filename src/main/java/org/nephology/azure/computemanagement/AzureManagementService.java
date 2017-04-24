package org.nephology.azure.computemanagement;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.naming.ServiceUnavailableException;

import com.microsoft.azure.management.compute.ComputeManagementClient;
import com.microsoft.azure.management.compute.models.VirtualMachine;
import org.nephology.azure.computemanagement.client.AzureClientProvider;
import org.nephology.properties.CustomPropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by PLGrubskiM on 2017-04-24.
 */
public class AzureManagementService {

    private final Logger logger = LoggerFactory.getLogger(AzureManagementService.class);

    @Autowired
    private AzureClientProvider azureClientProvider;

    @Autowired
    private CustomPropertyReader cpr;

    public List<VirtualMachine> getAllVirtualMachines() throws URISyntaxException, InterruptedException, IOException, ExecutionException, ServiceUnavailableException {
        List<VirtualMachine> virtualMachines;
        ComputeManagementClient client;
        return virtualMachines;
    }

    private void prepareAzureClient() {

        final String azureClientId = cpr.getAzureKey();
        final String azureSecret = cpr.getAzureSecret();
        final String azureTenantId = cpr.getAzureTenantId();

        if (!azureClientId.isEmpty() && !azureSecret.isEmpty() && !azureTenantId.isEmpty()) {
            azureClientProvider.setClientId(azureClientId);
            azureClientProvider.setClientSecret(azureSecret);
            azureClientProvider.setTenantId(azureTenantId);
        } else {
            logger.error("Azure credentials not provided");
        }

    }
}

package org.nephology.azure.computemanagement;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.azure.PagedList;
import com.microsoft.azure.management.compute.VirtualMachine;
import org.nephology.azure.computemanagement.client.AzureClientProvider;
import org.nephology.azure.computemanagement.domain.AzureVirtualMachineConverter;
import org.nephology.azure.computemanagement.domain.AzureVirtualMachineData;
import org.nephology.properties.CustomPropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by PLGrubskiM on 2017-04-24.
 */
@Component
public class AzureManagementService {

    private final Logger logger = LoggerFactory.getLogger(AzureManagementService.class);

    @Autowired
    private AzureClientProvider azureClientProvider;

    @Autowired
    private CustomPropertyReader cpr;

    public List<AzureVirtualMachineData> listAllVirtualMachines() {

        prepareAzureClient();

        final PagedList<VirtualMachine> list = azureClientProvider.getClient().virtualMachines().list();
        List<AzureVirtualMachineData> resultList = new ArrayList<AzureVirtualMachineData>();
        for (VirtualMachine azureVm : list) {
            resultList.add(AzureVirtualMachineConverter.convert(azureVm));
        }
        return resultList;
    }

    private void prepareAzureClient() {
        final String azureKey = cpr.getAzureKey();
        final String azureSecret = cpr.getAzureSecret();
        final String azureTenantId = cpr.getAzureTenantId();
        final String azureSubscriptionId = cpr.getAzureSubscriptionId();

        azureClientProvider.setClient(azureKey);
        azureClientProvider.setSecret(azureSecret);
        azureClientProvider.setTenant(azureTenantId);
        azureClientProvider.setSubscriptionId(azureSubscriptionId);

    }
}

package org.nephology.azure.computemanagement.domain;

import com.microsoft.azure.management.compute.VirtualMachine;

/**
 * Created by PLGrubskiM on 2017-05-22.
 */
public class AzureVirtualMachineConverter {

    public static AzureVirtualMachineData convert(VirtualMachine vm) {
        AzureVirtualMachineData newVm = new AzureVirtualMachineData();

        newVm.setVmId(vm.inner().vmId());
        newVm.setName(vm.name());
        newVm.setStatus(vm.provisioningState());
        newVm.setSize(vm.inner().hardwareProfile().vmSize().toString());
        newVm.setLocation(vm.inner().location());

        return newVm;
    }
}

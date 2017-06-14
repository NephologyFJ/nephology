package org.nephology.azure.computemanagement.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by PLGrubskiM on 2017-05-22.
 */
public class AzureVirtualMachineData {

    @Id
    private String id;
    private String vmId;
    private String name;
    private String status;
    private String size;
    private String location;


    public String getVmId() {
        return vmId;
    }

    public void setVmId(String vmId) {
        this.vmId = vmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "AzureVirtualMachineData{" +
                "vmId='" + vmId + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", size='" + size + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

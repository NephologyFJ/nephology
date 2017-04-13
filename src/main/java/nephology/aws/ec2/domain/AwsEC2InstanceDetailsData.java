package nephology.aws.ec2.domain;

import com.amazonaws.services.ec2.model.Instance;
import org.springframework.data.annotation.Id;

/**
 * Created by PLGrubskiM on 2017-04-11.
 */
public class AwsEC2InstanceDetailsData {

    public AwsEC2InstanceDetailsData() {
    }

    @Id
    private String id;
    private String instanceId;
    private String imageId;
    private String keyName;
    private String instanceType;
    private String subnetId;
    private String privateIpAddress;
    private String publicIpAddress;


    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    public void setPrivateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
    }

    public String getPublicIpAddress() {
        return publicIpAddress;
    }

    public void setPublicIpAddress(String publicIpAddress) {
        this.publicIpAddress = publicIpAddress;
    }

    @Override
    public String toString() {
        return "AwsEC2InstanceDetailsData{" +
                "id='" + id + '\'' +
                ", instanceId='" + instanceId + '\'' +
                ", imageId='" + imageId + '\'' +
                ", keyName='" + keyName + '\'' +
                ", instanceType='" + instanceType + '\'' +
                ", subnetId='" + subnetId + '\'' +
                ", privateIpAddress='" + privateIpAddress + '\'' +
                ", publicIpAddress='" + publicIpAddress + '\'' +
                '}';
    }
}

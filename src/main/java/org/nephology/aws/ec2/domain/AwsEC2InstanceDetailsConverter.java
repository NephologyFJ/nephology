package org.nephology.aws.ec2.domain;

import com.amazonaws.services.ec2.model.Instance;

/**
 * Created by PLGrubskiM on 2017-04-13.
 */
public class AwsEC2InstanceDetailsConverter {

    public static AwsEC2InstanceDetailsData convert(Instance instance) {
        AwsEC2InstanceDetailsData convertedInstance = new AwsEC2InstanceDetailsData();

        convertedInstance.setImageId(instance.getImageId());
        convertedInstance.setInstanceId(instance.getInstanceId());
        convertedInstance.setKeyName(instance.getKeyName());
        convertedInstance.setInstanceType(instance.getInstanceType());
        convertedInstance.setPrivateIpAddress(instance.getPrivateIpAddress());
        convertedInstance.setPublicIpAddress(instance.getPublicIpAddress());
        convertedInstance.setSubnetId(instance.getSubnetId());

        return convertedInstance;
    }
}

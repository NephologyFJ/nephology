package org.nephology.aws.ec2.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AwsEC2InstanceDetailsDataRepository extends MongoRepository<AwsEC2InstanceDetailsData, String> {

}
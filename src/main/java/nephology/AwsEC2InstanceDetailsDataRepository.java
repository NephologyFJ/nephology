package nephology;

import nephology.aws.ec2.domain.AwsEC2InstanceDetailsData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AwsEC2InstanceDetailsDataRepository extends MongoRepository<AwsEC2InstanceDetailsData, String> {

}
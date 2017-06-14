package org.nephology.azure.computemanagement.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by PLGrubskiM on 2017-05-22.
 */
public interface AzureVirtualMachineDataRepository extends MongoRepository<AzureVirtualMachineData, String> {
}

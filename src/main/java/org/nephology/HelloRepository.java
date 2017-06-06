package org.nephology;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface HelloRepository extends MongoRepository<Hello, String> {

    @Override
    void delete(Hello hello);

    Hello findByMessage(@Param("message") String message);

}
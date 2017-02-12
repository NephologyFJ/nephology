package hello;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HelloRepository extends MongoRepository<Hello, String> {

    Hello findByMessage(String message);

}
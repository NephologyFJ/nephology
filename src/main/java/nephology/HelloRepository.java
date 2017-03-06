package nephology;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.access.prepost.PreAuthorize;

public interface HelloRepository extends MongoRepository<Hello, String> {

    String ADMIN_GROUP_HREF = "https://api.stormpath.com/v1/groups/5N3YcZ9V496W4NrzMxWkwj";

    @PreAuthorize("hasAuthority('" + ADMIN_GROUP_HREF + "')")
    @Override
    void delete(Hello hello);

    Hello findByMessage(String message);

}
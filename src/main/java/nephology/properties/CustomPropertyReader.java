package nephology.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by PLGrubskiM on 2017-03-26.
 */
@Component
public class CustomPropertyReader {

    @Value("${custom.sample}")
    private String sample;

    @Value("${mongo.admin.href}")
    private String mongoAdminHref;

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getMongoAdminHref() {
        return mongoAdminHref;
    }

    public void setMongoAdminHref(String mongoAdminHref) {
        this.mongoAdminHref = mongoAdminHref;
    }
}

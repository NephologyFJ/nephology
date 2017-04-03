package nephology.aws.ec2;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by PLGrubskiM on 2017-04-03.
 */
public class EC2InstanceDetailsTest {

    EC2InstanceDetails details;

    @Before
    public void setUp() {
        details = new EC2InstanceDetails();
    }

    @Test
    public void getInstancesTest() {
        // given
        // when
        details.getInstances();
        // then
    }
}

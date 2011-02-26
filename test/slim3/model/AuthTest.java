package slim3.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AuthTest extends AppEngineTestCase {

    private Auth model = new Auth();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}

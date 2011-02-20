package slim3.controller.attend;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/attend/test");
        TestController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/attend/test.jsp"));
    }
}

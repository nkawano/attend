package slim3.controller.attend.login;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class LogoutController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        removeSessionScope("loginUser");
        
        return forward("/attend/");
    }
}

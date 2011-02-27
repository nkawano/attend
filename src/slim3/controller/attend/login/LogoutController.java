package slim3.controller.attend.login;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import slim3.constants.Constants;

public class LogoutController extends Controller {

    @Override
    public Navigation run() throws Exception {

        removeSessionScope(Constants.SESSION_KEY_LOGIN_USER);
        removeSessionScope(Constants.SESSION_KEY_LOGIN_USER_AUTH);

        return forward("/attend/");
    }
}

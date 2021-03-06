package slim3.controller.attend.manage.practice;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import slim3.constants.Constants;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        if(sessionScope(Constants.SESSION_KEY_LOGIN_USER) == null) {
            // TODO ログインしていないページに飛ばす必要がある
            return forward("/attend/error/");
        }
        return forward("index.jsp");
    }
}

package slim3.controller.attend.changePassword;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import slim3.model.Member;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Member loginUser = sessionScope("loginUser");

        if (loginUser == null) {
            // TODO ログインしていないページに飛ばす必要がある
            return forward("/attend/error/");
        } else {
            requestScope("key", loginUser.getKey());
            return forward("index.jsp");
        }
    }
}

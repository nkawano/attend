package slim3.controller.attend.changePassword;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import slim3.model.Member;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Member loginUser = sessionScope("loginUser");

        if (loginUser == null) {
            // TODO ���O�C�����Ă��Ȃ��y�[�W�ɔ�΂��K�v������
            return forward("/attend/error/");
        } else {
            requestScope("key", loginUser.getKey());
            return forward("index.jsp");
        }
    }
}

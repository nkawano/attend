package slim3.controller.attend.manage.member;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
//        if(sessionScope(Constants.SESSION_KEY_LOGIN_USER) == null) {
//             TODO ���O�C�����Ă��Ȃ��y�[�W�ɔ�΂��K�v������
//            return forward("/attend/error/");
//        }
        return forward("index.jsp");
    }
}

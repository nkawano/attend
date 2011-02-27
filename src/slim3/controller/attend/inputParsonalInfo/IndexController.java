package slim3.controller.attend.inputParsonalInfo;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import slim3.constants.Constants;
import slim3.model.Member;
import slim3.service.MemberService;

public class IndexController extends Controller {

    private MemberService memberSvc = new MemberService();

    @Override
    public Navigation run() throws Exception {

        Member loginUser = sessionScope(Constants.SESSION_KEY_LOGIN_USER);

        if(loginUser == null) {
            // TODO ���O�C�����Ă��Ȃ��y�[�W�ɔ�΂��K�v������
            return forward("/attend/error/");
        } else {
            request.setAttribute("member", memberSvc.searchFromId(loginUser.getId()));
            return forward("index.jsp");
        }
    }
}

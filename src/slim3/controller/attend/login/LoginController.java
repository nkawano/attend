package slim3.controller.attend.login;

import java.util.HashMap;
import java.util.Map;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.ApplicationMessage;

import slim3.constants.Constants;
import slim3.model.Member;
import slim3.model.MemberAuth;
import slim3.service.MemberAuthService;
import slim3.service.MemberService;

public class LoginController extends Controller {

    private MemberService svc = new MemberService();
    private MemberAuthService authSvc = new MemberAuthService();

    @Override
    public Navigation run() throws Exception {

        Member member = svc.login(asString("id"), asString("password"));

        if (member != null) {

            MemberAuth memberAuth = authSvc.searchFromMemberKey(member);

            if (memberAuth == null) {
                // �c���̓o�^���ɒc���������͍쐬����邽�߁A��{�I�ɂ����͒ʂ�Ȃ����ǁA
                // �����쐬����ĂȂ������ꍇ�́A�����l�œo�^���Ă��܂��B(���O�C���ł��Ă邩��ǂ���ˁH)
                memberAuth = authSvc.registAsInitial(member);
            }

            // �t�B���^�[�Ō��������擾���邽�߁Ahot-reloading�ȃN���X��cool�ȃt�B���^�ňˑ����Ă��܂�Ȃ��悤�ɁA
            // hashMap�ɋl�ߑւ�
            Map<String, Integer> authMap = new HashMap<String, Integer>();
            authMap.put(Constants.MAP_KEY_ATTENDANCE, memberAuth.getAttendance());
            authMap.put(Constants.MAP_KEY_MEMBER, memberAuth.getMember());
            authMap.put(Constants.MAP_KEY_PRACTICE, memberAuth.getPractice());
            authMap.put(Constants.MAP_KEY_MEMBERAUTH, memberAuth.getMemberAuth());

            sessionScope(Constants.SESSION_KEY_LOGIN_USER, member);
            sessionScope(Constants.SESSION_KEY_LOGIN_USER_AUTH, authMap);

        } else {
            errors.put("cannotLogin", ApplicationMessage.get("login.error"));
        }

        return forward("/attend/");
    }
}

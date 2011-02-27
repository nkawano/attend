package slim3.controller.attend.manage.member.updateAuth;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import slim3.model.Member;
import slim3.model.MemberAuth;
import slim3.service.MemberAuthService;
import slim3.service.MemberService;

public class IndexController extends Controller {

    private MemberService memberService = new MemberService();
    private MemberAuthService memberAuthService = new MemberAuthService();

    @Override
    public Navigation run() throws Exception {

        String id = asString("id");

        if(id == null) {
            throw new IllegalArgumentException("ID��null�ł��B");
        }

        Member member = memberService.searchFromId(id);

        if(member == null) {
            throw new IllegalArgumentException("�����ύX�Ώۂ�ID�����݂��܂���. ID:" + id);
        }

        MemberAuth memberAuth =memberAuthService.searchFromMemberKey(member);

        if(memberAuth == null){
            //�����G���e�B�e�B���܂����݂��Ȃ��ꍇ�A�����ŉi�������Ă��܂��B(�c���o�^���ɓo�^�����͂��Ȃ̂ŁA��{�I�ɂȂ��͂��B)
            memberAuth = memberAuthService.registAsInitial(member);
        }

        requestScope("memberInfo", member);
        requestScope("attendance", memberAuth.getAttendance());
        requestScope("member", memberAuth.getMember());
        requestScope("practice", memberAuth.getPractice());
        requestScope("memberAuth", memberAuth.getMemberAuth());
        requestScope("memberAuthKey", memberAuth.getKey());

        return forward("index.jsp");
    }

}

package slim3.controller.attend.manage.member.updateAuth;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import slim3.constants.Constants;
import slim3.model.Member;
import slim3.model.MemberAuth;
import slim3.service.MemberAuthService;
import slim3.service.MemberService;

public class IndexController extends Controller {

    private MemberService memberService = new MemberService();
    private MemberAuthService memberAuthService = new MemberAuthService();

    @Override
    public Navigation run() throws Exception {

        if(asString("id") == null) {
            throw new IllegalArgumentException("IDがnullです。");
        }

        Member member = memberService.searchFromId(asString("id"));

        if(member == null) {
            throw new IllegalArgumentException("権限変更対象のIDが存在しません. ID:" + asString("id"));
        }

        MemberAuth memberAuth =memberAuthService.searchFromMemberKey(member);

        if(memberAuth == null){
            memberAuth = new MemberAuth();
            memberAuth.setAttendance(Constants.AUTH_REFER);
            memberAuth.setMember(Constants.AUTH_NOTHING);
            memberAuth.setPractice(Constants.AUTH_NOTHING);
            memberAuth.setMemberAuth(Constants.AUTH_NOTHING);
            memberAuth.getMemberRef().setModel(member);

            //ここで永続化してしまう。(キーが生成されてくるため、参照を更新)
            memberAuth = memberAuthService.regist(memberAuth);
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

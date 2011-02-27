package slim3.controller.attend.manage.member.updateAuth;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import slim3.model.Member;
import slim3.model.MemberAuth;
import slim3.service.MemberAuthService;
import slim3.service.MemberService;

import com.google.appengine.api.datastore.Key;

public class SubmitController extends Controller {

    MemberAuthService memberAuthService = new MemberAuthService();
    MemberService memberService = new MemberService();

    @Override
    public Navigation run() throws Exception {

        Key memberKey = asKey("memberKey");
        Key memberAuthKey = asKey("memberAuthKey");
        // 以下設定された権限情報
        Integer attendance = asInteger("attendance");
        Integer member = asInteger("member");
        Integer practice = asInteger("practice");
        Integer memberAuth = asInteger("memberAuth");

        MemberAuth memberAuthModel = memberAuthService.searchFromKey(memberAuthKey);

        memberAuthModel.setAttendance(attendance);
        memberAuthModel.setMember(member);
        memberAuthModel.setPractice(practice);
        memberAuthModel.setMemberAuth(memberAuth);
        memberAuthModel.getMemberRef().setKey(memberKey);

        //参照を更新結果インスタンスに更新
        memberAuthModel = memberAuthService.regist(memberAuthModel);

        //画面表示用に取得
        Member memberInfo = memberService.searchFromKey(memberKey);


        requestScope("auth", memberAuthModel);
        requestScope("memberInfo", memberInfo);

        return forward("submit.jsp");
    }
}

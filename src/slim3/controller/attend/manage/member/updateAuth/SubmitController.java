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
        // �ȉ��ݒ肳�ꂽ�������
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

        //�Q�Ƃ��X�V���ʃC���X�^���X�ɍX�V
        memberAuthModel = memberAuthService.regist(memberAuthModel);

        //��ʕ\���p�Ɏ擾
        Member memberInfo = memberService.searchFromKey(memberKey);


        requestScope("auth", memberAuthModel);
        requestScope("memberInfo", memberInfo);

        return forward("submit.jsp");
    }
}

package slim3.controller.attend.manage.member.update;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.DateUtil;

import slim3.constants.Constants;
import slim3.meta.MemberMeta;
import slim3.model.Member;
import slim3.service.MemberService;

import com.google.appengine.api.datastore.Key;

public class SubmitController extends Controller {

    private MemberService svc = new MemberService();
    private MemberMeta meta = new MemberMeta();

    @Override
    public Navigation run() throws Exception {

        // ���̓`�F�b�N
        if(!validate()){

            // ��ʂ��ĕ\�����邽�߂�Member�����\�z
            Member member = new Member();

            member.setKey(asKey("key"));
            member.setFirstName(asString("firstName"));
            member.setLastName(asString("lastName"));
            try {
                member.setBirthDay(asDate("birthDay", DateUtil.ISO_DATE_PATTERN));
            } catch (Exception e) {

            }
            member.setMailAddress(asString("mailAddress"));
            member.setPart(asString("part"));

            requestScope("member",member);
            return forward("index.jsp");
        }

        // �X�V�Ј������\�z����
        Member updatedMember = getUpdatedMemberFromRequest();

        // �Ј������X�V����
        svc.update(updatedMember);

        // TODO
        // �X�V���ɃG���[�����������Ƃ��̍l��

        // ��ʕ\���p�f�[�^���Z�b�g����
        requestScope("member", updatedMember);
        updateLoginInfo();

        return forward("submit.jsp");
    }

    /**
     * �Z�b�V�����ɂ��郍�O�C����Ԃ��ŐV���܂��B
     */
    protected void updateLoginInfo() {
        Member loginUser = sessionScope(Constants.SESSION_KEY_LOGIN_USER);
        removeSessionScope(Constants.SESSION_KEY_LOGIN_USER);
        sessionScope(Constants.SESSION_KEY_LOGIN_USER, svc.searchFromKey(loginUser.getKey()));
    }

    /**
     * �X�V�ΏێЈ������\�z���܂��B
     *
     * @return �X�V�ΏێЈ����
     * @throws IllegalArgumentException ��ʓ��͎��̃G���[
     */
    private Member getUpdatedMemberFromRequest() throws IllegalArgumentException {

        Key key = asKey("key");

        if(key == null){
            throw new IllegalArgumentException("�L�[���擾�ł��܂���B");
        }

        Member member = svc.searchFromKey(asKey("key"));

        if(member == null) {
            throw new IllegalArgumentException("key�ɑΉ�����c�������݂��܂���Bkey:" +  key);
        } else {
            member.setFirstName(asString("firstName"));
            member.setLastName(asString("lastName"));
            try {
                member.setBirthDay(asDate("birthDay", DateUtil.ISO_DATE_PATTERN));
            } catch (Exception e) {
                throw new IllegalArgumentException("�a�����̓��t�ϊ��G���[�B" + e.getMessage());
            }
            member.setMailAddress(asString("mailAddress"));
            member.setPart(asString("part"));
        }

        return member;
    }

    /**
     * ��ʓ��̓`�F�b�N���s�Ȃ��܂��B
     *
     * @return �`�F�b�N����
     */
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add(meta.firstName, v.required());
        v.add(meta.lastName, v.required());
        v.add(meta.birthDay, v.required(), v.dateType(DateUtil.ISO_DATE_PATTERN));
        v.add(meta.mailAddress, v.required());
        return v.validate();
    }
}
package slim3.controller.attend.manage.member.update;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import slim3.meta.MemberMeta;
import slim3.model.Member;
import slim3.service.MemberService;

public class IndexController extends Controller {
    
    private MemberService svc = new MemberService();
    private MemberMeta meta = new MemberMeta();

    @Override
    public Navigation run() throws Exception {

        // ���̓`�F�b�N
        if(!validate()){
            errors.put("selectError", "�N���I��ł��������B");
            return forward("/attend/managementMember/searchMember/");   // index.jsp����/updateMember/�ɔ��ł��܂�
        }
        
        // �{�^���ɂ���đJ�ڐ��U�蕪����iupdate�̏ꍇ�͓��R���g���[���ɂď����B�j
        if(asString("update") != null) {
            // �X�V�{�^���������ꂽ�ꍇ
        } else if(asString("delete") != null) {
            // �폜�{�^���������ꂽ�ꍇ
            return forward("/attend/manage/member/delete/");
        }
        
        String inputId = asString("id");
        
        if (inputId == null) {
            throw new IllegalArgumentException("�L�[�ƂȂ�id���擾�ł��܂���B");
        }
        
        Member member = svc.searchFromId(asString("id"));
        
        if(member == null) {
            throw new IllegalArgumentException("id�ɑΉ�����c�������݂��܂���Bid:" +  inputId);
        } else {
            requestScope("member", member);
        }
        
        return forward("index.jsp");
    }

    /**
     * ��ʓ��̓`�F�b�N���s�Ȃ��܂��B
     * 
     * @return �`�F�b�N����
     */
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add(meta.id, v.required());
        return v.validate();
    }
}

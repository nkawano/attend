package slim3.controller.attend.changePassword;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;

import slim3.model.Member;
import slim3.service.MemberService;

public class SubmitController extends Controller {
    
    MemberService memberSvc = new MemberService();

    @Override
    public Navigation run() throws Exception {
        
        if(!validate() || !customValidate()){
            return forward(basePath);
        }
        
        Member member = memberSvc.searchFromKey(asKey("key"));

        if(member == null){
            throw new IllegalArgumentException("���݂��Ȃ��L�[���w�肳��Ă��܂��B");
        }
        
        if(member.getPassword().equals(asString("oldPassword"))){
            member.setPassword(asString("newPassword"));
            memberSvc.update(member);
            return forward("submit.jsp");
        } else {
            errors.put("inputError", "�p�X���[�h���Ԉ���Ă��܂��B");
            return forward(basePath);
        }
        
    }
    
    
    /**
     * ��ʓ��̓`�F�b�N���s�Ȃ��܂��B
     * 
     * @return �`�F�b�N����
     */
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("oldPassword", v.required());
        v.add("newPassword", v.required());
        v.add("newPassword2", v.required());
        return v.validate();
    }
    
    /**
     * ��ʓ��̓`�F�b�N���s�Ȃ��܂��B
     * 
     * @return �`�F�b�N����
     */
    protected boolean customValidate() {
        
        if(asString("newPassword").equals(asString("newPassword2"))){
            return true;
        } else {
            errors.put("inputError", "�V�����p�X���[�h����v���Ă��܂���B");
            return false;
        }
    }
}

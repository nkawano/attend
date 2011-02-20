package slim3.controller.attend.login;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.ApplicationMessage;

import slim3.model.Member;
import slim3.service.MemberService;

public class LoginController extends Controller {

    private MemberService svc = new MemberService();
    
    @Override
    public Navigation run() throws Exception {
        
        Member member = svc.login(asString("id"), asString("password"));
        
        if(member != null) {
            sessionScope("loginUser", member);
        } else {
            errors.put("cannotLogin", ApplicationMessage.get("login.error"));
        }
        
        return forward("/attend/");
    }
}

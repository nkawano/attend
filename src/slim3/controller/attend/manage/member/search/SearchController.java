package slim3.controller.attend.manage.member.search;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import slim3.model.Member;
import slim3.service.MemberService;

public class SearchController extends Controller {

    @Override
    public Navigation run() throws Exception {
        
        MemberService svc = new MemberService();
        
        // 入力されたidで始まるMemberを取得する
        List<Member> list = svc.searchStartWithId(asString("id"));
        
        // 取得されていたらrequestScopeに格納する
        if(list!= null && list.size() > 0) {
            requestScope("memberList", list);
        }
        
        return forward("index.jsp");
    }
}
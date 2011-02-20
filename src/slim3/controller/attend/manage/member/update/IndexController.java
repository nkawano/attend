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

        // 入力チェック
        if(!validate()){
            errors.put("selectError", "誰か選んでください。");
            return forward("/attend/managementMember/searchMember/");   // index.jspだと/updateMember/に飛んでしまう
        }
        
        // ボタンによって遷移先を振り分ける（updateの場合は等コントローラにて処理。）
        if(asString("update") != null) {
            // 更新ボタンが押された場合
        } else if(asString("delete") != null) {
            // 削除ボタンが押された場合
            return forward("/attend/manage/member/delete/");
        }
        
        String inputId = asString("id");
        
        if (inputId == null) {
            throw new IllegalArgumentException("キーとなるidが取得できません。");
        }
        
        Member member = svc.searchFromId(asString("id"));
        
        if(member == null) {
            throw new IllegalArgumentException("idに対応する団員が存在しません。id:" +  inputId);
        } else {
            requestScope("member", member);
        }
        
        return forward("index.jsp");
    }

    /**
     * 画面入力チェックを行ないます。
     * 
     * @return チェック結果
     */
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add(meta.id, v.required());
        return v.validate();
    }
}

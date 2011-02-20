package slim3.controller.attend.inputParsonalInfo;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.DateUtil;

import slim3.meta.MemberMeta;
import slim3.model.Member;
import slim3.service.MemberService;

import com.google.appengine.api.datastore.Key;

public class SubmitController extends Controller {
    
    private MemberService svc = new MemberService();
    private MemberMeta meta = new MemberMeta();

    @Override
    public Navigation run() throws Exception {
        
        // 入力チェック
        if(!validate() || !checkID(asString("id"))){

            // 画面を再表示するためにMember情報を構築
            Member member = new Member();

            member.setKey(asKey("key"));
            member.setId(asString("id"));
            member.setFirstName(asString("firstName"));
            member.setLastName(asString("lastName"));
            try {
                member.setBirthDay(asDate("birthDay", DateUtil.ISO_DATE_PATTERN));
            } catch (Exception e) {
                
            }
            member.setMailAddress(asString("mailAddress"));
            member.setPart(asString("part"));
            
            requestScope("member", member);
            return forward("index.jsp");
        }
        
        // 更新社員情報を構築する
        Member updatedMember = getUpdatedMemberFromRequest();

        // 社員情報を更新する
        svc.update(updatedMember);

        // TODO
        // 更新時にエラーが発生したときの考慮
        
        // 画面表示用データをセットする
        requestScope("member", updatedMember);
        updateLoginInfo();
        
        return forward("submit.jsp");
    }

    /**
     * 更新対象社員情報を構築します。
     * 
     * @return 更新対象社員情報
     * @throws IllegalArgumentException 画面入力時のエラー
     */
    private Member getUpdatedMemberFromRequest() throws IllegalArgumentException {
        
        Key key = asKey("key");
        
        if(key == null){
            throw new IllegalArgumentException("キーが取得できません。");            
        }
                
        Member member = svc.searchFromKey(asKey("key"));
        
        if(member == null) {
            throw new IllegalArgumentException("keyに対応する団員が存在しません。key:" +  key);
        } else {
            member.setId(asString("id"));
            member.setFirstName(asString("firstName"));
            member.setLastName(asString("lastName"));
            try {
                member.setBirthDay(asDate("birthDay", DateUtil.ISO_DATE_PATTERN));
            } catch (Exception e) {
                throw new IllegalArgumentException("誕生日の日付変換エラー。" + e.getMessage());
            }
            member.setMailAddress(asString("mailAddress"));
            member.setPart(asString("part"));
        }
        
        return member;
    }
    

    /**
     * セッションにあるログイン状態を最新可します。
     */
    protected void updateLoginInfo() {
        Member loginUser = sessionScope("loginUser");
        removeSessionScope("loginUser");
        sessionScope("loginUser", svc.searchFromKey(loginUser.getKey()));
    }
    
    /**
     * 画面入力チェックを行ないます。
     * 
     * @return チェック結果
     */
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add(meta.id, v.required(), v.regexp("[A-Za-z0-9]+", "英字だけでお願い！"));
        v.add(meta.firstName, v.required());
        v.add(meta.lastName, v.required());
        v.add(meta.birthDay, v.required(), v.dateType(DateUtil.ISO_DATE_PATTERN));
        v.add(meta.mailAddress, v.required());
        return v.validate();
    }

    /**
     * 入力されたIDを検証する
     * 
     * @param inputId
     * @return 検証結果
     */
    private boolean checkID(String inputId) {
        
        // 入力されたIDが空だった場合エラー発生
        if(inputId == null || inputId.isEmpty()) {
            errors.put("inputError", "IDを入力してください。");
            return false;
        }
        
        // IDでEntityが取得できる場合、
        if(svc.searchFromId(inputId) != null) {
            
            Member member = svc.searchFromKey(asKey("key"));

            // IDが更新されていればエラー発生
            if(member.getId().equals(inputId)){
                return true;
            } else {
                errors.put("inputError", "既に存在するIDです。");
                return false;
            }
        }
        
        return true;
    }
}

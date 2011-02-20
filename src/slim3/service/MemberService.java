package slim3.service;

import java.util.List;
import java.util.Map;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.util.BeanUtil;

import slim3.meta.MemberMeta;
import slim3.model.Member;

import com.google.appengine.api.datastore.Key;


public class MemberService {
    
    private MemberMeta t = new MemberMeta();
    
    /**
     * IDとPasswordでユーザー情報を取得します
     * 
     * @param id
     * @param password
     * @return Member
     */
    public Member login(String id, String password) {
        return searchFromIdAndPassword(id, password);
    }
    
    /**
     * Memberを一人登録する。
     * ＩＤがない場合、また同じＩＤが存在する場合はエラー
     * 
     * @param input
     * @return
     * @throws IllegalArgumentException
     */
    public Member regist(Map<String, Object> input) throws IllegalArgumentException {
        
        if(input == null) {
            throw new IllegalArgumentException("Error! Input input is null.");
        }

        Member member = new Member();
        BeanUtil.copy(input, member);

        // 社員番号がなければエラー
        if (member.getId() == null) {
            throw new IllegalArgumentException("Error! Input id is null.");
        }
        // 社員が存在すれば登録失敗
        if (searchFromId(member.getId()) != null) {
            throw new IllegalArgumentException("Error! Input id already exists.");
        }

        // データ登録
        GlobalTransaction gtx = Datastore.beginGlobalTransaction();
        gtx.put(member);
        gtx.commit();
        return member;
    }
    
    /**
     * Key情報でMemberを検索します。
     * 
     * @param key
     * @return Member
     */
    public Member searchFromKey(Key key) {
        return Datastore.query(t).filter(t.key.equal(key)).asSingle();
    }
    
    /**
     * IDでMemberを検索します
     * 
     * @param id
     * @return Member
     */
    public Member searchFromId(String id) {
        return Datastore.query(t).filter(t.id.equal(id)).asSingle();
    }

    /**
     * MemberをIDとPasswordで検索します
     * 
     * @param id
     * @return Member
     */
    public Member searchFromIdAndPassword(String id, String password) throws IllegalArgumentException {
        if(id == null) {
            throw new IllegalArgumentException("Error! Input id is null.");
        } else if(password == null) {
            throw new IllegalArgumentException("Error! Input password is null.");
        } else {
            return Datastore.query(t).filter(
                t.id.equal(id),
                t.password.equal(password)).asSingle();
        }
    }
    
    /**
     * MemberをIDで先方一致検索します
     * 
     * @param id
     * @return Member
     */
    public List<Member> searchStartWithId(String id) throws IllegalArgumentException {
        if(id == null) {
            throw new IllegalArgumentException("Error! Input id is null.");
        } else {
            return Datastore.query(t).filter(t.id.startsWith(id)).asList();
        }
    }
    
    /**
     * 全Memberを取得取得します（IDでソート）
     * 
     * @return 全Member
     */
    public List<Member> getAll() {
        return Datastore.query(t).sort(t.id.asc).asList();
    }
    
    /**
     * Member情報を更新します
     * 
     * @param member 更新対象社員情報
     * @return 更新後社員情報
     * @throws IllegalArgumentException 更新対象社員が存在しない場合に発生
     */
    public Member update(Member member) throws IllegalArgumentException {

        if(member == null) {
            throw new IllegalArgumentException("Error! Input member is null.");
        } else if(searchFromKey(member.getKey()) == null){
            throw new IllegalArgumentException("Error! Target member does not exist. : Input key is " + member.getKey());
        } else {
            GlobalTransaction gtx = Datastore.beginGlobalTransaction();
            gtx.put(member);
            gtx.commit();
        }
        return member;
    }
    
    /**
     * Memberを一人更新します。
     * 
     * @param input
     * @return
     * @throws IllegalArgumentException
     */
    public Member update(Map<String, Object> input) throws IllegalArgumentException {
        
        if(input == null) {
            throw new IllegalArgumentException("Error! Input input is null.");
        }

        Member member = new Member();
        BeanUtil.copy(input, member);

        // 社員番号がなければエラー
        if (member.getId() == null) {
            throw new IllegalArgumentException("Error! Input id is null.");
        }
        // 社員が存在しなければ更新失敗
        if (searchFromKey(member.getKey()) == null) {
            throw new IllegalArgumentException("Error! Input id already exists.");
        }

        // データ登録
        GlobalTransaction gtx = Datastore.beginGlobalTransaction();
        gtx.put(member);
        gtx.commit();
        return member;
    }    
    
    /**
     * Memberを削除します
     * 
     * @param id 削除対象ID
     * @return 成功時trueを返す
     */
    public boolean delete(String id) throws IllegalArgumentException {
        
        if(id == null) {
            return false;
        }
        Member member = searchFromId(id);
        if(member == null){
            return false;
        } else {
            GlobalTransaction gtx = Datastore.beginGlobalTransaction();
            gtx.delete(member.getKey());
            gtx.commit();
        }
        return true;
    }
}

package slim3.service;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import com.google.appengine.api.datastore.Key;

import slim3.meta.MemberAuthMeta;
import slim3.model.Member;
import slim3.model.MemberAuth;


public class MemberAuthService {

    MemberAuthMeta t = new MemberAuthMeta();

    public MemberAuth searchFromMemberKey(Member member){
        return Datastore.query(t).filter(t.memberRef.equal(member.getKey())).asSingle();
    }

    public MemberAuth searchFromKey(Key key){
        return Datastore.query(t).filter(t.key.equal(key)).asSingle();
    }

    public MemberAuth regist(MemberAuth memberAuth){

        // ïKê{èÓïÒÇÃämîF
        if(memberAuth == null) {
            throw new IllegalArgumentException("Error! Input attendance is null.");
        }

        GlobalTransaction gtx = Datastore.beginGlobalTransaction();
        gtx.put(memberAuth);
        gtx.commit();

        return memberAuth;
    }


}

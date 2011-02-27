package slim3.service;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;

import com.google.appengine.api.datastore.Key;

import slim3.constants.Constants;
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

        // �K�{���̊m�F
        if(memberAuth == null) {
            throw new IllegalArgumentException("Error! Input memberAuth is null.");
        }

        if(memberAuth.getMemberRef().getModel() == null){
            //�����o�[�ւ̃����[�V�����������Ă��Ȃ���΃G���[
            throw new IllegalArgumentException("Error! Input memberAuth's relation to member is null.");
        }

        GlobalTransaction gtx = Datastore.beginGlobalTransaction();
        gtx.put(memberAuth);
        gtx.commit();

        return memberAuth;
    }


    /**
     * �c�����������o�^�p���\�b�h<br/>
     * �����œn���ꂽMember�ɕR�Â��c�����������쐬���o�^�B
     *  <br/>
     * �����l�͉��L�Q��
     *
     * �o�ȏ��F�Ȃ�
     * �����o���F�Ȃ�
     * ���K�����F�Ȃ�
     * �c���������F�Ȃ�
     *
     * @param member
     * @return
     */
    public MemberAuth registAsInitial(Member member){

        // �K�{���̊m�F
        if(member == null) {
            throw new IllegalArgumentException("Error! Input member is null.");
        }

        MemberAuth memberAuth = new MemberAuth();
        memberAuth.setAttendance(Constants.AUTH_NOTHING);
        memberAuth.setMember(Constants.AUTH_NOTHING);
        memberAuth.setPractice(Constants.AUTH_NOTHING);
        memberAuth.setMemberAuth(Constants.AUTH_NOTHING);
        memberAuth.getMemberRef().setModel(member);


        GlobalTransaction gtx = Datastore.beginGlobalTransaction();
        gtx.put(memberAuth);
        gtx.commit();

        return memberAuth;
    }

}

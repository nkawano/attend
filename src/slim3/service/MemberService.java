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
     * ID��Password�Ń��[�U�[�����擾���܂�
     * 
     * @param id
     * @param password
     * @return Member
     */
    public Member login(String id, String password) {
        return searchFromIdAndPassword(id, password);
    }
    
    /**
     * Member����l�o�^����B
     * �h�c���Ȃ��ꍇ�A�܂������h�c�����݂���ꍇ�̓G���[
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

        // �Ј��ԍ����Ȃ���΃G���[
        if (member.getId() == null) {
            throw new IllegalArgumentException("Error! Input id is null.");
        }
        // �Ј������݂���Γo�^���s
        if (searchFromId(member.getId()) != null) {
            throw new IllegalArgumentException("Error! Input id already exists.");
        }

        // �f�[�^�o�^
        GlobalTransaction gtx = Datastore.beginGlobalTransaction();
        gtx.put(member);
        gtx.commit();
        return member;
    }
    
    /**
     * Key����Member���������܂��B
     * 
     * @param key
     * @return Member
     */
    public Member searchFromKey(Key key) {
        return Datastore.query(t).filter(t.key.equal(key)).asSingle();
    }
    
    /**
     * ID��Member���������܂�
     * 
     * @param id
     * @return Member
     */
    public Member searchFromId(String id) {
        return Datastore.query(t).filter(t.id.equal(id)).asSingle();
    }

    /**
     * Member��ID��Password�Ō������܂�
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
     * Member��ID�Ő����v�������܂�
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
     * �SMember���擾�擾���܂��iID�Ń\�[�g�j
     * 
     * @return �SMember
     */
    public List<Member> getAll() {
        return Datastore.query(t).sort(t.id.asc).asList();
    }
    
    /**
     * Member�����X�V���܂�
     * 
     * @param member �X�V�ΏێЈ����
     * @return �X�V��Ј����
     * @throws IllegalArgumentException �X�V�ΏێЈ������݂��Ȃ��ꍇ�ɔ���
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
     * Member����l�X�V���܂��B
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

        // �Ј��ԍ����Ȃ���΃G���[
        if (member.getId() == null) {
            throw new IllegalArgumentException("Error! Input id is null.");
        }
        // �Ј������݂��Ȃ���΍X�V���s
        if (searchFromKey(member.getKey()) == null) {
            throw new IllegalArgumentException("Error! Input id already exists.");
        }

        // �f�[�^�o�^
        GlobalTransaction gtx = Datastore.beginGlobalTransaction();
        gtx.put(member);
        gtx.commit();
        return member;
    }    
    
    /**
     * Member���폜���܂�
     * 
     * @param id �폜�Ώ�ID
     * @return ������true��Ԃ�
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

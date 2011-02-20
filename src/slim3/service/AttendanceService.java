package slim3.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.util.BeanUtil;
import org.slim3.util.DateUtil;

import slim3.meta.AttendanceMeta;
import slim3.model.Attendance;
import slim3.model.Member;
import slim3.model.Practice;

import com.google.appengine.api.datastore.Key;


public class AttendanceService {
    
    private AttendanceMeta t = new AttendanceMeta();
    private MemberService memberSvc = new MemberService();
    private PracticeService practiceSvc = new PracticeService();

    /**
     * �o������o�^���鎞�̑O����
     * �o�����̓��̓`�F�b�N����сArefModel�̒ǉ��B
     * 
     * @param attendance �o�^�Ώۂ̏o�����
     * @return �\�z��̏o�����
     * @throws IllegalArgumentException ���̓G���[������
     */
    private Attendance preRegist(Attendance attendance) throws IllegalArgumentException {
        // �K�{���̊m�F
        if(attendance == null) {
            throw new IllegalArgumentException("Error! Input attendance is null.");
        }
        
        Member member = null;
        Practice practice = null;

        if(attendance.getMemberKey() == null) {
            throw new IllegalArgumentException("Error! Input Attendance.memberKey is null.");
        } else if ((member = memberSvc.searchFromKey(attendance.getMemberKey())) == null){
            throw new IllegalArgumentException("Error! Member related to attendance is null.");
        }
        
        if(attendance.getPracticeKey() == null) {
            throw new IllegalArgumentException("Error! Input Attendance.practicerKey is null.");
        } else if ((practice = practiceSvc.searchFromKey(attendance.getPracticeKey())) == null) {
            throw new IllegalArgumentException("Error! Practice related to attendance is null.");
        }

        // Key, MemberRef, PracticeRef �̐ݒ�
        attendance.setKey(generateKey(attendance));
        if(attendance.getMemberRef().getModel() == null) {
            attendance.getMemberRef().setModel(member);
        }
        if(attendance.getPracticeRef().getModel() == null) {
            attendance.getPracticeRef().setModel(practice);
        }
        
        // Input�̐ݒ�
        attendance.setInputFlg(true);
        
        return attendance;        
    }
    
    /**
     * �o�������P���o�^���܂�
     * 
     * @param attendance �o�^�Ώۂ̏o�����
     * @return �o�^���ꂽ�o�����
     */
    public Attendance regist(Attendance attendance) throws IllegalArgumentException{
        attendance = preRegist(attendance);
        GlobalTransaction gtx = Datastore.beginGlobalTransaction();
        gtx.put(attendance);
        gtx.commit();
        return attendance;
    }

    /**
     * RequestMap����o������o�^���܂�
     * 
     * @param input RequestMap
     * @return �o�^���ꂽ�o�����
     * @throws IllegalArgumentException
     */
    public Attendance regist(Map<String, Object> input) throws IllegalArgumentException {
        if(input == null) {
            throw new IllegalArgumentException("Error! Input input is null.");
        } else {
            Attendance attendance = new Attendance();
            BeanUtil.copy(input, attendance);       
            return regist(attendance);
        }
    }

    /**
     * �o�����𕡐����o�^���܂�
     * �P���ł��o�^�Ɏ��s�����ꍇ�́A�r���ɓo�^�������̂��S�č폜���܂��B�iAtomic�ێ��̂��߁j
     * 
     * @param attendanceList �o�^�Ώۂ̏o�����̃��X�g
     * @return �o�^���ꂽ�o�����̃��X�g
     */
    public List<Attendance> registList(List<Attendance> attendanceList) throws IllegalArgumentException {
        if(attendanceList == null || attendanceList.size() == 0) {
            throw new IllegalArgumentException("Error! Input attendanceList is null.");
        } else {
            List<Attendance> constructedList = new ArrayList<Attendance>();
            for (Attendance attendance : attendanceList) {
                constructedList.add(preRegist(attendance));
            }
            GlobalTransaction gtx = Datastore.beginGlobalTransaction();
            gtx.put(constructedList);
            gtx.commit();            
            return constructedList;
        }
    }
    
    /**
     * �o�������P���X�V���܂�
     * �X�V�Ώۂ����݂��Ȃ��ꍇ�̓G���[�𔭐����܂��B
     * 
     * @param attendance �X�V�Ώۂ̏o�����
     * @return 
     */
    public Attendance update(Attendance attendance) throws IllegalArgumentException {
        if(attendance == null) {
            throw new IllegalArgumentException("Error! Input attendance is null.");
        } else if(searchFromKey(attendance.getKey()) == null){
            throw new IllegalArgumentException("Error! Target attendance does not exist. Key is " + attendance.getKey());
        }
        GlobalTransaction gtx = Datastore.beginGlobalTransaction();
        gtx.put(attendance);
        gtx.commit();
        return attendance;
    }

    /**
     * �o�����𕡐����X�V���܂�
     * �X�V�Ώۂ����݂��Ȃ��ꍇ�̓G���[�𔭐����܂��B
     * 
     * @param attendanceList �X�V�Ώۂ̏o�����̃��X�g
     * @return �X�V���ꂽ�o�����̃��X�g
     */
    public List<Attendance> updateList(List<Attendance> attendanceList) {
        if(attendanceList == null || attendanceList.size() == 0) {
            throw new IllegalArgumentException("Error! Input attendanceList is null.");
        } else {
            // �s���ȏo����񂪂���ꍇ�͍X�V���Ȃ�
            for(Attendance attendance : attendanceList) {
                if(attendance == null) {
                    throw new IllegalArgumentException("Error! Input attendance is null.");
                } else if(searchFromKey(attendance.getKey()) == null){
                    throw new IllegalArgumentException("Error! Target attendance does not exist. Key is " + attendance.getKey());
                }                
            }
            // ���ۂ̍X�V����
            return registList(attendanceList);
        }
    }

    /**
     * �o�������P���폜���܂��B
     * 
     * @param attendance �폜�Ώۂ̏o�����
     * @return �폜�̌��ʁi����:true, ���s:false�j
     */
    public boolean delete(Attendance attendance) {
        if(attendance == null) {
            return false;
        } else {
            GlobalTransaction gtx = Datastore.beginGlobalTransaction();
            gtx.delete(attendance.getKey());
            gtx.commit();
            return true;
        }
    }
    
    /**
     * �w������̏o�ȏ���S�č폜���܂��B
     * 
     * @param date
     * @param gtx
     */
    public void deleteList(Date date, GlobalTransaction gtx) {
        List<Attendance> attendanceList = searchFromPracticeDate(date);
        if(attendanceList != null && attendanceList.size() != 0) {
            deleteList(attendanceList, gtx);
        }
    }

    /**
     * �o�����𕡐����폜���܂�
     * 
     * @param attendanceList �폜�Ώۂ̏o�����̃��X�g
     * @throws IllegalArgumentException
     */
    public void deleteList(List<Attendance> attendanceList) throws IllegalArgumentException {
        deleteList(attendanceList, null);
    }
    
    
    /**
     * �o�����𕡐����폜���܂�
     * 
     * @param attendanceList �폜�Ώۂ̏o�����̃��X�g
     * @throws IllegalArgumentException
     */    
    public void deleteList(List<Attendance> attendanceList, GlobalTransaction gtx) throws IllegalArgumentException {
        if(attendanceList == null || attendanceList.size() == 0) {
            throw new IllegalArgumentException("Error! Input attendanceList is null.");
        } else {
            Set<Key> keySet = new HashSet<Key>();
            for(Attendance attendance : attendanceList) {
                keySet.add(attendance.getKey());
            }
            if(gtx == null) {
                gtx = Datastore.beginGlobalTransaction();
                gtx.delete(keySet);
                gtx.commit();
            } else {
                gtx.delete(keySet);
            }
        }        
    }
    
    /**
     * Key�ŏo�������P���擾���܂�
     * 
     * @param key
     * @return
     */
    public Attendance searchFromKey(Key key) {
        if(key == null) {
            return null;
        } else {
            return Datastore.query(t).filter(t.key.equal(key)).asSingle();
        }
    }
    
    /**
     * �����o�[�����͂����o�������������܂��B
     * 
     * @param memberId
     * @return �o�����̃��X�g
     */
    public List<Attendance> searchFromMemberId(String memberId) {
        
        if(memberId == null) {
            return null;
        }
        
        Member member = memberSvc.searchFromId(memberId);
        if (member == null) {
            return null;
        } else {
            return Datastore
                .query(t)
                .filter(t.memberKey.equal(member.getKey()))
                .asList();
        }
    }
    
    /**
     * ����̗��K���̏o�������������܂��B
     * 
     * @param practiceDate ���K���̊J�n����
     * @return �o�����̃��X�g
     */
    public List<Attendance> searchFromPracticeDate(Date practiceDate) {
        
        if(practiceDate == null) {
            return null;
        }
        
        Practice practice = practiceSvc.searchFromStartDateTime(practiceDate);
        if (practice == null) {
            return null;
        } else {
            return Datastore
                .query(t)
                .filter(t.practiceKey.equal(practice.getKey()))
                .asList();
        }
    }

    /**
     * �����o�[�Ɨ��K���ŏo�������P���������܂�
     * 
     * @param memberId
     *            �����o�[ID
     * @param practiceDate
     *            ���K���̊J�n����
     * @return �o�����
     */
    public Attendance searchFromMemberIdAndPracticeDate(String memberId, Date practiceDate) {
        
        if(memberId == null || practiceDate == null) {
            return null;
        }

        Member member = memberSvc.searchFromId(memberId);
        Practice practice = practiceSvc.searchFromStartDateTime(practiceDate);
        if(member == null || practice == null){
            return null;
        } else {
            return Datastore
                .query(t)
                .filter(
                    t.memberKey.equal(member.getKey()),
                    t.practiceKey.equal(practice.getKey()))
                .asSingle();
        }
    }
    
    /**
     * �����o�[�̏o�ȏ����w����ԕ��S�Ď擾���܂��B
     * 
     * @param memberId
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<Attendance> searchFromMemberIdAndPracticeDateSpan(String memberId, Date fromDate, Date toDate){
        
        if(memberId == null || fromDate == null || toDate == null) {
            return null;
        }
        
        List<Attendance> attendanceList = searchFromMemberId(memberId);
        if(attendanceList == null) {
            return null;
        }
        
        // �w����Ԃ�Entity�������o����B
        List<Attendance> returnList = new ArrayList<Attendance>();
        
        Date fromDateOnly = DateUtil.clearTimePart(fromDate);
        Date toDateOnly = DateUtil.clearTimePart(toDate);
        
        for(Attendance attendance : attendanceList){
            Date practiceDate = DateUtil.clearTimePart(attendance.getPracticeRef().getModel().getStartDate());
            if(practiceDate.compareTo(fromDateOnly) >= 0 && practiceDate.compareTo(toDateOnly) <= 0) {
                returnList.add(attendance);
            }
        }
        
        if(returnList.size() == 0) { 
            return null;
        } else {
            return returnList;
        }
    }

    /**
     * �����o�[�̏o�������A�w�茎�ɂ��đS�Ď擾���܂��B
     * 
     * @param memberId
     * @param year
     * @param month
     * @return
     */
    public List<Attendance> searchFromMemberIdAndPracticeMonth(String memberId, int year, int month){
        String yearStr = Integer.toString(year);
        String monthStr = Integer.toString(month);
        if(monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }
        
        Date fromDate = DateUtil.toDate(yearStr + "-" + monthStr + "-" + "01", DateUtil.ISO_DATE_PATTERN); 
        Date toDate = getEndOfMonth(fromDate);

        return searchFromMemberIdAndPracticeDateSpan(memberId, fromDate, toDate);
    }


    /**
     * Attendance �� key �𐶐����܂��B
     * Key is intended to be "Attendance("Member(hoge)/Practice("hoge")")"
     * 
     * @param memberKey
     * @param practiceKey
     * @return
     */
    protected Key generateKey(Key memberKey, Key practiceKey){
        return Datastore.createKey(memberKey, t, practiceKey.toString());
    }

    /**
     * Attendance �� key �𐶐����܂��B
     * Key is intended to be "Attendance("Member(hoge)/Practice("hoge")")"
     * 
     * @param attendance
     * @return
     */
    protected Key generateKey(Attendance attendance) {
        if(attendance == null || attendance.getMemberKey() == null || attendance.getPracticeKey() == null) {
            return null;
        } else {
            return generateKey(attendance.getMemberKey(), attendance.getPracticeKey());
        }
    }
    
    /**
     * �w�肳�ꂽ���t�̌��������擾���܂�
     * 
     * @param date
     * @return
     */
    protected Date getEndOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
    
    /**
     * �����o�[���Ɨ��K��񂩂珉�������ꂽ�o�������擾���܂��B
     * 
     * @param member
     * @param practice
     * @return
     */
    public Attendance getInitalizedAttendance(Member member, Practice practice) {
        Attendance attendance = new Attendance();
        
        attendance.setAttendance(0);
        attendance.setMemberKey(member.getKey());
        attendance.setPracticeKey(practice.getKey());
        attendance.setFinished(false);
        attendance.setInputFlg(false);
        attendance.getMemberRef().setModel(member);
        attendance.getPracticeRef().setModel(practice);
        return attendance;
    }

    /**
     * ���K���̓��͍ς݃t���O���I�t�ɂ��܂�
     * 
     * @param key
     * @return
     */
    public Attendance updateInputFlgToFalse(Key key) throws IllegalArgumentException {
        if(key == null) {
            throw new IllegalArgumentException("Error! Input key is null.");
        }
        Attendance attendance = searchFromKey(key);
        attendance.setInputFlg(false);
        return update(attendance);
    }
}

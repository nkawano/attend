package slim3.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.GlobalTransaction;
import org.slim3.util.DateUtil;

import slim3.meta.PracticeMeta;
import slim3.model.Practice;

import com.google.appengine.api.datastore.Key;


public class PracticeService {

    public final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm";
    public final String DATE_PATTERN = "yyyy-MM-dd";
    public final String TIME_PATTERN = "HH:mm";
    
    private PracticeMeta t = new PracticeMeta();
    
    /**
     * ���K����1�o�^���܂�
     * 
     * @param practice �o�^�Ώۂ̗��K���
     * @return 
     * @throws IllegalArgumentException ���͕s����O
     */
    public Practice regist(Practice practice) throws IllegalArgumentException {
        return regist(practice, null);
    }
    
    /**
     * ���K����1�o�^���܂�
     * 
     * @param practice �o�^�Ώۂ̗��K���
     * @return 
     * @throws IllegalArgumentException ���͕s����O
     */    
    public Practice regist(Practice practice, GlobalTransaction gtx) throws IllegalArgumentException {
        
        if(searchFromStartDateTime(practice.getStartDate()) != null) {
            throw new IllegalArgumentException("���ɑ��݂�����K���ł��B");
        }

        // Transaction���w�肳��Ă��Ȃ��Ƃ�
        if(gtx == null) {
            gtx = Datastore.beginGlobalTransaction();
            gtx.put(practice);
            gtx.commit();
        } else { 
            gtx.put(practice);
        }

        return practice;    
    }
    
    /**
     * Key��1���擾���s���܂�
     * 
     * @param key key
     * @return ���K���
     */
    public Practice searchFromKey(Key key) {
        return Datastore.query(t).filter(t.key.equal(key)).asSingle();
    }
    
    /**
     * ���K�����擾���܂��B
     * �J�n�������x�����Ɏw�萔�B
     * 
     * @param num �擾�����
     */
    public List<Practice> search(int num) {
        return Datastore.query(t).sort(t.startDate.asc).limit(num).asList();
    }

    /**
     * ���K�����擾���܂�
     * ���t�w��������ꍇ�́A�w����ȍ~�̂݁B
     * 
     * @param date �w���
     * @param num ��
     * @return
     */
    public List<Practice> search(Date date, int num) {
        if(date == null) {
            return search(num);
        } else {
            return Datastore
                .query(t)
                .filter(t.startDate.greaterThanOrEqual(date))
                .sort(t.startDate.asc)
                .asList();
        }
    }
    
    /**
     * �J�n�����ŗ��K�����P���擾���܂�
     * 
     * @param startDate �J�n����
     * @return ���K���
     */
    public Practice searchFromStartDateTime(Date startDate) {
        return Datastore.query(t).filter(t.startDate.equal(startDate)).asSingle();
    }
    
    /**
     * �J�n�����w�肵�ė��K���𕡐��擾���܂��B
     * 
     * @param startDate �J�n��
     */
    public List<Practice> searchFromYearAndMonth(int year, int month){
        
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);    // 1����0�ɂȂ�炵���E�E�E
        Date fromDate = DateUtil.clearTimePart(cal.getTime());
        cal.add(Calendar.MONTH , 1);
        Date toDate = DateUtil.clearTimePart(cal.getTime());
        
        return Datastore
            .query(t)
            .filter(t.startDate.greaterThanOrEqual(fromDate))
            .filterInMemory(t.startDate.lessThan(toDate))
            .asList();
    }

    /**
     * �J�n�����w�肵�ė��K���𕡐��擾���܂��B
     * 
     * @param startDate �J�n��
     */
    public List<Practice> search(Date startDate){
        
        Date fromDate = DateUtil.clearTimePart(startDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        cal.add(Calendar.DATE, 1);
        Date toDate = cal.getTime();
        
        return Datastore
            .query(t)
            .filter(t.startDate.greaterThan(fromDate))
            .filterInMemory(t.startDate.lessThan(toDate))
            .asList();
    }

    /**
     * ���K�����X�V���܂��B
     * 
     * @param practice �X�V������K���
     * @return �X�V��̗��K���
     * @throws IllegalArgumentException ���͕s����O
     */
    public Practice update(Practice practice) throws IllegalArgumentException {
        
        GlobalTransaction gtx = Datastore.beginGlobalTransaction();
        
        Practice testingPractice = searchFromStartDateTime(practice.getStartDate());
        
        if(testingPractice != null) {
            if(testingPractice.getKey().toString().equals(practice.getKey().toString())){
                // ���K�J�n�������X�V����Ă��Ȃ��ꍇ�́A�ʏ�̍X�V
                gtx.put(practice);
            } else {
                // �X�V��̗��K�J�n���������łɓo�^����Ă���ꍇ�A�G���[�Ƃ���B
                throw new IllegalArgumentException("�X�V��̗��K�J�n�����͂��łɓo�^����Ă��܂��B");                
            }
        } else {
            // ���K�J�n�������X�V���ꂽ�ꍇ�A�����̗��K�����폜���āA�V�������K�����쐬����B
            delete(practice.getKey(), gtx);
            practice = regist(practice, gtx);
        }

        gtx.commit();

        return practice;
    }

    /**
     * Key�ŗ��K�����폜���܂��B
     * 
     * @param key key
     */
    public boolean delete (Key key) {
        return delete(key, null);
    }
    
    /**
     * Key�ŗ��K�����폜���܂��B
     * 
     * @param key
     * @param tx current Transaction
     */
    public boolean delete (Key key, GlobalTransaction gtx) {
        
        Practice practice = searchFromKey(key);

        if (practice == null) {
            return false;
        } else {
            AttendanceService svc = new AttendanceService();
            if (gtx == null) {
                // Transaction���w�肳��Ă��Ȃ��Ƃ�
                gtx = Datastore.beginGlobalTransaction();
                gtx.delete(key);
                svc.deleteList(searchFromKey(key).getStartDate(), gtx);
                gtx.commit();
            } else {
                gtx.delete(key);
                svc.deleteList(searchFromKey(key).getStartDate(), gtx);
            }
            return true;
        }
    }
    
    /**
     * �J�n�����ŗ��K�����폜���܂��B
     * 
     * @param startDate
     */
    public boolean delete (Date startDate) {
        
        Practice practice = searchFromStartDateTime(startDate);
        
        if(practice != null) {
            delete(practice.getKey());
            return true;
        } else {
            return false;
        }
    }
    
}

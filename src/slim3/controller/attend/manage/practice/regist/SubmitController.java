package slim3.controller.attend.manage.practice.regist;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.DateUtil;

import slim3.model.Practice;
import slim3.service.PracticeService;

public class SubmitController extends Controller {

    private PracticeService svc = new PracticeService();
    
    @Override
    public Navigation run() throws Exception {
        
        if(!validate()) {
            return forward(basePath);
        }
        
        Practice practice = construct();
        
        if(!customValidate(practice)) {
            return forward(basePath);
        }
        
        svc.regist(practice);
        
        requestScope("practice", practice);
        
        return forward("submit.jsp");
    }
    
    
    /**
     * ��ʓ��̓`�F�b�N���s�Ȃ��܂��B
     * 
     * @return �`�F�b�N����
     */
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("practiceDate", v.required(), v.dateType(DateUtil.ISO_DATE_PATTERN), v.regexp("[0-9-]+", "�t�H�[�}�b�g���ςł��B"));
        v.add("startTime", v.required(), v.dateType(svc.TIME_PATTERN));
        v.add("endTime", v.required(), v.dateType(svc.TIME_PATTERN));
        v.add("gatheringTime", v.dateType(svc.TIME_PATTERN));
        return v.validate();
    }
    
    /**
     * ��ʓ��̓`�F�b�N���s���܂��B
     * 
     * @param practice
     * @return
     */
    protected boolean customValidate(Practice practice) {
        if(svc.searchFromStartDateTime(practice.getStartDate()) != null) {
            errors.put("inputError", "���łɑ��݂�����K�J�n�����ł��B");
            return false;
        }
        return true;
    }
    
    /**
     * ���̓f�[�^���G���e�B�e�B�ɋl�ߑւ��܂�
     */
    protected Practice construct() {
        
        Practice practice = new Practice();
        
        // �J�n�������쐬����
        practice.setStartDate(DateUtil.toDate(asString("practiceDate") + " " + asString("startTime") , svc.DATETIME_PATTERN));
        
        // �I���������쐬����
        practice.setEndDate(DateUtil.toDate(asString("practiceDate") + " " + asString("endTime") , svc.DATETIME_PATTERN));

        // ���K�ꏊ
        practice.setPracticePlace(asString("practicePlace"));
        
        // �W���������쐬����B�i���t�ɂ��ẮA���K�J�n�����Ɠ���ƂȂ�j
        if(!asString("gatheringTime").isEmpty()) {
            practice.setGatheringDate(DateUtil.toDate(asString("practiceDate") + " " + asString("gatheringTime") , svc.DATETIME_PATTERN));
        }
        
        // �W���ꏊ
        practice.setGatheringPoint(asString("gatheringPoint"));
        
        // ���l
        practice.setRecital(asString("racital"));
        
        return practice;
    }
}

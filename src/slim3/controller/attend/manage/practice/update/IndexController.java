package slim3.controller.attend.manage.practice.update;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.util.DateUtil;

import slim3.meta.PracticeMeta;
import slim3.model.Practice;
import slim3.service.PracticeService;

public class IndexController extends Controller {
    
    private PracticeMeta meta = new PracticeMeta();
    private PracticeService svc = new PracticeService();

    @Override
    public Navigation run() throws Exception {

        // ���̓`�F�b�N
        if(!validate()){
            errors.put("selectError", "1�I��ŉ������B");
            return forward("/attend/manage/practice/search/");   // index.jsp���ƈႤ�Ƃ���ɔ��ł��܂�
        }
        
        // �{�^���ɂ���đJ�ڐ��U�蕪����iupdate�̏ꍇ�͓��R���g���[���ɂď����B�j
        if(asString("update") != null) {
            // �X�V�{�^���������ꂽ�ꍇ
            Practice practice = svc.searchFromKey(asKey("key"));
            
            request.setAttribute("practiceDate", DateUtil.toString(practice.getStartDate(), DateUtil.ISO_DATE_PATTERN));
            request.setAttribute("startTime", DateUtil.toString(practice.getStartDate(), "HH:mm"));
            request.setAttribute("endTime", DateUtil.toString(practice.getEndDate(), "HH:mm"));
            request.setAttribute("practicePlace", practice.getPracticePlace());
            request.setAttribute("gatheringTime", DateUtil.toString(practice.getGatheringDate(), "HH:mm"));
            request.setAttribute("gatheringPoint", practice.getGatheringPoint());
            request.setAttribute("racital", practice.getRecital());
            request.setAttribute("key", practice.getKey());
            
            return forward("index.jsp");
            
        } else if(asString("delete") != null) {
            // �폜�{�^���������ꂽ�ꍇ
            return forward("/attend/manage/practice/delete/");
        }
        
        // TODO �z��O�̂��߃G���[��ʂɑJ�ڂ���K�v������
        return forward("index.jsp");
    }
    
    /**
     * ��ʓ��̓`�F�b�N���s�Ȃ��܂��B
     * 
     * @return �`�F�b�N����
     */
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add(meta.key, v.required());
        return v.validate();
    }
}

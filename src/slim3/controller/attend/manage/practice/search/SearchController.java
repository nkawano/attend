package slim3.controller.attend.manage.practice.search;

import java.util.Date;
import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import slim3.model.Practice;
import slim3.service.PracticeService;

public class SearchController extends Controller {
    
    private static PracticeService svc = new PracticeService();

    @Override
    public Navigation run() throws Exception {
        
        if(customValidate()){
            Date date = asDate("date", "yyyy-MM");
            List<Practice> list = null;
            if(date != null) {
                list = svc.search(date, 50);
                if(list.size() > 0) { 
                    requestScope("practiceList", list);
                } else { 
                    // ���������Ȃ��|�̃��b�Z�[�W�����
                    errors.put("cannotFind", "�݂���܂��[��B");
                }
            } else {
                // �G���[���b�Z�[�W����
                errors.put("cannotSearch", "���͂��Ă��������B");            
            }
        }
        return forward("index.jsp");
    }
    
    private boolean customValidate() {
        
        try {
            asDate("date", "yyyy-MM");
        } catch (Exception e) {
            errors.put("cannotSearch", "���͂��ςł����ǁA�A�A");
            return false;
        }
        
        return true;
    }
}

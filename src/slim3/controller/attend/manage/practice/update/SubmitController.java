package slim3.controller.attend.manage.practice.update;

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

        if (asString("cancel") != null) {
            return forward("/attend/manage/practice/search/");
        } else if (asString("submit") != null) {

            if (!validate()) {
                return forward("index.jsp");
            }

            Practice practice = construct();

            if (!customValidate(practice)) {
                return forward("index.jsp");
            }
            
            svc.update(practice);

            requestScope("practice", practice);

            return forward("submit.jsp");
        }
        throw new RuntimeException("予期してないよ。");

    }

    /**
     * 画面入力チェックを行ないます。
     * 
     * @return チェック結果
     */
    protected boolean validate() {
        Validators v = new Validators(request);
        v.add("practiceDate", v.required(), v.dateType(DateUtil.ISO_DATE_PATTERN), v.regexp("[0-9-]+", "フォーマットが変です。"));
        v.add("startTime", v.required(), v.dateType(svc.TIME_PATTERN));
        v.add("endTime", v.required(), v.dateType(svc.TIME_PATTERN));
        v.add("gatheringTime", v.dateType(svc.TIME_PATTERN));
        return v.validate();
    }

    /**
     * 画面入力チェックを行います。
     * 
     * @param practice
     * @return
     */
    protected boolean customValidate(Practice practice) {
        Practice origPractice = svc.searchFromStartDateTime(practice.getStartDate());
        if(origPractice != null && !origPractice.getKey().toString().equals(practice.getKey().toString())) {
            errors.put("inputError", "すでに存在する練習開始日時です。");
            return false;
        }
        return true;
    }
    
    /**
     * 入力データをエンティティに詰め替えます
     */
    protected Practice construct() {

        Practice practice = new Practice();

        // 開始日時を作成する
        practice.setStartDate(DateUtil.toDate(asString("practiceDate")
            + " "
            + asString("startTime"), svc.DATETIME_PATTERN));

        // 終了日時を作成する
        practice.setEndDate(DateUtil.toDate(asString("practiceDate")
            + " "
            + asString("endTime"), svc.DATETIME_PATTERN));

        // 練習場所
        practice.setPracticePlace(asString("practicePlace"));

        // 集合日時を作成する。（日付については、練習開始日時と同一となる）
        if (!asString("gatheringTime").isEmpty()) {
            practice.setGatheringDate(DateUtil.toDate(asString("practiceDate")
                + " "
                + asString("gatheringTime"), svc.DATETIME_PATTERN));
        }

        // 集合場所
        practice.setGatheringPoint(asString("gatheringPoint"));

        // 備考
        practice.setRecital(asString("racital"));

        // Key
        practice.setKey(asKey("key"));

        return practice;
    }
}

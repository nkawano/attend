package slim3.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import slim3.taglib.AttendanceForEach.FutureIdx;

//import slim3.taglib.AttendanceForEach.FutureIdx;

/**
 * CheckPastDateTag�Ŏw�肳�ꂽ���t���������̏ꍇ�ɁA �{�^�O�̃{�f�B�����o�͂��܂��B<br/>
 * ����useCount��true�ɐݒ肵���ꍇ�A�y�[�W���ɖ{�^�O���o���������_�ł̖{�^�O�̌ďo�񐔂�ێ����܂��B
 * useCount�̃f�t�H���g�l��false�ł��B �Ăяo���񐔂��擾����ꍇ��requestScope����useCount.count�Ŏ擾���Ă��������B
 *
 * @author naoyuki
 *
 */
public class FutureTag implements Tag {

    private static final long serialVersionUID = 1L;

    private PageContext pageContext;
    private Tag parent;

    public FutureTag() {

    }

    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public PageContext getPageContext() {
        return pageContext;
    }

    public void setParent(Tag parent) {
        this.parent = parent;

    }

    public Tag getParent() {
        return this.parent;
    }

    public int doStartTag() throws JspException {

        CheckPastDateTag checkParent = (CheckPastDateTag) getParent();

        if (checkParent.isPast()) {
            return SKIP_BODY;
        } else {
            return EVAL_BODY_INCLUDE;
        }
    }

    public int doEndTag() throws JspException {

        try {
            CheckPastDateTag checkParent = (CheckPastDateTag) getParent();
            AttendanceForEach parentsPearent =
                (AttendanceForEach) checkParent.getParent();

            if (!checkParent.isPast()) {
                FutureIdx fci =
                    (FutureIdx) parentsPearent.getPageContext().getRequest().getAttribute(
                        AttendanceForEach.FUTURE_IDX);
                fci.setIdx(fci.getIdx() + 1);
            }
        } catch (ClassCastException e) {
            new JspException("�^�O�̎g�������Ԉ���Ă܂��B");
        }
        return EVAL_PAGE;
    }

    public void release() {
    }

}

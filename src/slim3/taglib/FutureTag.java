package slim3.taglib;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

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

    private static final String REQUEST_LABEL = "useCount";

    private PageContext pageContext;
    private Tag parent;
    private boolean useCount = false;;
    private InnerCount innerCount;
    private ServletRequest request;

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

        if (!getPageContext().getRequest().equals(request)) {
            InnerCount obj = new InnerCount();
            obj.count = 0;
            setInnerCount(obj);
            request = getPageContext().getRequest();
            getPageContext().getRequest().setAttribute(REQUEST_LABEL, obj);
        }

        CheckPastDateTag checkParent = (CheckPastDateTag) getParent();

        if (checkParent.isPast()) {
            return SKIP_BODY;
        } else {
            return EVAL_BODY_INCLUDE;
        }
    }

    public int doEndTag() throws JspException {

        if (useCount) {
            CheckPastDateTag checkParent = (CheckPastDateTag) getParent();
            if (!checkParent.isPast()) {
                InnerCount obj = getInnerCount();
                obj.count++;
                getPageContext().getRequest().setAttribute(REQUEST_LABEL, obj);
            }
        }

        return EVAL_PAGE;
    }

    public void release() {
    }

    public boolean isUseCount() {
        return useCount;
    }

    public void setUseCount(boolean useCount) {
        this.useCount = useCount;
    }

    public InnerCount getInnerCount() {
        return innerCount;
    }

    public void setInnerCount(InnerCount innerCount) {
        this.innerCount = innerCount;
    }

    // �J�E���g�p�����N���X
    public class InnerCount {

        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

    }

}

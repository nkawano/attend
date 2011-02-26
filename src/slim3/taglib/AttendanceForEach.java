package slim3.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.taglibs.standard.tag.rt.core.ForEachTag;

public class AttendanceForEach extends ForEachTag {

    private static final long serialVersionUID = 1L;

    static final String FUTURE_IDX = "future";

    @Override
    public int doStartTag() throws JspException {

        FutureIdx futureIxd = new FutureIdx();

        futureIxd.setIdx(0);// �C���f�b�N�X�̏�����

        pageContext.getRequest().setAttribute(FUTURE_IDX, futureIxd);

        return super.doStartTag();
    }

    public PageContext getPageContext() {
        return super.pageContext;
    }

    // �������C���f�b�N�X�p�����N���X
    public class FutureIdx {

        private int idx;

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

    }

}

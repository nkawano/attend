package slim3.taglib;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * CheckPastDateTagで指定された日付が未来日の場合に、 本タグのボディ部を出力します。<br/>
 * 属性useCountをtrueに設定した場合、ページ中に本タグが出現した時点での本タグの呼出回数を保持します。
 * useCountのデフォルト値はfalseです。 呼び出し回数を取得する場合はrequestScopeからuseCount.countで取得してください。
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

    // カウント用内部クラス
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

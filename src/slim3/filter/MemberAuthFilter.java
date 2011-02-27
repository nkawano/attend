package slim3.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import slim3.constants.Constants;

public class MemberAuthFilter implements Filter {

    // ��������OK�ȃy�[�W�ւ̃p�X
    private static List<String> NO_CONDITIONS;

    public void init(FilterConfig config) throws ServletException {

        NO_CONDITIONS = new ArrayList<String>();
        NO_CONDITIONS.add("/attend/index.jsp");
        NO_CONDITIONS.add("/attend/inputAttendance/index.jsp");
        NO_CONDITIONS.add("/attend/inputAttendance/submit.jsp");

    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        if (req instanceof HttpServletRequest) {
            HttpServletRequest httpReq = (HttpServletRequest) req;

            String path = httpReq.getServletPath();
            System.out.println(path);

            if (checkBasic(httpReq, res, path, chain)) {
                // ��{�`�F�b�N��true�̏ꍇ�����ŏI��
                chain.doFilter(httpReq, res);
                return;
            }

            // �����`�F�b�N
            if(!checkMemberAuthAndDispatch(httpReq, res, path)){
                //���������̏ꍇ�̓g�b�v��ʂ�foraward���Z�b�g
                setForwardToTop(httpReq, res);
            }

            chain.doFilter(req, res);

        } else {
            throw new IllegalAccessError("������񂪎Q�Ƃł��Ȃ����߃G���[");
        }


    }

    private boolean checkBasic(HttpServletRequest httpReq, ServletResponse res,
            String path, FilterChain chain) throws IOException,
            ServletException {

        if (path.startsWith("/_ah")) {
            // TODO �Ǘ��y�[�W�͖{�Ԃ��Ƃǂ�����H
            return true;
        }

        if (NO_CONDITIONS.contains(path)) {
            // ��������OK�̃y�[�W�͏����I���
            return true;
        }

        HttpSession session = httpReq.getSession();
        if (session.getAttribute(Constants.SESSION_KEY_LOGIN_USER) == null) {
            // ���O�C�����ĂȂ��ꍇ�́A�g�b�v��ʂ��w�肵��OK
            setForwardToTop(httpReq, res);
            return true;
        }

        // ���O�C�����Ă����ԂŁA������񂪃Z�b�V�����ɂȏꍇ�̓G���[
        @SuppressWarnings("unchecked")
        Map<String, Integer> auth =
            (Map<String, Integer>) session
                .getAttribute(Constants.SESSION_KEY_LOGIN_USER_AUTH);

        if (auth == null) {
            throw new IllegalAccessError("������񂪎Q�Ƃł��Ȃ����߃G���[");
        }

        return false;

    }

    private boolean checkMemberAuthAndDispatch(HttpServletRequest httpReq,
            ServletResponse res, String path) throws ServletException,
            IOException {

//        HttpSession session = httpReq.getSession();

//        @SuppressWarnings("unchecked")
//        Map<String, Integer> auth =
//            (Map<String, Integer>) session
//                .getAttribute(Constants.SESSION_KEY_LOGIN_USER_AUTH);

//        if (path.startsWith("/attend/manage/attendance")) {
//            return false;
//        }

        return true;

    }

    private void setForwardToTop(HttpServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        //req.getRequestDispatcher("/attend/index.jsp").forward(req, res);
//        HttpServletResponse httpRes = (HttpServletResponse)res;

        System.out.println(req.getProtocol());
        System.out.println(req.getPathInfo());
        System.out.println(req.getQueryString());
        System.out.println(req.getRequestURL());


//        httpRes.sendRedirect("http://localhost:8888/attend/");
        req.getRequestDispatcher("/attend/index.jsp").forward(req, res);

        return;
    }

}

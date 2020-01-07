package main.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelloFilter implements Filter {
    private FilterConfig _filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        StringBuffer url = request.getRequestURL();
        String sessionId = request.getRequestedSessionId();
//        servletResponse.setContentType("text/html;charset=UTF-8");
        // filterChain doFilter 执行前使用 response 和执行之后使用有着不同的效果。
//        servletResponse.getWriter().write("你好，index.jsp by helloFilter");
        filterChain.doFilter(servletRequest, servletResponse);
        servletResponse.getWriter().write("你好，index.jsp by helloFilter");
    }

    @Override
    public void destroy() {
    }
}

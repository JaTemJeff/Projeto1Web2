package edu.utfpr.filtros;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class FiltroLogin implements Filter {
    
    @Override
        public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {    
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String path = request.getRequestURI();
        System.out.println(path);
        String loginURI = request.getContextPath() + "/login";
        System.out.println(loginURI);
        String cadastroURI = request.getContextPath()+ "/cadastro";

        System.out.println(session.getAttribute("usuario"));
        boolean loggedIn = session.getAttribute("usuario") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean cadastroRequest = request.getRequestURI().equals(cadastroURI);
        boolean cssRequest = path.endsWith(".css");

        if (loggedIn || loginRequest || cadastroRequest || cssRequest) {
            System.out.println("true");
            chain.doFilter(request, response);
        } else {
            System.out.println("false");
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {}
}

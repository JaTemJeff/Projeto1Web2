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
        HttpSession session = request.getSession(false);
        String path = request.getRequestURI();
        if(path.endsWith(".css")){
          chain.doFilter(request,response);
        }
        String loginURI = request.getContextPath() + "/login";
        String cadastroURI = request.getContextPath()+ "/cadastro";


        boolean loggedIn = session != null && session.getAttribute("usuario") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean cadastroRequest = request.getRequestURI().equals(cadastroURI);

        if (loggedIn || loginRequest || cadastroRequest) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }
    }

     @Override
        public void destroy() {}
}

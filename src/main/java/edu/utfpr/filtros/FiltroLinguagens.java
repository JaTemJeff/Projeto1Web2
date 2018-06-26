package edu.utfpr.filtros;

import java.io.IOException;
import java.util.ResourceBundle;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class FiltroLinguagens implements Filter{
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {    
        ResourceBundle bundle = ResourceBundle.getBundle("messages", req.getLocale());
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        req.setAttribute("bundle", bundle);
        chain.doFilter(req, res);
    }
    
    @Override
    public void destroy() {}

}

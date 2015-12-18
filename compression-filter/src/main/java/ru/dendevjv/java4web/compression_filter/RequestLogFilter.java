package ru.dendevjv.java4web.compression_filter;

import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.time.Instant;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestLogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        System.out.println("Entering RequestLogFilter.doFilter()");
        Instant time = Instant.now();
        StopWatch timer = new StopWatch();
        try {
            timer.start();
            chain.doFilter(request, response);
        } finally {
            timer.stop();
            HttpServletRequest in = (HttpServletRequest) request;
            HttpServletResponse out = (HttpServletResponse) response;
            String length = out.getHeader("Content-Length");
            String contentLength = getHeader(out, "Content-Length");
            if (length == null || length.length() == 0) {
                length = "-";
            }
            System.out.println(in.getRemoteAddr() + " -- [" + time + "]" +
                    " \"" + in.getMethod() + " " + in.getRequestURI() + " " +
                    in.getProtocol() + "\" " + out.getStatus() + " " + length + 
                    " Headers:" + contentLength + " " + timer);
            System.out.println("Leaving RequestLogFilter.doFilter()");
        }
    }

    private String getHeader(HttpServletResponse out, String string) {
        Collection<String> headerNames = out.getHeaderNames();
        return headerNames.toString();
    }

    @Override
    public void destroy() {}

}

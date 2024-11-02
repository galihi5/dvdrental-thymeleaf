package com.gaw.dvdrental.config;

import jakarta.servlet.*;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class LogFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("### LogFilter.init");
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    System.out.println("### LogFilter.doFilter");
    servletRequest
        .getParameterMap()
        .forEach(
            (k, v) -> {
              System.out.println(k);
              for (int i = 0; i < v.length; i++) {
                System.out.println(v[i]);
              }
            });
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {
    System.out.println("### LogFilter.destroy");
    Filter.super.destroy();
  }
}

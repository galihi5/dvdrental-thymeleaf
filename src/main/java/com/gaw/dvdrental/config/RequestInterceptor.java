package com.gaw.dvdrental.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object object) {
    System.out.println("================== 1 - pre handle.");
    System.out.println("METHOD type:" + request.getMethod());
    System.out.println("Request URI: " + request.getRequestURI());
    System.out.println("Servlet PATH: " + request.getServletPath());
    // check which controller method is requested
    if (object instanceof HandlerMethod) {
      // can be added different logics
      Class<?> controllerClass = ((HandlerMethod) object).getBeanType();
      String methodName = ((HandlerMethod) object).getMethod().getName();
      System.out.println("Controller name: " + controllerClass.getName());
      System.out.println("Method name:" + methodName);
    }
    System.out.println("-------------- 1 - pre handle.");
    return true;
  }

  @Override
  public void postHandle(
      HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) {
    System.out.println("================== 2 - post handle.");
    //        model.getModel().entrySet().forEach(System.out::println);
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request,
      HttpServletResponse response,
      Object object,
      Exception exception) {
    if (exception != null) {
      // exception handle part
      System.out.println("An error occured.");
    }
    System.out.println("================== 3 - after completion.");
  }
}

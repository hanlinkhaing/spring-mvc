package com.demo.springmvc.cofig;

import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Locale;

@Configuration
@ControllerAdvice
public class WebConfig implements WebMvcConfigurer {

  private static Logger logger= LoggerFactory.getLogger(WebConfig.class);

  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    sessionLocaleResolver.setDefaultLocale(Locale.US);
    return sessionLocaleResolver;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Bean
  public LocalValidatorFactoryBean validator() {
    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource());
    return bean;
  }

  @Bean
  public PrettyTime prettyTime(){
    PrettyTime prettyTime=new PrettyTime();
    return  prettyTime;
  }
  @Bean
  public SpringSecurityDialect springSecurityDialect(){
    SpringSecurityDialect springSecurityDialect=
            new SpringSecurityDialect();
    return springSecurityDialect;
  }


  @ExceptionHandler({EntityNotFoundException.class, ConstraintViolationException.class})
  public ModelAndView
  handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest request){

    ModelAndView mv=new ModelAndView();
    mv.addObject("url",request.getRequestURL());
    mv.addObject("exception",ex);
    mv.addObject("msg",ex.getMessage());
    mv.setViewName("notFound");

    return mv;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder(){
    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }


  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
      registry.addRedirectViewController("/","/products");
  }
}

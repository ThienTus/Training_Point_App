/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtt.configs;

import com.dtt.converters.DateConverter;
import com.dtt.formatters.ActivityFormatter;
import com.dtt.formatters.ArticleFormatter;
import com.dtt.formatters.FacultyFormatter;
import com.dtt.formatters.ParticipantFormatter;
import com.dtt.formatters.ParticipationTypeFormatter;
import com.dtt.formatters.UserFormatter;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author ThienTu
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.dtt.controllers",
    "com.dtt.repositories",
    "com.dtt.services",
    "com.dtt.validator",
    "com.dtt.components",
    "com.dtt.utils"
})
public class WebApplicationContextConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/javascript/**").addResourceLocations("/resources/javascript/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
    }
//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver(){
//        InternalResourceViewResolver r = new InternalResourceViewResolver();
//        r.setViewClass(JstlView.class);
//        r.setPrefix("/WEB-INF/pages/");
//        r.setSuffix(".jsp");
//        
//        return r;
//    }


    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource m = new ResourceBundleMessageSource();
        m.setBasename("messages");
        return m;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver() {
            @Override
            public boolean isMultipart(HttpServletRequest request) {
                return FileUploadBase.isMultipartContent(new ServletRequestContext(request));
            }
        };
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new ArticleFormatter());
        registry.addFormatter(new FacultyFormatter());
        registry.addFormatter(new ParticipantFormatter());
        registry.addFormatter(new UserFormatter());
        registry.addFormatter(new ParticipationTypeFormatter());
        registry.addFormatter(new ActivityFormatter());
        registry.addConverter(new DateConverter());
    }

}

package pl.proCvGenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import pl.proCvGenerator.converter.*;
import pl.proCvGenerator.facade.WebFacade;
import pl.proCvGenerator.validator.PatternValidator;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.proCvGenerator")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public ViewResolver viewResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setContentType("text/html; charset=UTF-8");
        viewResolver.setTemplateEngine(engine);

        return viewResolver;
    }

    @Bean
    public PatternValidator patternValidator() {
        return new PatternValidator();
    }

    @Bean
    public CvContentConverter cvContentConverter() {
        return new CvContentConverter();
    }

    @Bean
    public PersonalInfoConverter personalInfoConverter() {
        return new PersonalInfoConverter();
    }

    @Bean
    public EducationConverter educationConverter() {
        return new EducationConverter();
    }

    @Bean
    public EmploymentConverter employmentConverter() {
        return new EmploymentConverter();
    }

    @Bean
    public HobbyConverter hobbyConverter() { return new HobbyConverter(); }

    @Bean
    public WebFacade webFacade() { return new WebFacade(); }

}

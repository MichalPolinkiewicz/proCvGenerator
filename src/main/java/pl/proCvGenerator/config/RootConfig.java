package pl.proCvGenerator.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.proCvGenerator.dao.Dog;
import pl.proCvGenerator.patterns.Pattern;
import pl.proCvGenerator.patterns.PatternImpl1;
import pl.proCvGenerator.patterns.PatternImpl2;
import pl.proCvGenerator.patterns.PatternImpl3;
import pl.proCvGenerator.pdf.PdfCreator;
import pl.proCvGenerator.repository.DogRepository;
import pl.proCvGenerator.service.DbService;
import pl.proCvGenerator.validator.TextValidator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class RootConfig {

    @Bean
    public Map patterns() {
        Map<String, Pattern> patterns = new HashMap<>();
        patterns.put("1", simplePattern());
        patterns.put("2", customPattern());
        patterns.put("3", pattern3());

        return patterns;
    }

    @Bean(name = "1")
    public Pattern simplePattern() {
        return new PatternImpl1();
    }

    @Bean(name = "2")
    public Pattern customPattern() {
        return new PatternImpl2();
    }

    @Bean(name = "3")
    public Pattern pattern3() { return new PatternImpl3(); }

    @Bean
    public PdfCreator pdfCreator() {
        return new PdfCreator();
    }

    @Bean
    public TextValidator charsValidator() {
        return new TextValidator();
    }

    @Bean(name = "messages")
    public Properties properties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("messages.properties"));
        Properties properties = null;

        try {
            propertiesFactoryBean.afterPropertiesSet();
            properties = propertiesFactoryBean.getObject();
        } catch (IOException e) {

        }
        return properties;
    }
}

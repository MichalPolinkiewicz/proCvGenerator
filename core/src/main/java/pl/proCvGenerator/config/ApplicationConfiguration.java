package pl.proCvGenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.proCvGenerator.patterns.helpers.PatternImpl1Helper;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PatternImpl1Helper patternHelper(){
        return new PatternImpl1Helper();
    }
}

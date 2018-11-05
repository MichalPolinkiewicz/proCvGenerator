package pl.proCvGenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.proCvGenerator.patterns.PatternHelper;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PatternHelper patternHelper(){
        return new PatternHelper();
    }
}

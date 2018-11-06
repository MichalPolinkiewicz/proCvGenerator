package pl.proCvGenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.proCvGenerator.patterns.CustomPattern;
import pl.proCvGenerator.patterns.Pattern;
import pl.proCvGenerator.patterns.SimplePattern;
import pl.proCvGenerator.pdf.PdfCreator;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RootConfig {

    @Bean
    public Map patterns() {
        Map<String, Pattern> patterns = new HashMap<>();
        patterns.put("1", simplePattern());
        patterns.put("2", customPattern());

        return patterns;
    }

    @Bean(name = "1")
    public Pattern simplePattern() {
        return new SimplePattern();
    }

    @Bean(name = "2")
    public Pattern customPattern() {
        return new CustomPattern();
    }

    @Bean
    public PdfCreator pdfCreator() {
        return new PdfCreator();
    }

}

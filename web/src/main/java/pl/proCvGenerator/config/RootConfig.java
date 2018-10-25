package pl.proCvGenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.proCvGenerator.facade.CvCreatorFacade;
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
        patterns.put("simplePattern", simplePattern());
        patterns.put("customPattern", customPattern());

        return patterns;
    }

    @Bean
    public Pattern simplePattern() {
        return new SimplePattern();
    }

    @Bean
    public Pattern customPattern() {
        return new CustomPattern();
    }

    @Bean
    public PdfCreator pdfCreator(){
        return new PdfCreator();
    }

    @Bean
    public CvCreatorFacade cvCreatorFacade(){
        return new CvCreatorFacade(pdfCreator());
    }

}

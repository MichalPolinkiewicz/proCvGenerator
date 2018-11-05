package pl.proCvGenerator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.proCvGenerator.config.ApplicationConfiguration;
import pl.proCvGenerator.dto.User;
import pl.proCvGenerator.patterns.Pattern;
import pl.proCvGenerator.patterns.SimplePattern;
import pl.proCvGenerator.pdf.PdfCreator;

public class App {

    public static void main(String[] args) {

        User user = PdfCreator.createUser();
        Pattern pattern = new SimplePattern();
        PdfCreator pdfCreator = new PdfCreator();

        pdfCreator.generate(user, pattern);

    }
}

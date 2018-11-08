package pl.proCvGenerator;

import pl.proCvGenerator.dto.User;
import pl.proCvGenerator.patterns.Pattern;
import pl.proCvGenerator.patterns.PatternImpl1;
import pl.proCvGenerator.patterns.PatternImpl2;
import pl.proCvGenerator.pdf.PdfCreator;

public class App {

    public static void main(String[] args) {

        User user = PdfCreator.createUser();
        Pattern pattern = new PatternImpl2();
        PdfCreator pdfCreator = new PdfCreator();

        pdfCreator.generate(pattern);

    }
}

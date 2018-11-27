package pl.proCvGenerator;

import pl.proCvGenerator.patterns.Pattern;
import pl.proCvGenerator.patterns.PatternImpl2;
import pl.proCvGenerator.patterns.PatternImpl3;
import pl.proCvGenerator.pdf.PdfCreator;

public class App {

    public static void main(String[] args) {

        Pattern pattern = new PatternImpl3();
        PdfCreator pdfCreator = new PdfCreator();

        pdfCreator.generate(pattern);

    }
}

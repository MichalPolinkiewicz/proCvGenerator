package pl.proCvGenerator;

import pl.proCvGenerator.patterns.PatternImpl1;
import pl.proCvGenerator.pdf.PdfCreator;

public class App {

    public static void main(String[] args) {
        PdfCreator pdfCreator = new PdfCreator();
        pdfCreator.generate(new PatternImpl1());
    }
}

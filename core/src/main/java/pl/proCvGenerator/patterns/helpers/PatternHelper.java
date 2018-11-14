package pl.proCvGenerator.patterns.helpers;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

public class PatternHelper {

    public static Paragraph createSimpleParagraph(String text, Font font, int fontSize) {
        font.setSize(fontSize);
        Paragraph paragraph = new Paragraph(text, font);
        return paragraph;
    }
}

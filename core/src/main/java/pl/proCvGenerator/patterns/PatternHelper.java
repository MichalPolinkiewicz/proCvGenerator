package pl.proCvGenerator.patterns;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.LineSeparator;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.fonts.Fonts;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PatternHelper {

    public static List<Education> sortEducationList(List<Education> educationList){
        return educationList
                .stream()
                .sorted(Comparator.comparing(Education::getEndDate).reversed())
                .collect(Collectors.toList());
    }

    public static List<Employment> sortEmploymentList(List<Employment> employmentList){
        return employmentList
                .stream()
                .sorted(Comparator.comparing(Employment::getEndDate).reversed())
                .collect(Collectors.toList());
    }

    public static Paragraph createNormalParagraph(String text, Font font) {
        font.setSize(12);
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setMultipliedLeading(1);

        return paragraph;
    }

    public static Paragraph createClauseParagraph(String text, Font font){
        font.setSize(11);
        Paragraph paragraph = new Paragraph(text, font);

        return paragraph;
    }

    public static Paragraph createHeaderParagraph(String text, Font font) {
        font.setSize(18);
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setMultipliedLeading(1);

        return paragraph;
    }

    public static LineSeparator createSeparatorLine(){
        LineSeparator lineSeparator = new LineSeparator(10,10, BaseColor.BLACK, 10,10);

        return lineSeparator;
    }
}

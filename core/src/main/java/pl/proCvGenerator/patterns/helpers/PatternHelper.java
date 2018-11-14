package pl.proCvGenerator.patterns.helpers;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PatternHelper {

    public static Paragraph createSimpleParagraph(String text, Font font, int fontSize) {
        font.setSize(fontSize);
        Paragraph paragraph = new Paragraph(text, font);
        return paragraph;
    }

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
}

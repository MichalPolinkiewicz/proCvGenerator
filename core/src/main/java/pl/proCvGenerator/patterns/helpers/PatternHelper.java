package pl.proCvGenerator.patterns.helpers;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.RoundingMode.CEILING;

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

    public static BigDecimal validateStringList(List<String> list, int numberOfChars) {
        double totalLinesForSection = 0;

        for (int i = 0; i < list.size(); i++) {
            double itemChars = list.get(i).length();
            int linesForRecord = 1;
            if (itemChars / numberOfChars > 1 && itemChars / numberOfChars < 3) {
                linesForRecord = 2;
            } else if (itemChars / numberOfChars > 3) {
                linesForRecord = 3;
            }
            totalLinesForSection += linesForRecord;
        }
        return BigDecimal.valueOf(totalLinesForSection).setScale(1, CEILING);
    }

    public static BigDecimal validateEducationsList(List<Education> list, int charsInLine, int extraChars) {
        double totalLines = 0;

        for (int i = 0; i < list.size(); i++) {
            Education e = list.get(i);
            totalLines += extraChars + e.getSchoolName().length() + e.getStartDate().length() + e.getEndDate().length() + e.getSubject().length()
                    + e.getDegree().length();
        }
        BigDecimal decimal = BigDecimal.valueOf(totalLines);

        return decimal.divide(BigDecimal.valueOf(charsInLine), CEILING);
    }

    public static BigDecimal validateEmployments(List<Employment> list, int charsInLine, int extraChars) {
        double totalLines = 0;
        for (int i = 0; i < list.size(); i++) {
            Employment e = list.get(i);
            totalLines += extraChars + e.getPosition().length() + e.getCompany().length() + e.getStartDate().length()
                    + e.getEndDate().length() + e.getJobDescription().length();
        }
        BigDecimal decimal = BigDecimal.valueOf(totalLines);
        return decimal.divide(BigDecimal.valueOf(charsInLine), CEILING);
    }

    public static BigDecimal validateString(String s, int charsInLine) {
        BigDecimal a = new BigDecimal(s.length()).setScale(1, CEILING);

        return a.divide(BigDecimal.valueOf(charsInLine), CEILING);
    }
}

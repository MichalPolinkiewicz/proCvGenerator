package pl.proCvGenerator.patterns;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SimplePattern implements Pattern {

    private static final Logger logger = LoggerFactory.getLogger(SimplePattern.class);

    @Override
    public void generateHeader(Document document){
        logger.debug("genarateHeader()...");

        Paragraph paragraph = createHeaderParagraph("CV");
        paragraph.setFontSize(24);
        paragraph.setTextAlignment(TextAlignment.CENTER);

        document.add(paragraph);
    }

    @Override
    public void generatePersonalInfoSection(Document document, PersonalInfo personalInfo) {
        Paragraph paragraph = new Paragraph("");
        document.add(paragraph);
        paragraph = createHeaderParagraph("Dane osobowe:");
        document.add(paragraph);
        document.add(createSeparatorLine());
        paragraph = createNormalParagraph(personalInfo.getName() + " " + personalInfo.getSurname());
        document.add(paragraph);
        paragraph = createNormalParagraph(personalInfo.getCity());
        document.add(paragraph);
        paragraph = createNormalParagraph(personalInfo.getPhone());
        document.add(paragraph);
        paragraph = createNormalParagraph("Email: " + personalInfo.getEmail());
        document.add(paragraph);
    }

    @Override
    public void generateEducationSection(Document document, List<Education> educationList) {
        Paragraph paragraph = new Paragraph("");
        document.add(paragraph);
        paragraph = createHeaderParagraph("Edukacja:");
        document.add(paragraph);
        document.add(createSeparatorLine());

        for (Education education : sortEducationList(educationList)) {
            paragraph = createNormalParagraph(education.getStartDate() + " - " + education.getEndDate() + " - " + education.getSchoolName()
                    + ", " + education.getSubject() + ", " + education.getDegree());
            document.add(paragraph);
        }
    }

    @Override
    public void generateEmploymentSection(Document document, List<Employment> employments) {
        Paragraph paragraph = new Paragraph("");
        document.add(paragraph);
        paragraph = createHeaderParagraph("Doświadczenie zawodowe");
        document.add(paragraph);
        document.add(createSeparatorLine());

        for (Employment e : sortEmploymentList(employments)){
            paragraph = createNormalParagraph(e.getStartDate() + " - " + e.getEndDate() + " - " + e.getCompany() + ".");
            document.add(paragraph);
            paragraph = createNormalParagraph("Stanowisko: " + e.getPosition() + ", zakres obowiązków: " + e.getJobDescription());
            document.add(paragraph);
        }
    }

    @Override
    public void generateHobbySection(Document document, String hobby) {

    }

    @Override
    public void generateClause(Document document) {

    }

    private Paragraph createNormalParagraph(String text) {
        PdfFont font = null;

        try {
            font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN, PdfEncodings.CP1250);

        } catch (IOException e){
            logger.error("ERROR: " + e.getMessage());
        }

        Paragraph paragraph = new Paragraph(text);
        paragraph.setFont(font);
        paragraph.setFontSize(14);
        paragraph.setMultipliedLeading(1);

        return paragraph;
    }

    private Paragraph createHeaderParagraph(String text) {
        Paragraph paragraph = createNormalParagraph(text);
        paragraph.setBold();
        paragraph.setFontSize(18);
        paragraph.setMultipliedLeading(1);

        return paragraph;
    }

    private LineSeparator createSeparatorLine(){
        SolidLine solidLine = new SolidLine(2);
        LineSeparator lineSeparator = new LineSeparator(solidLine);

        return lineSeparator;
    }

    private List<Education> sortEducationList(List<Education> educationList){
        return educationList
                .stream()
                .sorted(Comparator.comparing(Education::getEndDate).reversed())
                .collect(Collectors.toList());
    }

    private List<Employment> sortEmploymentList(List<Employment> employmentList){
        return employmentList
                .stream()
                .sorted(Comparator.comparing(Employment::getEndDate).reversed())
                .collect(Collectors.toList());
    }

}

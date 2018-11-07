package pl.proCvGenerator.patterns;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;
import pl.proCvGenerator.fonts.Fonts;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static pl.proCvGenerator.patterns.PatternHelper.*;

public class SimplePattern implements Pattern {

    private static final Logger logger = LoggerFactory.getLogger(SimplePattern.class);
    private Font timesNewRoman = Fonts.TIMES_12;

    @Override
    public void prepareDocument(Document document) {
        document.setMargins(20, 30, 10, 30);
    }

    @Override
    public void generateHeader(Document document) {
        try {
            Paragraph paragraph = createHeaderParagraph("CV", timesNewRoman);
            document.add(paragraph);
        } catch (DocumentException e) {

        }
    }

    @Override
    public void generatePersonalInfoSection(Document document, PersonalInfo personalInfo) {
        try {
            Paragraph paragraph = new Paragraph("");
            document.add(paragraph);
            paragraph = createHeaderParagraph("Dane osobowe:", timesNewRoman);
            document.add(paragraph);
            document.add(createSeparatorLine());
            paragraph = createNormalParagraph(personalInfo.getName() + " " + personalInfo.getSurname(), timesNewRoman);
            document.add(paragraph);
            paragraph = createNormalParagraph(personalInfo.getCity(), timesNewRoman);
            document.add(paragraph);
            paragraph = createNormalParagraph(personalInfo.getPhone(), timesNewRoman);
            document.add(paragraph);

            if (!personalInfo.getEmail().equals("")) {
                paragraph = createNormalParagraph(personalInfo.getEmail(), timesNewRoman);
                document.add(paragraph);
            }
            if (!personalInfo.getPage().equals("")) {
                paragraph = createNormalParagraph(personalInfo.getPage(), timesNewRoman);
                document.add(paragraph);
            }
        } catch (DocumentException e) {

        }

    }

    @Override
    public void generateDescriptionSection(Document document, String description) {
        try {
            Paragraph paragraph = new Paragraph("");
            document.add(paragraph);
            paragraph = createHeaderParagraph("O mnie:", timesNewRoman);
            document.add(paragraph);
            document.add(createSeparatorLine());
            paragraph = createNormalParagraph(description, timesNewRoman);
            document.add(paragraph);
        } catch (DocumentException e) {

        }

    }

    @Override
    public void generateEducationSection(Document document, List<Education> educationList) {
        try {
            Paragraph paragraph = new Paragraph("");
            document.add(paragraph);
            paragraph = createHeaderParagraph("Edukacja:", timesNewRoman);
            document.add(paragraph);
            document.add(createSeparatorLine());

            for (Education education : sortEducationList(educationList)) {
                paragraph = createNormalParagraph(education.getStartDate() + " - " + education.getEndDate() + " - " + education.getSchoolName()
                        + ", " + education.getSubject() + ", " + education.getDegree(), timesNewRoman);
                document.add(paragraph);
            }
        } catch (DocumentException e) {

        }

    }

    @Override
    public void generateEmploymentSection(Document document, List<Employment> employments) {
        try {
            Paragraph paragraph = new Paragraph("");
            document.add(paragraph);
            paragraph = createHeaderParagraph("Doświadczenie zawodowe:", timesNewRoman);
            document.add(paragraph);
            document.add(createSeparatorLine());

            for (Employment e : sortEmploymentList(employments)) {
                paragraph = createNormalParagraph(e.getStartDate() + " - " + e.getEndDate() + " - " + e.getCompany() + ".", timesNewRoman);
                document.add(paragraph);
                paragraph = createNormalParagraph("Stanowisko: " + e.getPosition() + ", zakres obowiązków: " + e.getJobDescription(), timesNewRoman);
                document.add(paragraph);
            }
        } catch (DocumentException e) {

        }

    }

    @Override
    public void generateSkillsSection(Document document, List<String> skills) {
        try {
            Paragraph paragraph = new Paragraph("");
            document.add(paragraph);
            paragraph = createHeaderParagraph("Umiejętności:", timesNewRoman);
            document.add(paragraph);
            document.add(createSeparatorLine());

            for (String skill : skills) {
                paragraph = createNormalParagraph("- " + skill, timesNewRoman);
                document.add(paragraph);
            }
        } catch (DocumentException e) {

        }

    }

    @Override
    public void generateHobbySection(Document document, List<String> hobbyList) {
        try {
            Paragraph paragraph = new Paragraph("");
            document.add(paragraph);
            paragraph = createHeaderParagraph("Zainteresowania:", timesNewRoman);
            document.add(paragraph);
            document.add(createSeparatorLine());

            for (String hobby : hobbyList) {
                paragraph = createNormalParagraph("- " + hobby, timesNewRoman);
                document.add(paragraph);
            }
        } catch (DocumentException e) {

        }

    }

    @Override
    public void generateClause(Document document, String clause) {
        try {
            Paragraph paragraph = createClauseParagraph(clause, timesNewRoman);
            document.add(paragraph);
        } catch (DocumentException e) {

        }

    }

    private List<Education> sortEducationList(List<Education> educationList) {
        return educationList
                .stream()
                .sorted(Comparator.comparing(Education::getEndDate).reversed())
                .collect(Collectors.toList());
    }

    private List<Employment> sortEmploymentList(List<Employment> employmentList) {
        return employmentList
                .stream()
                .sorted(Comparator.comparing(Employment::getEndDate).reversed())
                .collect(Collectors.toList());
    }

}

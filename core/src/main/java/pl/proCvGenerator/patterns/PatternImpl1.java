package pl.proCvGenerator.patterns;


import com.itextpdf.text.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;

import java.util.List;

import static pl.proCvGenerator.fonts.Fonts.UBUNTU_BOLD;
import static pl.proCvGenerator.fonts.Fonts.UBUNTU_NORMAL;
import static pl.proCvGenerator.patterns.helpers.PatternImpl1Helper.*;

public class PatternImpl1 implements Pattern {

    private static final Logger logger = LoggerFactory.getLogger(PatternImpl1.class);

    @Override
    public Document prepareDocument() {
        Document document = new Document();
        Rectangle pageSize = new Rectangle(PageSize.A4);
        document.setPageSize(pageSize);
        document.setMargins(20, 20, 15, 15);

        return document;
    }

    @Override
    public void generateCv(Document document, CvContent cvContent) {
        generateHeader(document);
        generatePersonalInfoSection(document, cvContent.getPersonalInfo());
        generateDescriptionSection(document, cvContent.getPersonalInfo().getDescription());
        generateEducationSection(document, cvContent.getEducationList());
        generateEmploymentSection(document, cvContent.getEmployments());
        generateSkillsSection(document, cvContent.getSkills());
        generateHobbySection(document, cvContent.getHobbies());
    }

    public void generateHeader(Document document) {
        try {
            Paragraph paragraph = createCvTitle("CV", UBUNTU_BOLD);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
        } catch (DocumentException e) {
        }
    }

    public void generatePersonalInfoSection(Document document, PersonalInfo personalInfo) {
        try {
            Paragraph paragraph = new Paragraph("");
            document.add(paragraph);
            paragraph = createHeaderParagraph("Dane osobowe:", UBUNTU_BOLD);
            document.add(paragraph);
            document.add(createSeparatorLine());
            paragraph = createNormalParagraph(personalInfo.getName() + " " + personalInfo.getSurname(), UBUNTU_NORMAL);
            document.add(paragraph);
            paragraph = createNormalParagraph(personalInfo.getCity(), UBUNTU_NORMAL);
            document.add(paragraph);
            paragraph = createNormalParagraph(personalInfo.getPhone(), UBUNTU_NORMAL);
            document.add(paragraph);

            if (!personalInfo.getEmail().equals("")) {
                paragraph = createNormalParagraph(personalInfo.getEmail(), UBUNTU_NORMAL);
                document.add(paragraph);
            }
            if (!personalInfo.getPage().equals("")) {
                paragraph = createNormalParagraph(personalInfo.getPage(), UBUNTU_NORMAL);
                document.add(paragraph);
            }
        } catch (DocumentException e) {

        }
    }

    public void generateDescriptionSection(Document document, String description) {
        try {
            Paragraph paragraph = new Paragraph("");
            document.add(paragraph);
            paragraph = createHeaderParagraph("O mnie:", UBUNTU_BOLD);
            document.add(paragraph);
            document.add(createSeparatorLine());
            paragraph = createNormalParagraph(description, UBUNTU_NORMAL);
            document.add(paragraph);
        } catch (DocumentException e) {

        }
    }

    public void generateEducationSection(Document document, List<Education> educationList) {
        try {
            Paragraph paragraph = new Paragraph("");
            document.add(paragraph);
            paragraph = createHeaderParagraph("Edukacja:", UBUNTU_BOLD);
            document.add(paragraph);
            document.add(createSeparatorLine());

            for (Education education : sortEducationList(educationList)) {
                paragraph = createNormalParagraph(education.getStartDate() + " - " + education.getEndDate() + " - " + education.getSchoolName()
                        + ", " + education.getSubject() + ", " + education.getDegree(), UBUNTU_NORMAL);
                document.add(paragraph);
            }
        } catch (DocumentException e) {

        }
    }

    public void generateEmploymentSection(Document document, List<Employment> employments) {
        try {
            Paragraph paragraph = new Paragraph("");
            document.add(paragraph);
            paragraph = createHeaderParagraph("Doświadczenie zawodowe:", UBUNTU_BOLD);
            document.add(paragraph);
            document.add(createSeparatorLine());

            for (Employment e : sortEmploymentList(employments)) {
                paragraph = createNormalParagraph(e.getStartDate() + " - " + e.getEndDate() + " - " + e.getCompany() + ".", UBUNTU_NORMAL);
                document.add(paragraph);
                paragraph = createNormalParagraph("Stanowisko: " + e.getPosition() + ", zakres obowiązków: " + e.getJobDescription(), UBUNTU_NORMAL);
                document.add(paragraph);
            }
        } catch (DocumentException e) {

        }
    }

    public void generateSkillsSection(Document document, List<String> skills) {
        try {
            Paragraph paragraph = new Paragraph("");
            document.add(paragraph);
            paragraph = createHeaderParagraph("Umiejętności:", UBUNTU_BOLD);
            document.add(paragraph);
            document.add(createSeparatorLine());

            for (String skill : skills) {
                paragraph = createNormalParagraph("- " + skill, UBUNTU_NORMAL);
                document.add(paragraph);
            }
        } catch (DocumentException e) {

        }
    }

    public void generateHobbySection(Document document, List<String> hobbyList) {
        try {
            Paragraph paragraph = new Paragraph("");
            document.add(paragraph);
            paragraph = createHeaderParagraph("Zainteresowania:", UBUNTU_BOLD);
            document.add(paragraph);
            document.add(createSeparatorLine());

            for (String hobby : hobbyList) {
                paragraph = createNormalParagraph("- " + hobby, UBUNTU_NORMAL);
                document.add(paragraph);
            }
        } catch (DocumentException e) {

        }
    }

    public void generateClause(Document document, String clause) {
        try {
            Paragraph paragraph = new Paragraph("");
            paragraph.setSpacingAfter(30);
            document.add(paragraph);
            document.add(createSeparatorLine());
            paragraph = createClauseParagraph(clause, UBUNTU_NORMAL);
            document.add(paragraph);

        } catch (DocumentException e) {
        }


    }

}

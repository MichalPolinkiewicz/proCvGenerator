package pl.proCvGenerator.patterns;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.proCvGenerator.dao.CvContent;
import pl.proCvGenerator.dao.Education;
import pl.proCvGenerator.dao.Employment;
import pl.proCvGenerator.dao.PersonalInfo;
import pl.proCvGenerator.exception.PdfException;
import pl.proCvGenerator.exception.TooMuchCharsException;
import pl.proCvGenerator.fonts.Fonts;
import pl.proCvGenerator.patterns.helpers.PatternHelper;
import pl.proCvGenerator.validator.TextValidator;

import java.util.Properties;

public class PatternImpl1 implements Pattern {

    @Autowired
    private TextValidator textValidator;
    @Autowired
    @Qualifier("myProperties")
    private Properties properties;

    private static final Logger LOGGER = LoggerFactory.getLogger(PatternImpl1.class);
    private static final String CLASS_NAME = PatternImpl1.class.getSimpleName();
    private final Font normalFont = Fonts.TAHOMA_NORMAL;
    private final Font boldFont = Fonts.TAHOMA_BOLD;
    private static final int PARAGRAPH_SIZE = 14;
    private static final int SECTION_HEADER_SIZE = 18;

    private static int maxLinesForLeft = 26;
    private static int maxLinesForRight = 22;
    private static int maxCharsInLineForSkills = 40;
    private static int maxCharsInLineForEducation = 22;
    private static int maxCharsInLineForRightSection = 33;
    private static int maxCharsForNameAndSurname = 25;

    @Override
    public void validate(CvContent cvContent) throws TooMuchCharsException {
        String methodName = "validate()";

        PersonalInfo personalInfo = cvContent.getPersonalInfo();
        String nameAndSurname = personalInfo.getName() + " " + personalInfo.getSurname();
        if (nameAndSurname.length() > maxCharsForNameAndSurname) {
            maxLinesForLeft = 25;
            maxLinesForRight = 21;
        } else if (nameAndSurname.length() > 49) {
            throw new TooMuchCharsException("too mach chars for name and surname - max is 49");
        }

        if (personalInfo.getPosition().length() > 40) {
            throw new TooMuchCharsException("too mach chars for position - max is 40");
        }

        int linesForEmployment = 0;
        for (Employment e : cvContent.getEmployments()) {
            linesForEmployment += textValidator.calculateLinesForSentence(e.getPosition() + ", " + e.getCompany() + ".", 21);
            int maxCharsInLineForEmployment = 25;
            linesForEmployment += textValidator.calculateLinesForSentence(e.getJobDescription(), maxCharsInLineForEmployment);
        }
        LOGGER.info(CLASS_NAME + " - " + methodName + " - lines for employments = " + linesForEmployment);

        int linesForSkills = 0;
        for (String s : cvContent.getSkills()) {
            linesForSkills += textValidator.calculateLinesForSentence(s, maxCharsInLineForSkills);
        }
        LOGGER.info(CLASS_NAME + " - " + methodName + " - lines for skills = " + linesForSkills);

        int totalLinesLeft = linesForEmployment + linesForSkills;
        LOGGER.info(CLASS_NAME + " - " + methodName + " - TOTAL LINES IN LEFT SECTION = " + totalLinesLeft);

        if (totalLinesLeft > maxLinesForLeft) {
            throw new TooMuchCharsException("too much chars on left side");
        }

        int linesForDescription = textValidator.calculateLinesForSentence(cvContent.getPersonalInfo().getDescription(), maxCharsInLineForRightSection);
        LOGGER.info(CLASS_NAME + " - " + methodName + " - lines for description = " + linesForDescription);

        int linesForContact =
                textValidator.calculateLinesForSentence(personalInfo.getPhone(), maxCharsInLineForRightSection) +
                        textValidator.calculateLinesForSentence(personalInfo.getCity(), maxCharsInLineForRightSection) +
                        textValidator.calculateLinesForSentence(personalInfo.getEmail(), maxCharsInLineForRightSection);
        if (cvContent.getPersonalInfo().getPage() != null) {
            linesForContact += textValidator.calculateLinesForSentence(personalInfo.getPage(), maxCharsInLineForRightSection);
        }
        LOGGER.info(CLASS_NAME + " - " + methodName + " - contact lines = " + linesForContact);

        int linesForEducation = 0;
        for (Education e : cvContent.getEducationList()) {
            linesForEducation += textValidator.calculateLinesForSentence(e.getSchoolName() + ", "
                    + e.getSubject() + ", " + e.getDegree(), maxCharsInLineForEducation);
        }
        LOGGER.info(CLASS_NAME + " - " + methodName + " - lines for educations = " + linesForEducation);

        int linesForHobbies = 0;
        for (String h : cvContent.getHobbies()) {
            linesForHobbies += textValidator.calculateLinesForSentence(h, maxCharsInLineForRightSection);
        }
        LOGGER.info(CLASS_NAME + " - " + methodName + " - lines for hobbies = " + linesForHobbies);

        int totalLinesRight = linesForDescription + linesForContact + linesForEducation + linesForHobbies;
        LOGGER.info(CLASS_NAME + " - " + methodName + " - TOTAL LINES IN RIGHT SECTION = " + totalLinesRight);
        if (totalLinesRight > maxLinesForRight) {
            throw new TooMuchCharsException("too much chars on right side");
        }
    }

    @Override
    public Document prepareDocument() {
        Document document = new Document();
        Rectangle pageSize = new Rectangle(PageSize.A4);
        document.setPageSize(pageSize);
        document.setMargins(25, 25, 15, 15);

        return document;
    }

    @Override
    public void generateCv(Document document, CvContent cvContent) throws PdfException {
        addHeader(document, cvContent.getPersonalInfo());
        createCvBody(document, cvContent);
    }

    public void addHeader(Document document, PersonalInfo personalInfo) throws PdfException {
        Paragraph paragraph = PatternHelper.createSimpleParagraph((personalInfo.getName() + " " +
                personalInfo.getSurname()).toUpperCase(), boldFont, 34);
        try {
            document.add(paragraph);
            paragraph = PatternHelper.createSimpleParagraph(personalInfo.getPosition().toUpperCase(),
                    boldFont, SECTION_HEADER_SIZE);
            boldFont.setColor(new BaseColor(179, 71, 0));
            paragraph.setSpacingAfter(50);
            document.add(paragraph);
        } catch (DocumentException e) {
            String message = CLASS_NAME + " - addHeader() - ERROR: " + e;
            LOGGER.error(message);
            throw new PdfException(message);
        }
    }

    public void createCvBody(Document document, CvContent cvContent) throws PdfException {
        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 1.5f});

            PdfPCell left = createLeftSection(cvContent);
            left.setPaddingRight(40);
            left.setBorder(Rectangle.NO_BORDER);
            table.addCell(left);

            PdfPCell right = createRightSection(cvContent);
            right.setBorder(Rectangle.NO_BORDER);
            table.addCell(right);

            document.add(table);
        } catch (DocumentException e) {
            String message = CLASS_NAME + " - createCvBody() - ERROR: " + e;
            LOGGER.error(message);
            throw new PdfException(message);
        }
    }


    public PdfPCell createLeftSection(CvContent cvContent) throws PdfException {
        PdfPCell mainCell = new PdfPCell();
        try {
            Paragraph paragraph = PatternHelper.createSimpleParagraph("D O Ś W I A D C Z E N I E", boldFont, SECTION_HEADER_SIZE);
            paragraph.setSpacingBefore(10);
            paragraph.setSpacingAfter(5);
            mainCell.addElement(paragraph);
            PdfPTable table = new PdfPTable(2);
            table.setWidths(new float[]{1, 2});
            table.setWidthPercentage(100);

            for (int i = 0; i < cvContent.getEmployments().size(); i++) {
                PdfPCell helperCell = new PdfPCell();
                Employment e = cvContent.getEmployments().get(i);
                paragraph = PatternHelper.createSimpleParagraph(e.getStartDate() + " - " + e.getEndDate(),
                        normalFont, PARAGRAPH_SIZE);
                helperCell.addElement(paragraph);
                helperCell.setBorder(Rectangle.NO_BORDER);
                table.addCell(helperCell);

                helperCell = new PdfPCell();
                paragraph = PatternHelper.createSimpleParagraph(e.getPosition().toUpperCase() + ", "
                        + e.getCompany() + "." + "\n" + e.getJobDescription(), normalFont, PARAGRAPH_SIZE);
                helperCell.addElement(paragraph);
                helperCell.setBorder(Rectangle.NO_BORDER);
                table.addCell(helperCell);
            }
            mainCell.addElement(table);

            paragraph = PatternHelper.createSimpleParagraph("U M I E J Ę T N O Ś C I", boldFont,
                    SECTION_HEADER_SIZE);
            paragraph.setSpacingBefore(10);
            paragraph.setSpacingAfter(5);
            mainCell.addElement(paragraph);

            for (int i = 0; i < cvContent.getSkills().size(); i++) {
                if (i == cvContent.getSkills().size() - 1) {
                    paragraph = PatternHelper.createSimpleParagraph("- " + cvContent.getSkills().get(i) + ".",
                            normalFont, PARAGRAPH_SIZE);
                } else {
                    paragraph = PatternHelper.createSimpleParagraph("- " + cvContent.getSkills().get(i) + ",",
                            normalFont, PARAGRAPH_SIZE);
                }
                mainCell.addElement(paragraph);
            }
        } catch (DocumentException e) {
            String message = CLASS_NAME + " - createLeftSection() - ERROR: " + e;
            LOGGER.error(message);
            throw new PdfException(message);
        }

        return mainCell;
    }

    private PdfPCell createRightSection(CvContent cvContent) throws PdfException {
        PdfPCell cell = new PdfPCell();
        try {
            Paragraph paragraph = PatternHelper.createSimpleParagraph("O  M N I E", boldFont, SECTION_HEADER_SIZE);
            paragraph.setSpacingAfter(5);
            cell.addElement(paragraph);
            paragraph = PatternHelper.createSimpleParagraph(cvContent.getPersonalInfo().getDescription(), normalFont,
                    PARAGRAPH_SIZE);
            cell.addElement(paragraph);

            paragraph = PatternHelper.createSimpleParagraph("K O N T A K T", boldFont, SECTION_HEADER_SIZE);
            paragraph.setSpacingBefore(10);
            paragraph.setSpacingAfter(5);
            cell.addElement(paragraph);
            PersonalInfo personalInfo = cvContent.getPersonalInfo();
            paragraph = PatternHelper.createSimpleParagraph(personalInfo.getPhone(), normalFont, PARAGRAPH_SIZE);
            cell.addElement(paragraph);
            paragraph = PatternHelper.createSimpleParagraph(personalInfo.getEmail(), normalFont, PARAGRAPH_SIZE);
            cell.addElement(paragraph);
            paragraph = PatternHelper.createSimpleParagraph(personalInfo.getCity(), normalFont, PARAGRAPH_SIZE);
            cell.addElement(paragraph);
            if (personalInfo.getPage() != null) {
                paragraph = PatternHelper.createSimpleParagraph(personalInfo.getPage(), normalFont, PARAGRAPH_SIZE);
                cell.addElement(paragraph);
            }

            paragraph = PatternHelper.createSimpleParagraph("W Y K S Z T A Ł C E N I E", boldFont, SECTION_HEADER_SIZE);
            paragraph.setSpacingBefore(10);
            paragraph.setSpacingAfter(5);
            cell.addElement(paragraph);
            PdfPTable table;
            table = new PdfPTable(2);
            table.setWidths(new float[]{1, 2});
            table.setWidthPercentage(100);

            for (int i = 0; i < cvContent.getEducationList().size(); i++) {
                PdfPCell helperCell = new PdfPCell();
                Education e = cvContent.getEducationList().get(i);
                paragraph = PatternHelper.createSimpleParagraph(e.getStartDate() + " - " + e.getEndDate(),
                        normalFont, PARAGRAPH_SIZE);
                helperCell.addElement(paragraph);
                helperCell.setBorder(Rectangle.NO_BORDER);
                table.addCell(helperCell);

                helperCell = new PdfPCell();
                paragraph = PatternHelper.createSimpleParagraph(e.getSchoolName() + ", " + e.getSubject() + ", "
                        + e.getDegree(), normalFont, PARAGRAPH_SIZE);
                helperCell.addElement(paragraph);
                helperCell.setBorder(Rectangle.NO_BORDER);
                table.addCell(helperCell);
            }
            cell.addElement(table);

            paragraph = PatternHelper.createSimpleParagraph("H O B B Y", boldFont, SECTION_HEADER_SIZE);
            paragraph.setSpacingBefore(10);
            paragraph.setSpacingAfter(5);
            cell.addElement(paragraph);

            for (int i = 0; i < cvContent.getHobbies().size(); i++) {
                if (i == cvContent.getSkills().size() - 1) {
                    paragraph = PatternHelper.createSimpleParagraph("- " + cvContent.getHobbies().get(i) + ".",
                            normalFont, PARAGRAPH_SIZE);
                } else {
                    paragraph = PatternHelper.createSimpleParagraph("- " + cvContent.getHobbies().get(i) + ",",
                            normalFont, PARAGRAPH_SIZE);
                }
                cell.addElement(paragraph);
            }
        } catch (DocumentException e) {
            String message = CLASS_NAME + " - createRightSection() - ERROR: " + e;
            LOGGER.error(message);
            throw new PdfException(message);
        }
        return cell;
    }
}

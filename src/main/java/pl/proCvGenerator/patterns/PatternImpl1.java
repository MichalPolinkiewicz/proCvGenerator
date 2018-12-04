package pl.proCvGenerator.patterns;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;
import pl.proCvGenerator.exception.PdfException;
import pl.proCvGenerator.exception.TooMuchCharsException;
import pl.proCvGenerator.fonts.Fonts;
import pl.proCvGenerator.patterns.helpers.PatternHelper;
import pl.proCvGenerator.validator.CharsValidator;

import java.util.List;

public class PatternImpl1 implements Pattern {

    @Autowired
    private CharsValidator charsValidator;

    private static final Logger LOGGER = LoggerFactory.getLogger(PatternImpl1.class);
    private static final String CLASS_NAME = PatternImpl1.class.getSimpleName();
    private final Font normalFont = Fonts.TAHOMA_NORMAL;
    private final Font boldFont = Fonts.TAHOMA_BOLD;
    private static final int PARAGRAPH_SIZE = 14;
    private static final int SECTION_HEADER_SIZE = 18;
    private static final int MAX_LINES_FOR_LEFT = 26;
    private static final int MAX_LINES_FOR_RIGHT = 23;
    private static final int MAX_CHARS_IN_LINE_FOR_EMPLOYMENT = 25;
    private static final int MAX_CHARS_IN_LINE_FOR_SKILLS = 41;
    private static final int MAX_CHARS_IN_LINE_FOR_EDUCATION = 24;
    private static final int MAX_CHARS_IN_LINE_FOR_RIGHT_SECTION = 32;

    @Override
    public void validate(CvContent cvContent) throws TooMuchCharsException {
        int linesForEmployment = 0;
        List<Employment> employmentList = cvContent.getEmployments();
        for (int i = 0; i < employmentList.size(); i++) {
            Employment e = employmentList.get(i);
            linesForEmployment += charsValidator.calculateLinesForSentence(e.getPosition() + ", " + e.getCompany(),
                    21);
            linesForEmployment += charsValidator.calculateLinesForSentence(e.getJobDescription(),
                    MAX_CHARS_IN_LINE_FOR_EMPLOYMENT);
        }
        LOGGER.info("Lines for employments = " + linesForEmployment);

        int linesForSkills = 0;
        for (int i = 0; i < cvContent.getSkills().size(); i++) {
            linesForSkills += charsValidator.calculateLinesForSentence(cvContent.getSkills().get(i),
                    MAX_CHARS_IN_LINE_FOR_SKILLS);
        }
        LOGGER.info("Lines for skills = " + linesForSkills);

        int linesForDescription = charsValidator.calculateLinesForSentence(cvContent.getPersonalInfo().getDescription(),
                MAX_CHARS_IN_LINE_FOR_RIGHT_SECTION);
        LOGGER.info("Lines for description = " + linesForDescription);

        PersonalInfo personalInfo = cvContent.getPersonalInfo();
        int linesForContact =
                charsValidator.calculateLinesForSentence(personalInfo.getPhone(), MAX_CHARS_IN_LINE_FOR_RIGHT_SECTION) +
                        charsValidator.calculateLinesForSentence(personalInfo.getCity(), MAX_CHARS_IN_LINE_FOR_RIGHT_SECTION) +
                        charsValidator.calculateLinesForSentence(personalInfo.getEmail(), MAX_CHARS_IN_LINE_FOR_RIGHT_SECTION);
        if (cvContent.getPersonalInfo().getPage() != null) {
            linesForContact += charsValidator.calculateLinesForSentence(personalInfo.getPage(), MAX_CHARS_IN_LINE_FOR_RIGHT_SECTION);
        }
        LOGGER.info("Contact lines = " + linesForContact);

        int linesForEducation = 0;
        List<Education> educations = cvContent.getEducationList();
        for (int i = 0; i < educations.size(); i++) {
            Education e = educations.get(i);
            linesForEducation += charsValidator.calculateLinesForSentence(e.getSchoolName() + ", "
                    + e.getSubject() + ", " + e.getDegree(), MAX_CHARS_IN_LINE_FOR_EDUCATION);
        }
        LOGGER.info("Lines for educations = " + linesForEducation);

        int linesForHobbies = 0;
        for (int i = 0; i < cvContent.getHobbies().size(); i++) {
            linesForHobbies += charsValidator.calculateLinesForSentence(cvContent.getHobbies().get(i),
                    MAX_CHARS_IN_LINE_FOR_RIGHT_SECTION);
        }
        LOGGER.info("Lines for hobbies = " + linesForHobbies);

        int totalLinesLeft = linesForEmployment + linesForSkills;
        LOGGER.info("TOTAL LINES IN LEFT SECTION = " + totalLinesLeft);
        if (totalLinesLeft > MAX_LINES_FOR_LEFT) {
            throw new TooMuchCharsException("too much chars on left side");
        }
        int totalLinesRight = linesForDescription + linesForContact + linesForEducation + linesForHobbies;
        LOGGER.info("TOTAL LINES IN RIGHT SECTION = " + totalLinesRight);
        if (totalLinesRight > MAX_LINES_FOR_RIGHT) {
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
                personalInfo.getSurname()).toUpperCase(), boldFont, 38);
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
            Paragraph paragraph = PatternHelper.createSimpleParagraph("D O Ś W I A D C Z E N I E", boldFont,
                    SECTION_HEADER_SIZE);
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

            paragraph = PatternHelper.createSimpleParagraph("W Y K S Z T A Ł C E N I E", boldFont,
                    SECTION_HEADER_SIZE);
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

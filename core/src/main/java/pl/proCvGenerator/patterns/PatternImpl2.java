package pl.proCvGenerator.patterns;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;
import pl.proCvGenerator.exception.TooMuchCharsException;
import pl.proCvGenerator.fonts.Fonts;
import pl.proCvGenerator.patterns.helpers.PatternHelper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.itextpdf.text.Element.*;
import static com.itextpdf.text.Rectangle.NO_BORDER;
import static pl.proCvGenerator.patterns.helpers.PatternHelper.*;

public class PatternImpl2 implements Pattern {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatternImpl2.class);
    private static final String CLASS_NAME = PatternImpl2.class.getSimpleName();
    private static final String PATH_TO_IMAGES = "/home/michal/IdeaProjects/proCvGenerator/core/src/main/resources/images/";
    private static final String PATH_TO_IMAGES_WINDOWS = "C:/Users/MPl/IdeaProjects/proCvGenerator/core/src/main/resources/images/";

    private static final int MAX_CHARS_IN_LINE_FOR_STRING = 25;
    private static final int MAX_CHARS_IN_LINE_FOR_STRING_LIST_RIGHT = 55;
    private static final int MAX_CHARS_IN_LINE_FOR_STRING_LIST_LEFT = 20;
    private static final int MAX_CHARS_IN_LINE_FOR_EMPLOYMENT = 55;
    private static final int EXTRA_CHARS_FOR_EMPLOYMENT = 27;
    private static final int MAX_CHARS_IN_LINE_FOR_EDUCATION = 24;
    private static final int EXTRA_CHARS_FOR_EDUCATION = 27;

    @Override
    public Document prepareDocument() {
        Document document = new Document();
        Rectangle pageSize = new Rectangle(PageSize.A4);
        document.setPageSize(pageSize);
        document.setMargins(15, 15, 15, 15);

        return document;
    }

    @Override
    public void generateCv(Document document, CvContent cvContent) throws TooMuchCharsException {
        createCvStructure(document);
        createCvHeaderSection(document, cvContent.getPersonalInfo());
        createCvBody(document, cvContent);
    }

    public void createCvStructure(Document document) {
        try {
            Rectangle rectangle = new Rectangle(0, 0, 205, 842);
            rectangle.setBackgroundColor(new BaseColor(210, 224, 224));
            document.add(rectangle);

            rectangle = new Rectangle(205, 0, 595, 842);
            rectangle.setBackgroundColor(new BaseColor(225, 234, 234));
            document.add(rectangle);

            rectangle = new Rectangle(0, 660, 595, 842);
            rectangle.setBackgroundColor(new BaseColor(0, 40, 77));
            document.add(rectangle);

        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + " - createCvStructure() - ERROR: " + e);
        }
    }

    public void createCvHeaderSection(Document document, PersonalInfo personalInfo) {
        try {
            Paragraph p = createParagraphForHeaderTable((personalInfo.getName() + " " + personalInfo.getSurname()).toUpperCase(), Fonts.CALIBRI_NORMAL, 32);
            p.setAlignment(ALIGN_JUSTIFIED_ALL);
            p.setIndentationLeft(100);
            p.setIndentationRight(100);
            document.add(p);

            PdfPTable table = createHeaderTable(personalInfo);
            Image image = Image.getInstance(PATH_TO_IMAGES + "whitephone.png");
            PdfPCell cell = matchImageToHeaderTable(image, 34, 34);
            table.addCell(cell);
            cell = matchParagraphToHeaderTable(createParagraphForHeaderTable(personalInfo.getPhone(), Fonts.CALIBRI_NORMAL, 14));
            table.addCell(cell);

            image = Image.getInstance(PATH_TO_IMAGES + "whitemessage.png");
            cell = matchImageToHeaderTable(image, 46, 46);
            table.addCell(cell);
            cell = matchParagraphToHeaderTable(createParagraphForHeaderTable(personalInfo.getEmail(), Fonts.CALIBRI_NORMAL, 14));
            table.addCell(cell);

            image = Image.getInstance(PATH_TO_IMAGES + "whitehouse.png");
            cell = matchImageToHeaderTable(image, 32, 32);
            table.addCell(cell);
            cell = matchParagraphToHeaderTable(createParagraphForHeaderTable(personalInfo.getCity(), Fonts.CALIBRI_NORMAL, 14));
            table.addCell(cell);

            document.add(table);

        } catch (DocumentException | IOException e) {
            LOGGER.error(CLASS_NAME + " - createCvHeaderSection() - " + "ERROR: " + e);
        }
    }

    public void createCvBody(Document document, CvContent cvContent) throws TooMuchCharsException {
        String methodName = "createCvBody";
        BigDecimal maxLinesForPage = BigDecimal.valueOf(25.0);
        String description = cvContent.getPersonalInfo().getDescription();
        List<Education> educationList = PatternHelper.sortEducationList(cvContent.getEducationList());
        List<String> hobbies = cvContent.getHobbies();
        List<Employment> employments = PatternHelper.sortEmploymentList(cvContent.getEmployments());
        List<String> skills = cvContent.getSkills();

        // LEFT COLUMN VALIDATION
        if (validateString(description, MAX_CHARS_IN_LINE_FOR_STRING).add(validateEducationsList(educationList, MAX_CHARS_IN_LINE_FOR_EDUCATION, EXTRA_CHARS_FOR_EDUCATION)).add(validateStringList(hobbies, MAX_CHARS_IN_LINE_FOR_STRING_LIST_LEFT)).compareTo(maxLinesForPage) > 0) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: too much chars in LEFT SIDE");
            throw new TooMuchCharsException("Too much chars in left section");
        }

        //RIGHT COLUMN VALIDATION
        if (validateStringList(skills, MAX_CHARS_IN_LINE_FOR_STRING_LIST_RIGHT).add(validateEmployments(employments, MAX_CHARS_IN_LINE_FOR_EMPLOYMENT, EXTRA_CHARS_FOR_EMPLOYMENT)).compareTo(maxLinesForPage) > 0) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: too much chars in RIGHT SIDE");
            throw new TooMuchCharsException("Too much chars in right section");
        }

        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 2});
            table.setSpacingBefore(30);
            PdfPCell leftCell = createLeftSection(cvContent);
            PdfPCell rightCell = createRightSection(cvContent);
            table.addCell(leftCell);
            table.addCell(rightCell);

            document.add(table);
        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: " + e);
        }
    }

    public PdfPCell createLeftSection(CvContent cvContent) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(NO_BORDER);
        cell.addElement(createHeaderParagrphForSection("o  m n i e"));
        cell.addElement(createSeparator());
        cell.addElement(createSimpleParagraphForSection(cvContent.getPersonalInfo().getDescription()));
        cell.addElement(new Paragraph(" "));

        cell.addElement(createHeaderParagrphForSection("w y k s z t a ł c e n i e"));
        cell.addElement(createSeparator());
        List<Education> educations = PatternHelper.sortEducationList(cvContent.getEducationList());
        for (int i = 0; i < educations.size(); i++) {
            Education e = educations.get(i);
            if (i == educations.size() - 1) {
                cell.addElement(createSimpleParagraphForSection(("- " + e.getSchoolName() + ", " + e.getStartDate() + " - " + e.getEndDate() + ", kierunek: " + e.getSubject() + ", " + e.getDegree() + ".")));
            } else {
                cell.addElement(createSimpleParagraphForSection(("- " + e.getSchoolName() + ", " + e.getStartDate() + " - " + e.getEndDate() + ", kierunek: " + e.getSubject() + ", " + e.getDegree() + ".")));
            }
        }
        cell.addElement(new Paragraph(" "));
        cell.addElement(createHeaderParagrphForSection("h o b b y"));
        cell.addElement(createSeparator());
        List<String> hobbies = cvContent.getHobbies();
        addElementFromStringList(cell, hobbies);
        cell.addElement(new Paragraph(" "));

        return cell;
    }

    public PdfPCell createRightSection(CvContent cvContent) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(NO_BORDER);
        cell.setPaddingLeft(15);
        cell.addElement(createHeaderParagrphForSection("d o ś w i a d c z e n i e  z a w o d o w e"));
        cell.addElement(createSeparator());

        List<Employment> employments = PatternHelper.sortEmploymentList(cvContent.getEmployments());
        for (int i = 0; i < employments.size(); i++) {
            Employment e = employments.get(i);
            cell.addElement(createSimpleParagraphForSection(e.getPosition().toUpperCase()
                    + ", " + e.getCompany() + ", " + e.getStartDate() + " - " + e.getEndDate() + "."));
            cell.addElement(createSimpleParagraphForSection("Zakres obowiązków: " + e.getJobDescription() + "."));
            cell.addElement(new Paragraph(" "));
        }

        cell.addElement(createHeaderParagrphForSection("u m i e j ę t n o ś c i"));
        cell.addElement(createSeparator());
        List<String> skills = cvContent.getSkills();
        addElementFromStringList(cell, skills);

        return cell;
    }

    //SECTION HELPERS
    private void addElementFromStringList(PdfPCell cell, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                cell.addElement(createSimpleParagraphForSection("- " + list.get(i) + "."));
            } else {
                cell.addElement(createSimpleParagraphForSection("- " + list.get(i) + ","));
            }
        }
    }

    private Paragraph createHeaderParagrphForSection(String text) {
        Font font = Fonts.CALIBRI_NORMAL;
        font.setColor(BaseColor.BLACK);
        font.setSize(16);
        Paragraph p = new Paragraph(text.toUpperCase(), font);

        return p;
    }

    private Paragraph createSimpleParagraphForSection(String text) {
        Font font = Fonts.CALIBRI_NORMAL;
        font.setColor(BaseColor.BLACK);
        font.setSize(14);
        Paragraph p = new Paragraph(text, font);

        return p;
    }

    private LineSeparator createSeparator() {
        LineSeparator lineSeparator = new LineSeparator(2, 96, BaseColor.BLACK, 0, -5);

        return lineSeparator;
    }

    // HEADER TABLE HELPERS
    private PdfPTable createHeaderTable(PersonalInfo personalInfo) throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(30);

        float size = 2.5f;
        if (personalInfo.getEmail().length() > 22) {
            size = 3.5f;
        }
        table.setWidths(new float[]{1, 2, 1, size, 1, 2});

        return table;
    }

    private Paragraph createParagraphForHeaderTable(String text, Font font, int fontSize) {
        font.setColor(BaseColor.WHITE);
        Paragraph p = PatternHelper.createSimpleParagraph(text, font, fontSize);
        p.setSpacingBefore(10);
        p.setSpacingAfter(10);

        return p;
    }

    private PdfPCell matchImageToHeaderTable(Image image, float width, float height) {
        image.scaleAbsolute(width, height);
        PdfPCell cell = new PdfPCell(image);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        cell.setVerticalAlignment(ALIGN_MIDDLE);
        cell.setBorder(NO_BORDER);

        return cell;
    }

    private PdfPCell matchParagraphToHeaderTable(Paragraph p) {
        PdfPCell cell = new PdfPCell(p);
        cell.setHorizontalAlignment(ALIGN_LEFT);
        cell.setVerticalAlignment(ALIGN_MIDDLE);
        cell.setBorder(NO_BORDER);

        return cell;
    }
}
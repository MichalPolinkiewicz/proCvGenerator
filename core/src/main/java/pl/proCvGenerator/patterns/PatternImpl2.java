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
import pl.proCvGenerator.fonts.Fonts;
import pl.proCvGenerator.patterns.helpers.PatternHelper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.itextpdf.text.Element.*;
import static com.itextpdf.text.Rectangle.NO_BORDER;
import static java.math.RoundingMode.CEILING;

public class PatternImpl2 implements Pattern {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatternImpl2.class);
    private static final String CLASS_NAME = PatternImpl2.class.getSimpleName();
    private static final String PATH_TO_IMAGES = "/home/michal/IdeaProjects/proCvGenerator/core/src/main/resources/images/";
    private static final int RIGHT = 55;

    @Override
    public Document prepareDocument() {
        Document document = new Document();
        Rectangle pageSize = new Rectangle(PageSize.A4);
        document.setPageSize(pageSize);
        document.setMargins(15, 15, 15, 15);

        return document;
    }

    @Override
    public void generateCv(Document document, CvContent cvContent) {
        createLayout(document);
        createGeneralInfoSection(document, cvContent.getPersonalInfo());
        createBody(document, cvContent);
    }

    public void createLayout(Document document) {
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
            LOGGER.error(CLASS_NAME + " - createLayout() - ERROR: " + e);
        }
    }

    public void createGeneralInfoSection(Document document, PersonalInfo personalInfo) {
        String methodName = "createGeneralInfoSection()";
        try {
            Paragraph p = createParagraphForHeaderTable((personalInfo.getName() + " " + personalInfo.getSurname()).toUpperCase(), Fonts.CALIBRI_NORMAL, 32);
            p.setAlignment(ALIGN_JUSTIFIED_ALL);
            p.setIndentationLeft(100);
            p.setIndentationRight(100);
            document.add(p);

            PdfPTable table = createHeaderTable(personalInfo);
            Image image = Image.getInstance(PATH_TO_IMAGES + "whitephone.png");
            //Image image = Image.getInstance("C:/Users/MPl/IdeaProjects/proCvGenerator/core/src/main/resources/images/whitephone.png");

            PdfPCell cell = matchImageToHeaderTable(image, 34, 34);
            table.addCell(cell);
            cell = matchParagraphToHeaderTable(createParagraphForHeaderTable(personalInfo.getPhone(), Fonts.CALIBRI_NORMAL, 14));
            table.addCell(cell);

            image = Image.getInstance(PATH_TO_IMAGES + "whitemessage.png");
            //image = Image.getInstance("C:/Users/MPl/IdeaProjects/proCvGenerator/core/src/main/resources/images/whitemessage.png");
            cell = matchImageToHeaderTable(image, 46, 46);
            table.addCell(cell);
            cell = matchParagraphToHeaderTable(createParagraphForHeaderTable(personalInfo.getEmail(), Fonts.CALIBRI_NORMAL, 14));
            table.addCell(cell);

            image = Image.getInstance(PATH_TO_IMAGES + "whitehouse.png");
            //image = Image.getInstance("C:/Users/MPl/IdeaProjects/proCvGenerator/core/src/main/resources/images/whitehouse.png");
            cell = matchImageToHeaderTable(image, 32, 32);
            table.addCell(cell);
            cell = matchParagraphToHeaderTable(createParagraphForHeaderTable(personalInfo.getCity(), Fonts.CALIBRI_NORMAL, 14));
            table.addCell(cell);

            document.add(table);

        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: " + e);
        } catch (IOException e) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: " + e);
        }
    }

    public void createBody(Document document, CvContent cvContent) {
        String methodName = "createBody";
        BigDecimal maxLinesForPage = BigDecimal.valueOf(25);

        // LEFT
        String description = cvContent.getPersonalInfo().getDescription();
        List<Education> educationList = cvContent.getEducationList();
        List<String> hobbies = cvContent.getHobbies();

        //RIGHT
        List<Employment> employments = cvContent.getEmployments();
        List<String> skills = cvContent.getSkills();

        System.out.println("LEFT: " + validateString(description).add(validateEducationsList(educationList)).add(validateStringList(hobbies, 20)));
        System.out.println("RIGHT:" + validateEmployments(employments).add(validateStringList(skills, 55)));

        if (maxLinesForPage.compareTo(
                validateString(description)
                        .add(validateEducationsList(educationList))
                        .add(validateStringList(hobbies, 20))) < 0) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: too much chars in LEFT SIDE");
        }

        if (maxLinesForPage.compareTo(
                validateEmployments(employments).add(validateStringList(skills, 55))) < 0) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: too much chars in RIGHT SIDE");
        }

        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 2});
            table.setSpacingBefore(30);
            PdfPCell leftCell = createLeftColumn(cvContent);
            PdfPCell rightCell = createRightColumn(cvContent);
            table.addCell(leftCell);
            table.addCell(rightCell);

            document.add(table);
        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: " + e);
        }
    }

    public Paragraph createCvTitle(String text, Font font, int size) {
        font.setColor(BaseColor.WHITE);
        Paragraph p = PatternHelper.createSimpleParagraph(text, font, size);

        return p;
    }

    public PdfPCell createLeftColumn(CvContent cvContent) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(NO_BORDER);
        cell.addElement(createHeaderForMainTable("o  m n i e"));
        cell.addElement(createSeparator());
        cell.addElement(createParagraph(cvContent.getPersonalInfo().getDescription()));
        cell.addElement(new Paragraph(" "));

        cell.addElement(createHeaderForMainTable("w y k s z t a ł c e n i e"));
        cell.addElement(createSeparator());
        List<Education> educations = PatternHelper.sortEducationList(cvContent.getEducationList());
        for (int i = 0; i < educations.size(); i++) {
            Education e = educations.get(i);
            if (i == educations.size() - 1) {
                cell.addElement(createParagraph(("- " + e.getSchoolName() + ", " + e.getStartDate() + " - " + e.getEndDate() + ", kierunek: " + e.getSubject() + ", " + e.getDegree() + ".")));
            } else {
                cell.addElement(createParagraph(("- " + e.getSchoolName() + ", " + e.getStartDate() + " - " + e.getEndDate() + ", kierunek: " + e.getSubject() + ", " + e.getDegree() + ".")));
            }
        }
        cell.addElement(new Paragraph(" "));
        cell.addElement(createHeaderForMainTable("h o b b y"));
        cell.addElement(createSeparator());
        List<String> hobbies = cvContent.getHobbies();
        addListElement(cell, hobbies);
        cell.addElement(new Paragraph(" "));

        return cell;
    }

    public PdfPCell createRightColumn(CvContent cvContent) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(NO_BORDER);
        cell.setPaddingLeft(15);
        cell.addElement(createHeaderForMainTable("d o ś w i a d c z e n i e  z a w o d o w e"));
        cell.addElement(createSeparator());

        List<Employment> employments = PatternHelper.sortEmploymentList(cvContent.getEmployments());
        for (int i = 0; i < employments.size(); i++) {
            Employment e = employments.get(i);
            cell.addElement(createParagraph(e.getPosition().toUpperCase()
                    + ", " + e.getCompany() + ", " + e.getStartDate() + " - " + e.getEndDate() + "."));
            cell.addElement(createParagraph("Zakres obowiązków: " + e.getJobDescription() + "."));
            cell.addElement(new Paragraph(" "));
        }

        cell.addElement(createHeaderForMainTable("u m i e j ę t n o ś c i"));
        cell.addElement(createSeparator());
        List<String> skills = cvContent.getSkills();
        addListElement(cell, skills);

        return cell;
    }

    private void addListElement(PdfPCell cell, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                cell.addElement(createParagraph("- " + list.get(i) + "."));
            } else {
                cell.addElement(createParagraph("- " + list.get(i) + ","));
            }
        }
    }

    public Paragraph createHeaderForMainTable(String text) {
        Font font = Fonts.CALIBRI_NORMAL;
        font.setColor(BaseColor.BLACK);
        font.setSize(16);
        Paragraph p = new Paragraph(text.toUpperCase(), font);

        return p;
    }

    public Paragraph createParagraph(String text) {
        Font font = Fonts.CALIBRI_NORMAL;
        font.setColor(BaseColor.BLACK);
        font.setSize(14);
        Paragraph p = new Paragraph(text, font);

        return p;
    }

    public LineSeparator createSeparator() {
        LineSeparator lineSeparator = new LineSeparator(2, 96, BaseColor.BLACK, 0, -5);

        return lineSeparator;
    }


    // HEADER TABLE SECTION
    public PdfPTable createHeaderTable(PersonalInfo personalInfo) throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(30);
        int chars = personalInfo.getPhone().length() + personalInfo.getEmail().length() + personalInfo.getCity().length();

        personalInfo.getEmail().length();

        float size = 2.5f;
        if (personalInfo.getEmail().length() > 22){
            size = 3.5f;
        }
        table.setWidths(
                new float[]{
                        1,2,1,size,1,2});


        return table;
    }

    public Paragraph createParagraphForHeaderTable(String text, Font font, int fontSize) {
        font.setColor(BaseColor.WHITE);
        Paragraph p = PatternHelper.createSimpleParagraph(text, font, fontSize);
        p.setSpacingBefore(10);
        p.setSpacingAfter(10);

        return p;
    }

    public PdfPCell matchImageToHeaderTable(Image image, float width, float height) {
        image.scaleAbsolute(width, height);
        PdfPCell cell = new PdfPCell(image);
        cell.setHorizontalAlignment(ALIGN_CENTER);
        cell.setVerticalAlignment(ALIGN_MIDDLE);
        cell.setBorder(NO_BORDER);

        return cell;
    }

    public PdfPCell matchParagraphToHeaderTable(Paragraph p) {
        PdfPCell cell = new PdfPCell(p);
        cell.setHorizontalAlignment(ALIGN_LEFT);
        cell.setVerticalAlignment(ALIGN_MIDDLE);
        cell.setBorder(NO_BORDER);

        return cell;
    }

    public BigDecimal validateStringList(List<String> list, int numberOfChars) {
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

    public BigDecimal validateEducationsList(List<Education> list) {
        double totalLines = 0;

        for (int i = 0; i < list.size(); i++) {
            Education e = list.get(i);
            totalLines += 23 + e.getSchoolName().length() + e.getStartDate().length() + e.getEndDate().length() + e.getSubject().length()
                    + e.getDegree().length();
        }
        BigDecimal decimal = BigDecimal.valueOf(totalLines);

        return decimal.divide(BigDecimal.valueOf(24), CEILING);
    }

    public BigDecimal validateEmployments(List<Employment> list) {
        double totalLines = 0;
        for (int i = 0; i < list.size(); i++) {
            Employment e = list.get(i);
            totalLines += 27 + e.getPosition().length() + e.getCompany().length() + e.getStartDate().length()
                    + e.getEndDate().length() + e.getJobDescription().length();
        }
        BigDecimal decimal = BigDecimal.valueOf(totalLines);
        return decimal.divide(BigDecimal.valueOf(55), CEILING);
    }

    public BigDecimal validateString(String s) {
        BigDecimal a = new BigDecimal(s.length()).setScale(1, CEILING);

        return a.divide(BigDecimal.valueOf(25), CEILING);
    }
}

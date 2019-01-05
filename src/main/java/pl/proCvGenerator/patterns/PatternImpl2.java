package pl.proCvGenerator.patterns;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
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

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static com.itextpdf.text.Element.*;
import static com.itextpdf.text.Rectangle.NO_BORDER;
import static pl.proCvGenerator.patterns.helpers.PatternHelper.createSimpleParagraph;

public class PatternImpl2 implements Pattern {

    @Autowired
    private TextValidator textValidator;
    @Autowired
    @Qualifier("messages")
    private Properties properties;

    private static final Logger LOGGER = LoggerFactory.getLogger(PatternImpl2.class);
    private static final String CLASS_NAME = PatternImpl2.class.getSimpleName();
    private static final String PATH_TO_IMAGES = "/home/michal/IdeaProjects/proCvGenerator/src/main/resources/images/";
    private static final String PATH_TO_IMAGES_WINDOWS = "C:/Users/MPl/IdeaProjects/proCvGenerator/src/main/resources/images/";

    private Font normalFont = Fonts.CALIBRI_NORMAL;
    private Font boldFont = Fonts.CALIBRI_BOLD;
    private static final int HEADER_SIZE = 20;
    private static final int PARAGRAPH_SIZE = 14;
    private static final int MAIN_TABLE_PARAGRAPH_SIZE = 11;

    private static final int MAX_CHARS_IN_LINE_LEFT = 28;
    private static final int MAX_CHARS_IN_LINE_RIGHT = 56;
    private static final int MAX_LINES = 24;

    private static final int EMAIL_MAX_CHARS = 34;
    private static final int PHONE_MAX_CHARS = 18;
    private static final int CITY_MAX_CHARS = 22;
    private static final int NAME_SURNAME_MAX_CHARS = 24;
    private static final int POSITION_MAX_CHARS = 30;


    @Override
    public void validate(CvContent cvContent) throws TooMuchCharsException {
        String methodName = "validate()";
        PersonalInfo personalInfo = cvContent.getPersonalInfo();

        if ((personalInfo.getName() + " " + personalInfo.getSurname()).length() > NAME_SURNAME_MAX_CHARS) {
            throw new TooMuchCharsException(methodName + " - too much max chars for name and surname, max is: " + (NAME_SURNAME_MAX_CHARS - 1));
        }
        if (personalInfo.getPosition().length() > POSITION_MAX_CHARS) {
            throw new TooMuchCharsException(methodName + " - too much max chars for position, max is: " + PHONE_MAX_CHARS);
        }
        if (personalInfo.getPhone().length() > PHONE_MAX_CHARS) {
            throw new TooMuchCharsException(methodName + " - too much max chars for phone, max is: " + PHONE_MAX_CHARS);
        }
        if (personalInfo.getEmail().length() > EMAIL_MAX_CHARS) {
            throw new TooMuchCharsException(methodName + " - too much max chars for email, max is: " + EMAIL_MAX_CHARS);
        }
        if (personalInfo.getCity().length() > CITY_MAX_CHARS) {
            throw new TooMuchCharsException(methodName + " - too much max chars for city, max is: " + CITY_MAX_CHARS);
        }

        int personalInfoLines = textValidator.calculateLinesForSentence(personalInfo.getDescription(), MAX_CHARS_IN_LINE_LEFT);
        LOGGER.info("Personal info lines = " + personalInfoLines);

        int educationLines = 0;
        for (Education e : cvContent.getEducationList()) {
            educationLines += textValidator.calculateLinesForSentence(e.getSchoolName() + e.getStartDate()
                    + e.getEndDate() + ", kierunek: " + e.getSubject() + ", " + e.getDegree(), MAX_CHARS_IN_LINE_LEFT);
        }
        LOGGER.info("Education lines = " + educationLines);

        int hobbiesLines = 0;
        for (String s : cvContent.getHobbies()) {
            hobbiesLines += textValidator.calculateLinesForSentence(s, MAX_CHARS_IN_LINE_LEFT);
        }
        LOGGER.info("Hobbies lines = " + hobbiesLines);

        int totalLinesLeft = personalInfoLines + educationLines + hobbiesLines;
        LOGGER.info("TOTAL LINES IN LEFT SECTION = " + totalLinesLeft);
        if (totalLinesLeft > MAX_LINES) {
            throw new TooMuchCharsException("too much chars inf left section");
        }

        int employmentsLines = 0;
        for (Employment e : cvContent.getEmployments()) {
            employmentsLines += textValidator.calculateLinesForSentence(e.getPosition() + ", " + e.getCompany()
                    + ", " + e.getStartDate() + " - " + e.getEndDate(), MAX_CHARS_IN_LINE_RIGHT);
            employmentsLines += textValidator.calculateLinesForSentence("Zakres obowiązków: " + e.getJobDescription(), MAX_CHARS_IN_LINE_RIGHT);
        }
        LOGGER.info("Employments lines = " + employmentsLines);

        int skillsLines = 0;
        for (String s : cvContent.getSkills()) {
            skillsLines += textValidator.calculateLinesForSentence("- " + s + ",", MAX_CHARS_IN_LINE_RIGHT);
        }
        LOGGER.info("Skills lines = " + skillsLines);
        int totalLinesRight = employmentsLines + skillsLines;
        LOGGER.info("TOTAL LINES IN RIGHT SECTION = " + totalLinesRight);
        if (totalLinesRight > MAX_LINES) {
            throw new TooMuchCharsException("too much chars inf right section");
        }
    }

    @Override
    public Document prepareDocument() {
        Document document = new Document();
        Rectangle pageSize = new Rectangle(PageSize.A4);
        document.setPageSize(pageSize);
        document.setMargins(15, 15, 15, 15);

        return document;
    }

    @Override
    public void generateCv(Document document, CvContent cvContent) throws PdfException {
        createCvStructure(document);
        createCvHeaderSection(document, cvContent.getPersonalInfo());
        createCvBody(document, cvContent);
    }

    public void createCvStructure(Document document) throws PdfException {
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
            String message = CLASS_NAME + " - createCvStructure - " + "ERROR: " + e;
            LOGGER.error(message);
            throw new PdfException(message);
        }
    }

    public void createCvHeaderSection(Document document, PersonalInfo personalInfo) throws PdfException {
        try {
            boldFont.setColor(BaseColor.WHITE);
            normalFont.setColor(BaseColor.WHITE);

            Paragraph p = createSimpleParagraph((personalInfo.getName() + " " + personalInfo.getSurname()).toUpperCase(), normalFont, 34);
            p.setAlignment(ALIGN_CENTER);
            document.add(p);
            p = createSimpleParagraph(personalInfo.getPosition().toUpperCase(), normalFont, 24);
            p.setAlignment(ALIGN_CENTER);
            document.add(p);

            PdfPTable table = createHeaderTable(personalInfo);

            Image image = Image.getInstance(PATH_TO_IMAGES_WINDOWS + "whitephone.png");
            PdfPCell cell = matchImageToHeaderTable(image, 34, 34);
            table.addCell(cell);
            cell = matchParagraphToHeaderTable(createSimpleParagraph(personalInfo.getPhone(), normalFont, MAIN_TABLE_PARAGRAPH_SIZE));
            table.addCell(cell);

            image = Image.getInstance(PATH_TO_IMAGES_WINDOWS + "whitemessage.png");
            cell = matchImageToHeaderTable(image, 46, 46);
            table.addCell(cell);
            cell = matchParagraphToHeaderTable(createSimpleParagraph(personalInfo.getEmail(), normalFont, MAIN_TABLE_PARAGRAPH_SIZE));
            table.addCell(cell);

            image = Image.getInstance(PATH_TO_IMAGES_WINDOWS + "whitehouse.png");
            cell = matchImageToHeaderTable(image, 32, 32);
            table.addCell(cell);
            cell = matchParagraphToHeaderTable(createSimpleParagraph(personalInfo.getCity(), normalFont, MAIN_TABLE_PARAGRAPH_SIZE));
            table.addCell(cell);
            document.add(table);
        } catch (DocumentException | IOException e) {
            String message = CLASS_NAME + " - createCvHeaderSection - " + "ERROR: " + e;
            LOGGER.error(message);
            throw new PdfException(message);
        }
    }

    public void createCvBody(Document document, CvContent cvContent) throws PdfException {
        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 2});
            table.setSpacingBefore(20);
            PdfPCell leftCell = createLeftSection(cvContent);
            PdfPCell rightCell = createRightSection(cvContent);
            table.addCell(leftCell);
            table.addCell(rightCell);
            document.add(table);
        } catch (DocumentException e) {
            String message = CLASS_NAME + " - createCvBody - " + "ERROR: " + e;
            LOGGER.error(message);
            throw new PdfException(message);
        }
    }

    public PdfPCell createLeftSection(CvContent cvContent) {
        normalFont.setColor(BaseColor.BLACK);
        boldFont.setColor(BaseColor.BLACK);
        PdfPCell cell = new PdfPCell();
        cell.setBorder(NO_BORDER);
        cell.addElement(createSimpleParagraph("o  m n i e".toUpperCase(), boldFont, HEADER_SIZE));
        cell.addElement(createSeparator());
        cell.addElement(createSimpleParagraph(cvContent.getPersonalInfo().getDescription(), normalFont, PARAGRAPH_SIZE));
        cell.addElement(new Paragraph(" "));

        cell.addElement(createSimpleParagraph("e d u k a c j a".toUpperCase(), boldFont, HEADER_SIZE));
        cell.addElement(createSeparator());
        List<Education> educations = PatternHelper.sortEducationList(cvContent.getEducationList());
        for (int i = 0; i < educations.size(); i++) {
            Education e = educations.get(i);
            if (i == educations.size() - 1) {
                cell.addElement(createSimpleParagraph(("- " + e.getSchoolName() + ", " + e.getStartDate() + " - " + e.getEndDate() + ", kierunek: " + e.getSubject() + ", " + e.getDegree() + "."), normalFont, PARAGRAPH_SIZE));
            } else {
                cell.addElement(createSimpleParagraph(("- " + e.getSchoolName() + ", " + e.getStartDate() + " - " + e.getEndDate() + ", kierunek: " + e.getSubject() + ", " + e.getDegree() + "."), normalFont, PARAGRAPH_SIZE));
            }
        }
        cell.addElement(new Paragraph(" "));
        cell.addElement(createSimpleParagraph("h o b b y".toUpperCase(), boldFont, HEADER_SIZE));
        cell.addElement(createSeparator());

        List<String> hobbies = cvContent.getHobbies();
        for (int i = 0; i < hobbies.size(); i++) {
            if (i == hobbies.size() - 1) {
                cell.addElement(createSimpleParagraph("- " + cvContent.getHobbies().get(i) + ".", normalFont, PARAGRAPH_SIZE));
            } else {
                cell.addElement(createSimpleParagraph("- " + cvContent.getHobbies().get(i) + ".", normalFont, PARAGRAPH_SIZE));
            }
        }
        cell.addElement(new Paragraph(" "));

        return cell;
    }

    public PdfPCell createRightSection(CvContent cvContent) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(NO_BORDER);
        cell.setPaddingLeft(15);
        cell.addElement(createSimpleParagraph("d o ś w i a d c z e n i e  z a w o d o w e".toUpperCase(), boldFont, HEADER_SIZE));
        cell.addElement(createSeparator());

        List<Employment> employments = PatternHelper.sortEmploymentList(cvContent.getEmployments());
        for (int i = 0; i < employments.size(); i++) {
            Employment e = employments.get(i);
            cell.addElement(createSimpleParagraph(e.getPosition().toUpperCase()
                    + ", " + e.getCompany() + ", " + e.getStartDate() + " - " + e.getEndDate() + ".", normalFont, PARAGRAPH_SIZE));
            cell.addElement(createSimpleParagraph("Zakres obowiązków: " + e.getJobDescription() + ".", normalFont, PARAGRAPH_SIZE));
            cell.addElement(new Paragraph(" "));
        }

        cell.addElement(createSimpleParagraph("u m i e j ę t n o ś c i".toUpperCase(), boldFont, HEADER_SIZE));
        cell.addElement(createSeparator());
        List<String> skills = cvContent.getSkills();

        for (int i = 0; i < skills.size(); i++) {
            if (i == skills.size() - 1) {
                cell.addElement(createSimpleParagraph("- " + skills.get(i) + ".", normalFont, PARAGRAPH_SIZE));
            } else {
                cell.addElement(createSimpleParagraph("- " + skills.get(i) + ",", normalFont, PARAGRAPH_SIZE));
            }
        }
        return cell;
    }

    private LineSeparator createSeparator() {
        LineSeparator lineSeparator = new LineSeparator(2, 96, BaseColor.BLACK, 0, -5);

        return lineSeparator;
    }

    private PdfPTable createHeaderTable(PersonalInfo personalInfo) throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);

        float emailSize = 1.5f;
        if (personalInfo.getEmail().length() > 21) {
            emailSize = 2.5f;
        }

        table.setWidths(new float[]{0.8f, 1.5f, 0.8f, emailSize, 0.8f, 1.5f});

        return table;
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
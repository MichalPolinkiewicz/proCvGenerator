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

import java.util.List;
import java.util.Properties;

import static pl.proCvGenerator.patterns.helpers.PatternHelper.createSimpleParagraph;


public class PatternImpl3 implements Pattern {

    @Autowired
    private TextValidator textValidator;
    @Autowired
    @Qualifier("messages")
    private Properties properties;

    public static final Logger LOGGER = LoggerFactory.getLogger(PatternImpl3.class);
    private static final String CLASS_NAME = PatternImpl3.class.getSimpleName();
    private Font normalFont = Fonts.ANTONIO_NORMAL;
    private Font boldFont = Fonts.ANTONIO_BOLD;
    private static final int PARAGRAPH_HEADER_SIZE = 14;
    private static final int PARAGRAPH_SIZE = 12;
    private static final int SECTION_HEADER_SIZE = 18;

    private static int maxLinesLeft = 25;
    private static int maxLinesRight = 29;
    private static int maxCharsInLineLeft = 35;
    private static int maxCharsInLineRight = 78;

    @Override
    public void validate(CvContent cvContent) throws TooMuchCharsException {
        PersonalInfo personalInfo = cvContent.getPersonalInfo();

        if ((personalInfo.getName() + " " + personalInfo.getSurname()).length() > 25 && (personalInfo.getName() + " " + personalInfo.getSurname()).length() < 48) {
            maxLinesLeft = 22;
            maxLinesRight = 25;
        } else if ((personalInfo.getName() + " " + personalInfo.getSurname()).length() > 48) {
            throw new TooMuchCharsException(CLASS_NAME + " validate() - Too mach chars for name and surname, max is 47");
        }
        if (personalInfo.getPosition().length() > 25) {
            throw new TooMuchCharsException(CLASS_NAME + " validate() - Too mach chars for position, max is 25");
        }

        int contactLines =
                textValidator.calculateLinesForSentence(personalInfo.getPhone(), maxCharsInLineLeft) +
                        textValidator.calculateLinesForSentence(personalInfo.getCity(), maxCharsInLineLeft) +
                        textValidator.calculateLinesForSentence(personalInfo.getEmail(), maxCharsInLineLeft);
        if (cvContent.getPersonalInfo().getPage() != null) {
            contactLines += textValidator.calculateLinesForSentence(personalInfo.getPage(), maxCharsInLineLeft);
        }
        LOGGER.info("Contact lines = " + contactLines);

        int descriptionLines = textValidator.calculateLinesForSentence(personalInfo.getDescription(), maxCharsInLineLeft);
        LOGGER.info("Description lines = " + descriptionLines);

        int educationLines = 0;
        for (Education e : cvContent.getEducationList()) {
            educationLines += textValidator.calculateLinesForSentence("- " + e.getSchoolName() + ", "
                    + e.getStartDate() + " - " + e.getEndDate() + ", kierunek: " + e.getSubject() + ", " + e.getDegree(), maxCharsInLineLeft);
        }
        LOGGER.info("Education lines = " + educationLines);

        int hobbiesLines = 0;
        for (String h : cvContent.getHobbies()) {
            hobbiesLines += textValidator.calculateLinesForSentence("- " + h + ",", maxCharsInLineLeft);
        }
        int totalLinesForLeftSection = contactLines + descriptionLines + educationLines + hobbiesLines;
        LOGGER.info("Hobbies lines = " + hobbiesLines);
        LOGGER.info("TOTAL LINES IN LEFT SECTION = " + totalLinesForLeftSection);

        int employmentLines = 0;
        for (Employment e : cvContent.getEmployments()) {
            employmentLines += textValidator.calculateLinesForSentence(e.getPosition() + ", " + e.getCompany()
                    + ", " + e.getStartDate() + " - " + e.getEndDate() + ".", maxCharsInLineRight);
            employmentLines += textValidator.calculateLinesForSentence("Zakres obowiązków: " + e.getJobDescription(), maxCharsInLineRight);
        }
        LOGGER.info("Emplyments lines : " + employmentLines);

        int skillsLines = 0;
        for (String s : cvContent.getSkills()) {
            skillsLines += textValidator.calculateLinesForSentence("- " + s + ",", maxCharsInLineRight);
        }
        int totalLinesForRightSection = employmentLines + skillsLines;
        LOGGER.info("Skills : " + skillsLines);
        LOGGER.info("TOTAL LINES IN RIGHT SECTION = " + totalLinesForRightSection);

        if (totalLinesForLeftSection > maxLinesLeft) {
            throw new TooMuchCharsException("too much chars inf left section");
        }
        if (totalLinesForRightSection > maxLinesRight) {
            throw new TooMuchCharsException("too much chars inf right section");
        }
    }

    @Override
    public Document prepareDocument() {
        Document document = new Document();
        Rectangle pageSize = new Rectangle(PageSize.A4);
        document.setPageSize(pageSize);
        document.setMargins(20, 20, 15, 15);

        return document;
    }

    @Override
    public void generateCv(Document document, CvContent cvContent) throws PdfException {
        try {
            addCvHeader(document, cvContent.getPersonalInfo());

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(35);
            table.setWidths(new float[]{13, 2, 28});

            PdfPCell leftCell = createLeftSection(cvContent);
            leftCell.setBorder(Rectangle.NO_BORDER);

            PdfPCell space = new PdfPCell(new Phrase(" "));
            space.setBorder(Rectangle.NO_BORDER);
            space.addElement(new LineSeparator(580, 10, new BaseColor(60, 93, 93), 100, -315));

            PdfPCell rightCell = createRightSection(cvContent);
            rightCell.setBorder(Rectangle.NO_BORDER);

            table.addCell(leftCell);
            table.addCell(space);
            table.addCell(rightCell);
            document.add(table);

            //document.add(createClauseParagraph("Wyrażam zgodę na przetwarzanie moich danych osobowych dla potrzeb obecnych oraz przyszłych procesów rekrutacyjnych prowadzonych przez Citycom, zgodnie z przepisami ustawy z dnia 29.08.1997 r. o ochronie danych osobowych (Dz.U. Nr 133, poz. 883 z późn. zm.)"));

        } catch (DocumentException e) {
            String message = CLASS_NAME + " - createCvBody() - ERROR: " + e;
            LOGGER.error(message);
            throw new PdfException(message);
        }
    }

    private void addCvHeader(Document document, PersonalInfo personalInfo) throws PdfException {
        try {
            boldFont.setColor(60, 93, 93);
            Paragraph p = createSimpleParagraph((personalInfo.getName() + " " + personalInfo.getSurname()).toUpperCase(), boldFont, 44);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            normalFont.setColor(80, 124, 124);
            p = createSimpleParagraph(personalInfo.getPosition().toUpperCase(), normalFont, 24);
            p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
            p.setIndentationRight(150);
            p.setIndentationLeft(150);
            document.add(p);
        } catch (DocumentException e) {
            String message = CLASS_NAME + " - addCvHeader() - ERROR: " + e;
            LOGGER.error(message);
            throw new PdfException(message);
        }
    }

    private PdfPCell createLeftSection(CvContent cvContent) {
        PdfPCell cell = new PdfPCell();
        int aligment = Element.ALIGN_RIGHT;

        cell.addElement(addSectionHeader("kontakt", aligment));
        cell.addElement(addPersonalInfoSection(cvContent.getPersonalInfo()));
        cell.addElement(addSectionHeader("o mnie", aligment));
        cell.addElement(addAboutMeSection(cvContent.getPersonalInfo().getDescription()));
        cell.addElement(addSectionHeader("wykształcenie", aligment));
        cell.addElement(addEducationSection(cvContent.getEducationList()));
        cell.addElement(addSectionHeader("hobby", aligment));
        cell.addElement(addSectionFromStringList(cvContent.getHobbies(), Element.ALIGN_RIGHT));

        return cell;
    }

    private PdfPCell createRightSection(CvContent cvContent) {
        PdfPCell cell = new PdfPCell();
        int aligment = Element.ALIGN_LEFT;

        cell.addElement(addSectionHeader("doświadczenie zawodowe", aligment));
        cell.addElement(addEmploymentSection(cvContent.getEmployments()));
        cell.addElement(addSectionHeader("umiejętności", aligment));
        cell.addElement(addSectionFromStringList(cvContent.getSkills(), aligment));

        return cell;
    }

    private Paragraph addSectionHeader(String text, int aligment) {
        String textWithExtraSpace = "";
        for (int i = 0; i < text.length(); i++) {
            textWithExtraSpace += text.charAt(i) + " ";
        }
        boldFont.setColor(60, 93, 93);
        Paragraph p = createSimpleParagraph(textWithExtraSpace.toUpperCase(), boldFont, SECTION_HEADER_SIZE);
        p.setSpacingAfter(5);
        p.setAlignment(aligment);

        return p;
    }

    private Paragraph addPersonalInfoSection(PersonalInfo personalInfo) {
        Paragraph p = new Paragraph();
        int aligment = Element.ALIGN_RIGHT;

        Paragraph helper = createSimpleParagraph(personalInfo.getPhone(), normalFont, PARAGRAPH_SIZE);
        helper.setAlignment(aligment);
        p.add(helper);

        helper = createSimpleParagraph(personalInfo.getEmail(), normalFont, PARAGRAPH_SIZE);
        helper.setAlignment(aligment);
        p.add(helper);

        helper = createSimpleParagraph(personalInfo.getCity(), normalFont, PARAGRAPH_SIZE);
        helper.setAlignment(aligment);
        p.add(helper);

        if (personalInfo.getPage() != null) {
            helper = createSimpleParagraph(personalInfo.getPage(), normalFont, PARAGRAPH_SIZE);
            helper.setAlignment(aligment);
            p.add(helper);
        }
        p.setSpacingAfter(15);

        return p;
    }

    private Paragraph addAboutMeSection(String text) {
        Paragraph p = createSimpleParagraph(text, normalFont, PARAGRAPH_SIZE);
        p.setAlignment(Element.ALIGN_RIGHT);
        p.setSpacingAfter(15);

        return p;
    }

    private Paragraph addEducationSection(List<Education> educations) {
        Paragraph mainParagraph = new Paragraph();
        List<Education> sorted = PatternHelper.sortEducationList(educations);

        for (int i = 0; i < sorted.size(); i++) {
            Education e = sorted.get(i);
            Paragraph helperParagraph;
            if (i == sorted.size() - 1) {
                helperParagraph = createSimpleParagraph(("- " + e.getSchoolName() + ", " + e.getStartDate() + " - "
                                + e.getEndDate() + ", kierunek: " + e.getSubject() + ", " + e.getDegree() + "."),
                        normalFont, PARAGRAPH_SIZE);
            } else {
                helperParagraph = createSimpleParagraph(("- " + e.getSchoolName() + ", " + e.getStartDate() + " - "
                                + e.getEndDate() + ", kierunek: " + e.getSubject() + ", " + e.getDegree() + ","),
                        normalFont, PARAGRAPH_SIZE);
                helperParagraph.setSpacingAfter(5);
            }
            helperParagraph.setAlignment(Element.ALIGN_RIGHT);
            mainParagraph.add(helperParagraph);
            mainParagraph.setSpacingAfter(15);
        }
        return mainParagraph;
    }

    private Paragraph addSectionFromStringList(List<String> list, int aligment) {
        Paragraph mainParagraph = new Paragraph();

        for (int i = 0; i < list.size(); i++) {
            Paragraph helperParagraph;
            if (i == list.size() - 1) {
                helperParagraph = createSimpleParagraph("- " + list.get(i) + ".", normalFont, PARAGRAPH_SIZE);
            } else {
                helperParagraph = createSimpleParagraph("- " + list.get(i) + ",", normalFont, PARAGRAPH_SIZE);
            }
            helperParagraph.setAlignment(aligment);
            mainParagraph.add(helperParagraph);
        }
        return mainParagraph;
    }

    private Paragraph addEmploymentSection(List<Employment> employments) {
        Paragraph paragraph = new Paragraph();
        List<Employment> sorted = PatternHelper.sortEmploymentList(employments);

        for (int i = 0; i < sorted.size(); i++) {
            Employment e = sorted.get(i);
            //paragraph.setSpacingAfter(16);
            paragraph.add(createSimpleParagraph(e.getPosition().toUpperCase()
                    + ", " + e.getCompany() + ", " + e.getStartDate() + " - " + e.getEndDate() + ".", normalFont, PARAGRAPH_SIZE));
            //paragraph.setSpacingAfter(0);
            paragraph.add(createSimpleParagraph("Zakres obowiązków: " + e.getJobDescription() + ".", normalFont, PARAGRAPH_SIZE));
            paragraph.add(new Paragraph(" "));
        }
        return paragraph;
    }

    @Override
    public Paragraph createClauseParagraph(String clause) {
        Paragraph paragraph = new Paragraph();
        paragraph.add(createSimpleParagraph(clause, normalFont, 6));
        paragraph.setSpacingBefore(24);

        return paragraph;

    }
}

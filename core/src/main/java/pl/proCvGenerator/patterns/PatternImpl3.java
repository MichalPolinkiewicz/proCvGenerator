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
import sun.awt.image.PixelConverter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static pl.proCvGenerator.patterns.helpers.PatternHelper.*;
import static pl.proCvGenerator.patterns.helpers.PatternHelper.validateEmployments;
import static pl.proCvGenerator.patterns.helpers.PatternHelper.validateStringList;

public class PatternImpl3 implements Pattern {

    public static final Logger LOGGER = LoggerFactory.getLogger(PatternImpl3.class);
    private static final String CLASS_NAME = PatternImpl3.class.getSimpleName();
    private Font normalFont = Fonts.ANTONIO_NORMAL;
    private Font boldFont = Fonts.ANTONIO_BOLD;
    private static final int MAX_LINES_FOR_PAGE = 26;

    //left side STRING max = 32
    //right side = 80
    //max liczba wierszy = 26
    //extra przy employent = 27
    //extra przy education = 20

    @Override
    public Document prepareDocument() {
        Document document = new Document();
        Rectangle pageSize = new Rectangle(PageSize.A4);
        document.setPageSize(pageSize);
        document.setMargins(20, 20, 15, 15);

        return document;
    }

    @Override
    public void generateCv(Document document, CvContent cvContent) throws TooMuchCharsException {
        createCvStructure(document);
        createCvBody(document, cvContent);
    }

    public void createCvStructure(Document document) {
        try {
            Rectangle rectangle = new Rectangle(0, 0, 199, 842);
            rectangle.setBackgroundColor(BaseColor.WHITE);
            document.add(rectangle);

            rectangle = new Rectangle(202, 0, 595, 842);
            rectangle.setBackgroundColor(BaseColor.WHITE);
            document.add(rectangle);

            rectangle = new Rectangle(0, 680, 595, 842);
            rectangle.setBackgroundColor(BaseColor.WHITE);
            document.add(rectangle);

        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + " - createCvStructure() - ERROR: " + e);
        }
    }

    public void createCvBody(Document document, CvContent cvContent) throws TooMuchCharsException {
        addCvHeader(document, cvContent.getPersonalInfo());
        //walidacje
        //left side STRING max = 32
        //right side = 80
        //max liczba wierszy = 26
        //extra przy employent = 27
        //extra przy education = 20
        // LEFT COLUMN VALIDATION

        BigDecimal desc = validateString(cvContent.getPersonalInfo().getDescription(), 29);
        System.out.println("Description: " + desc);
        BigDecimal edu = validateEducationsList(cvContent.getEducationList(), 26, 20);
        System.out.println("Education: " + edu );
        BigDecimal hobby =  validateStringList(cvContent.getHobbies(),260);
        System.out.println("Hobby: " + hobby);
        List<String> list = new ArrayList<>();
        list.add(cvContent.getPersonalInfo().getPhone());
        list.add(cvContent.getPersonalInfo().getEmail());
        list.add(cvContent.getPersonalInfo().getCity());
        if (cvContent.getPersonalInfo().getPage() != null){
            list.add(cvContent.getPersonalInfo().getPage());
        }
        BigDecimal info = validateStringList(list,25);
        System.out.println("Info: " + info);
        System.out.println("Result: " + desc.add(edu).add(hobby).add(info));


//        //RIGHT COLUMN VALIDATION
//        if (validateStringList(skills, MAX_CHARS_IN_LINE_FOR_STRING_LIST_RIGHT)
//                .add(validateEmployments(employments, MAX_CHARS_IN_LINE_FOR_EMPLOYMENT, EXTRA_CHARS_FOR_EMPLOYMENT))
//                .compareTo(maxLinesForPage) > 0) {
//            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: too much chars in RIGHT SIDE");
//            throw new TooMuchCharsException("Too much chars in right section");
//        }

        try {
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(35);
            table.setWidths(new float[]{13, 2, 28});

            PdfPCell leftCell = createLeftSection(cvContent);
            leftCell.setBorder(Rectangle.NO_BORDER);
            PdfPCell space = new PdfPCell(new Phrase(" "));
            space.setBorder(Rectangle.NO_BORDER);
            space.addElement(new LineSeparator(660, 10, new BaseColor(60, 93, 93), 100, -330));
            PdfPCell rightCell = createRightSection(cvContent);
            rightCell.setBorder(Rectangle.NO_BORDER);

            table.addCell(leftCell);
            table.addCell(space);
            table.addCell(rightCell);

            document.add(table);
        } catch (DocumentException e) {

        }
    }

    private void addCvHeader(Document document, PersonalInfo personalInfo) {

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
            LOGGER.error(CLASS_NAME + "- addCvHeader() - " + "ERROR: " + e);
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
        cell.addElement(addSectionFromList(cvContent.getHobbies(), Element.ALIGN_RIGHT));

        return cell;
    }

    private PdfPCell createRightSection(CvContent cvContent) {
        PdfPCell cell = new PdfPCell();
        int aligment = Element.ALIGN_LEFT;

        cell.addElement(addSectionHeader("doświadczenie zawodowe", aligment));
        cell.addElement(addEmploymentSection(cvContent.getEmployments()));
        cell.addElement(addSectionHeader("umiejętności", aligment));
        cell.addElement(addSectionFromList(cvContent.getSkills(), aligment));

        return cell;
    }

    private Paragraph addSectionHeader(String text, int aligment) {
        String textWithExtraSpace = "";
        for (int i = 0; i < text.length(); i++){
            textWithExtraSpace += text.charAt(i) + " ";
        }
        boldFont.setColor(60, 93, 93);
        Paragraph p = createSimpleParagraph(textWithExtraSpace.toUpperCase(), boldFont, 18);
        p.setSpacingAfter(5);
        p.setAlignment(aligment);

        return p;
    }

    private Paragraph addPersonalInfoSection(PersonalInfo personalInfo) {
        Paragraph p = new Paragraph();
        int aligment = Element.ALIGN_RIGHT;

        Paragraph helper = createSimpleParagraph(personalInfo.getPhone(), normalFont, 12);
        helper.setAlignment(aligment);
        p.add(helper);

        helper = createSimpleParagraph(personalInfo.getEmail(), normalFont, 12);
        helper.setAlignment(aligment);
        p.add(helper);

        helper = createSimpleParagraph(personalInfo.getCity(), normalFont, 12);
        helper.setAlignment(aligment);
        p.add(helper);

        if (personalInfo.getPage() != null){
            helper = createSimpleParagraph(personalInfo.getPage(), normalFont, 12);
            helper.setAlignment(aligment);
            p.add(helper);
        }
        p.setSpacingAfter(15);

        return p;
    }

    private Paragraph addAboutMeSection(String text) {
        Paragraph p = createSimpleParagraph(text, normalFont, 12);
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
                helperParagraph = createSimpleParagraph(("- " + e.getSchoolName() + ", " + e.getStartDate() + " - " + e.getEndDate() + ", kierunek: " + e.getSubject() + ", " + e.getDegree() + "."), normalFont, 12);
            } else {
                helperParagraph = createSimpleParagraph(("- " + e.getSchoolName() + ", " + e.getStartDate() + " - " + e.getEndDate() + ", kierunek: " + e.getSubject() + ", " + e.getDegree() + ","), normalFont, 12);
                helperParagraph.setSpacingAfter(5);
            }
            helperParagraph.setAlignment(Element.ALIGN_RIGHT);
            mainParagraph.add(helperParagraph);
            mainParagraph.setSpacingAfter(15);
        }
        return mainParagraph;
    }

    private Paragraph addSectionFromList(List<String> list, int aligment) {
        Paragraph mainParagraph = new Paragraph();

        for (int i = 0; i < list.size(); i++) {
            Paragraph helperParagraph;
            if (i == list.size() - 1) {
                helperParagraph = createSimpleParagraph("- " + list.get(i) + ".", normalFont, 12);
            } else {
                helperParagraph = createSimpleParagraph("- " + list.get(i) + ",", normalFont, 12);
            }
            helperParagraph.setAlignment(aligment);
            mainParagraph.add(helperParagraph);
        }
        return mainParagraph;
    }

    private Paragraph addEmploymentSection(List<Employment> employments){
        Paragraph paragraph = new Paragraph();
        List<Employment> sorted = PatternHelper.sortEmploymentList(employments);

        for (int i = 0; i < sorted.size(); i++) {
            Employment e = sorted.get(i);
            paragraph.add(createSimpleParagraph(e.getPosition().toUpperCase()
                    + ", " + e.getCompany() + ", " + e.getStartDate() + " - " + e.getEndDate() + ".", normalFont,12));
            paragraph.add(createSimpleParagraph("Zakres obowiązków: " + e.getJobDescription() + ".", normalFont, 12));
            paragraph.add(new Paragraph(" "));
        }
        return paragraph;
    }

}

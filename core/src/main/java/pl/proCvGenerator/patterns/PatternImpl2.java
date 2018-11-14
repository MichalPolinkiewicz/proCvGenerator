package pl.proCvGenerator.patterns;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;
import pl.proCvGenerator.fonts.Fonts;
import pl.proCvGenerator.patterns.helpers.PatternHelper;

import java.io.IOException;
import java.util.List;

public class PatternImpl2 implements Pattern {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatternImpl2.class);
    private static final String CLASS_NAME = PatternImpl2.class.getSimpleName();
    private static final String PATH_TO_IMAGES = "/home/michal/IdeaProjects/proCvGenerator/core/src/main/resources/images/";

    @Override
    public Document prepareDocument() {
        Document document = new Document();
        Rectangle pageSize = new Rectangle(PageSize.A4);
        pageSize.setBackgroundColor(new BaseColor(226, 222, 215));
        document.setPageSize(pageSize);
        document.setMargins(15, 15, 15, 15);

        return document;
    }

    @Override
    public void generateCv(Document document, CvContent cvContent) {
        createLayout(document);
        createGeneralInfoSection(document, cvContent.getPersonalInfo());
        createBody(document, cvContent);

        System.out.println("Description: " + validateString(cvContent.getPersonalInfo().getDescription()));
        System.out.println("Skills: " + validateList(cvContent.getSkills()));
        System.out.println("Hobbies: " + validateList(cvContent.getHobbies()));
    }

    public void createLayout(Document document) {
        try {
            Rectangle rectangle = new Rectangle(0, 0, 200, 842);
            rectangle.setBackgroundColor(BaseColor.CYAN);
            document.add(rectangle);

            rectangle = new Rectangle(201, 0, 595, 842);
            rectangle.setBackgroundColor(BaseColor.DARK_GRAY);
            document.add(rectangle);

            rectangle = new Rectangle(0, 660, 595, 842);
            rectangle.setBackgroundColor(BaseColor.ORANGE);

            document.add(rectangle);
        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + " - createLayout() - ERROR: " + e);
        }
    }

    public void createGeneralInfoSection(Document document, PersonalInfo personalInfo) {
        String methodName = "createGeneralInfoSection()";

        try {
            Paragraph p = createParagraphForHeaderTable((personalInfo.getName() + " " + personalInfo.getSurname()).toUpperCase(), Fonts.CALIBRI_BOLD, 28);
            p.setAlignment(Element.ALIGN_CENTER);
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

        int maxLines = 29;
        String description = cvContent.getPersonalInfo().getDescription();
        List<String> skills = cvContent.getSkills();
        List<String> hobbies = cvContent.getHobbies();

        if (maxLines < validateString(description) + validateList(skills) + validateList(hobbies)) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: too much chars");
        }

        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 2});
            table.setSpacingBefore(40);

            PdfPCell leftCell = createLeft(cvContent);
            PdfPCell rightCell = createRight(cvContent);

            table.addCell(leftCell);
            table.addCell(rightCell);

            document.add(table);

        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: " + e);
        }
    }

    public PdfPCell createLeft(CvContent cvContent) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.addElement(createHeaderForMainTable("o mnie"));
        cell.addElement(createSeparator());
        cell.addElement(createParagraph(cvContent.getPersonalInfo().getDescription()));
        cell.addElement(createHeaderForMainTable("umiejętności"));
        cell.addElement(createSeparator());

        List<String> skills = cvContent.getSkills();
        for (int i = 0; i < skills.size(); i++) {
            if (i == skills.size() - 1) {
                cell.addElement(createParagraph("- " + skills.get(i) + "."));
            } else {
                cell.addElement(createParagraph("- " + skills.get(i) + ","));
            }
        }

        cell.addElement(createHeaderForMainTable("hobby"));
        cell.addElement(createSeparator());
        List<String> hobbies = cvContent.getHobbies();
        for (int i = 0; i < hobbies.size(); i++) {
            if (i == hobbies.size() - 1) {
                cell.addElement(createParagraph("- " + hobbies.get(i) + "."));
            } else {
                cell.addElement(createParagraph("- " + hobbies.get(i) + ","));
            }
        }

        return cell;
    }

    public PdfPCell createRight(CvContent cvContent) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPaddingLeft(15);
        cell.addElement(createHeaderForMainTable("doświadczenie zawodowe"));
        cell.addElement(createSeparator());

        List<Employment> employments = PatternHelper.sortEmploymentList(cvContent.getEmployments());
        for (int i = 0; i < employments.size(); i++) {
            Employment e = employments.get(i);
            cell.addElement(createParagraph(e.getPosition().toUpperCase()
                    + ", " + e.getCompany() + ", " + e.getStartDate() + " - " + e.getEndDate() + "."));
            cell.addElement(createParagraph("Zakres obowiązków: " + e.getJobDescription() + "."));
            cell.addElement(new Paragraph(" "));
        }

        return cell;
    }

    public Paragraph createHeaderForMainTable(String text) {
        Font font = Fonts.CALIBRI_NORMAL;
        font.setSize(16);
        Paragraph p = new Paragraph(text.toUpperCase(), font);

        return p;
    }

    public Paragraph createParagraph(String text) {
        Font font = Fonts.CALIBRI_NORMAL;
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
        table.setWidthPercentage(90);
        table.setSpacingBefore(30);
        table.setWidths(
                new float[]{
                        0.3f,
                        0.06f * personalInfo.getPhone().length(),
                        0.3f,
                        0.05f * personalInfo.getEmail().length(),
                        0.3f,
                        0.05f * personalInfo.getCity().length()});
        return table;
    }

    public Paragraph createParagraphForHeaderTable(String text, Font font, int fontSize) {
        Paragraph p = PatternHelper.createSimpleParagraph(text, font, fontSize);
        p.setSpacingBefore(10);
        p.setSpacingAfter(10);

        return p;
    }

    public PdfPCell matchImageToHeaderTable(Image image, float width, float height) {
        image.scaleAbsolute(width, height);
        PdfPCell cell = new PdfPCell(image);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.NO_BORDER);

        return cell;
    }

    public PdfPCell matchParagraphToHeaderTable(Paragraph p) {
        PdfPCell cell = new PdfPCell(p);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.NO_BORDER);

        return cell;
    }


    public Paragraph createNormalPagaraphForLeftSection(String text) {
        Paragraph p = PatternHelper.createSimpleParagraph(text, Fonts.CALIBRI_NORMAL, 14);
        p.setLeading(2, 1);

        return p;
    }

    public int validateList(List<String> list) {
        int totalLinesForSection = 0;

        for (int i = 0; i < list.size(); i++) {
            double itemChars = list.get(i).length();
            int linesForRecord = 1;
            if (itemChars / 26 > 1 && itemChars / 26 < 3) {
                linesForRecord = 2;
            } else if (itemChars / 26 > 3) {
                linesForRecord = 3;
            }
            totalLinesForSection += linesForRecord;
        }
        return totalLinesForSection;
    }

    public int validateString(String s) {
        return (s.length() / 24);
    }

    private void addList(List<String> list, PdfPTable table, PdfPCell cell) {
        table.addCell(cell);
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                cell.addElement(createNormalPagaraphForLeftSection("- " + list.get(i) + "."));
            } else {
                cell.addElement(createNormalPagaraphForLeftSection("- " + list.get(i) + ","));
            }
        }
        table.addCell(cell);
    }
}

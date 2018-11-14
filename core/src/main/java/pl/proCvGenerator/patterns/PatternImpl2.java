package pl.proCvGenerator.patterns;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.dto.PersonalInfo;
import pl.proCvGenerator.fonts.Fonts;
import pl.proCvGenerator.patterns.helpers.PatternHelper;

import java.io.IOException;
import java.util.List;

public class PatternImpl2 implements Pattern {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatternImpl2.class);
    private static final String CLASS_NAME = PatternImpl2.class.getSimpleName();

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
        createLeftSection(document, cvContent);

    }

    public void createGeneralInfoSection(Document document, PersonalInfo personalInfo) {
        String methodName = "createGeneralInfoSection()";

        try {
            Paragraph p = createParagraphForHeaderTable((personalInfo.getName() + " " + personalInfo.getSurname()).toUpperCase(), Fonts.CALIBRI_BOLD, 28);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            PdfPTable table = createHeaderTable(personalInfo);
            //Image image = Image.getInstance("/home/michal/IdeaProjects/proCvGenerator/core/src/main/resources/images/whitephone.png");
            Image image = Image.getInstance("C:/Users/MPl/IdeaProjects/proCvGenerator/core/src/main/resources/images/whitephone.png");

            PdfPCell cell = matchImageToCell(image, 35, 35);
            table.addCell(cell);
            cell = matchParagraphToCell(createParagraphForHeaderTable(personalInfo.getPhone(), Fonts.CALIBRI_NORMAL, 14));
            table.addCell(cell);

            //image = Image.getInstance("/home/michal/IdeaProjects/proCvGenerator/core/src/main/resources/images/whitemessage.png");
            image = Image.getInstance("C:/Users/MPl/IdeaProjects/proCvGenerator/core/src/main/resources/images/whitemessage.png");
            cell = matchImageToCell(image, 45, 45);
            table.addCell(cell);
            cell = matchParagraphToCell(createParagraphForHeaderTable(personalInfo.getEmail(), Fonts.CALIBRI_NORMAL, 14));
            table.addCell(cell);

            //image = Image.getInstance("/home/michal/IdeaProjects/proCvGenerator/core/src/main/resources/images/whitehouse.png");
            image = Image.getInstance("C:/Users/MPl/IdeaProjects/proCvGenerator/core/src/main/resources/images/whitehouse.png");
            cell = matchImageToCell(image, 32, 32);
            table.addCell(cell);
            cell = matchParagraphToCell(createParagraphForHeaderTable(personalInfo.getCity(), Fonts.CALIBRI_NORMAL, 14));
            table.addCell(cell);

            document.add(table);

        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: " + e);
        } catch (IOException e) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: " + e);
        }
    }

    public void createLeftSection(Document document, CvContent cvContent) {
        String methodName = "createLeftSection";

        try {
//            PdfPTable table = new PdfPTable(1);
//            table.setHorizontalAlignment(Element.ALIGN_LEFT);
//            table.setSpacingBefore(50);
//            table.setWidthPercentage(new float[]{180}, PageSize.A4);
//
//            PdfPCell cell = createHeaderCellForLeftSection("o mnie");
//            table.addCell(cell);
//            cell = new PdfPCell();
//            cell.addElement(createLineSeparatorForLeftSection());
//            cell.setBorder(Rectangle.NO_BORDER);
//            table.addCell(cell);
//
//            cell = createNormalCellForLeftSection(cvContent.getPersonalInfo().getDescription());
//            cell.setLeading(2, 1);
//            cell.setFixedHeight(validateString(cvContent.getPersonalInfo().getDescription()) * 17);
//            System.out.println(cvContent.getPersonalInfo().getDescription().length());
//            table.addCell(cell);
//
//            cell = new PdfPCell(createNormalCellForLeftSection(" "));
//            table.addCell(cell);
//
//            cell = createHeaderCellForLeftSection("umiejętności");
//            table.addCell(cell);
//
//            cell = new PdfPCell();
//            cell.addElement(createLineSeparatorForLeftSection());
//            cell.setBorder(Rectangle.NO_BORDER);
//            table.addCell(cell);
//
//            cell = createNormalCellForLeftSection("");
//            table.addCell(cell);
//
//            for (int i = 0; i < cvContent.getSkills().size(); i++) {
//                if (i == cvContent.getSkills().size() - 1) {
//                    cell.addElement(createNormalPagaraphForLeftSection("- " + cvContent.getSkills().get(i) + "."));
//                } else {
//                    cell.addElement(createNormalPagaraphForLeftSection("- " + cvContent.getSkills().get(i) + ","));
//                }
//            }
//            cell.setFixedHeight(validateList(cvContent.getSkills()) * 17);
//            table.addCell(cell);
//
//            cell = createNormalCellForLeftSection(" ");
//            table.addCell(cell);
//            cell = createHeaderCellForLeftSection("hobby");
//            table.addCell(cell);
//            cell = new PdfPCell();
//            cell.addElement(createLineSeparatorForLeftSection());
//            cell.setBorder(Rectangle.NO_BORDER);
//            table.addCell(cell);
//
//            cell = createNormalCellForLeftSection("");
//            table.addCell(cell);
//            for (int i = 0; i < cvContent.getHobbies().size(); i++) {
//                if (i == cvContent.getHobbies().size() - 1) {
//                    cell.addElement(createNormalPagaraphForLeftSection("- " + cvContent.getHobbies().get(i) + "."));
//                } else {
//                    cell.addElement(createNormalPagaraphForLeftSection("- " + cvContent.getHobbies().get(i) + ","));
//                }
//            }
//            cell.setFixedHeight(validateList(cvContent.getHobbies()) * 17);
//            System.out.println(validateList(cvContent.getHobbies()));
//            table.addCell(cell);
//
//            document.add(table);
//            table.setLockedWidth(true);
            int maxLines = 31;

            Paragraph p = createHeaderParagraphForLeftSection("o mnie");
            p.setSpacingBefore(40);
            document.add(p);
            document.add(createLineSeparatorForLeftSection());
            p = createParagraphForLeftSection(cvContent.getPersonalInfo().getDescription(), Fonts.CALIBRI_NORMAL, 14);
            p.setSpacingBefore(15);
            document.add(p);

            p = createHeaderParagraphForLeftSection("umiejętności");
            document.add(p);
            document.add(createLineSeparatorForLeftSection());
            for (int i = 0; i < cvContent.getSkills().size(); i++) {
                    p = createParagraphForLeftSection("- " + cvContent.getSkills().get(i) + ",", Fonts.CALIBRI_NORMAL, 14);
                    if (i == 0) {
                        p.setSpacingBefore(15);
                    }
                    if (i == cvContent.getSkills().size() - 1) {
                        p = createParagraphForLeftSection("- " + cvContent.getSkills().get(i) + ".", Fonts.CALIBRI_NORMAL, 14);
                    }
                    document.add(p);
            }

            p = createHeaderParagraphForLeftSection("hobby");
            document.add(p);
            document.add(createLineSeparatorForLeftSection());

            for (int i = 0; i < cvContent.getHobbies().size(); i++) {
                    p = createParagraphForLeftSection("- " + cvContent.getHobbies().get(i) + ", ", Fonts.CALIBRI_NORMAL, 14);
                    if (i == 0) {p.setSpacingBefore(15);}
                    if (i == cvContent.getHobbies().size()-1){
                        p = createParagraphForLeftSection("- " + cvContent.getHobbies().get(i) + ". ", Fonts.CALIBRI_NORMAL, 14);
                    }
                    document.add(p);
            }
        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + " - " + methodName + " - " + "ERROR: " + e);
        }
    }

    public void createLayout(Document document) {
        try {
            Rectangle rectangle = new Rectangle(0, 0, 200, 842);
            rectangle.setBackgroundColor(BaseColor.CYAN);
            document.add(rectangle);

            rectangle = new Rectangle(201, 0, 595, 842);
            rectangle.setBackgroundColor(BaseColor.DARK_GRAY);
            document.add(rectangle);

            rectangle = new Rectangle(0, 650, 595, 842);
            rectangle.setBackgroundColor(BaseColor.ORANGE);

            document.add(rectangle);
        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + " - createLayout() - ERROR: " + e);
        }
    }

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

    public Paragraph createHeaderParagraphForLeftSection(String text) {
        Paragraph p = createParagraphForLeftSection(text.toUpperCase(), Fonts.CALIBRI_BOLD, 16);
        p.setSpacingBefore(10);

        return p;
    }

    public LineSeparator createLineSeparatorForLeftSection() {
        LineSeparator lineSeparator = new LineSeparator(2, 100, BaseColor.BLACK, 20, -5);
        lineSeparator.setAlignment(Element.ALIGN_LEFT);

        return lineSeparator;
    }

    public Paragraph createParagraphForLeftSection(String text, Font font, int fontsize) {
        Paragraph p = PatternHelper.createSimpleParagraph(text, font, fontsize);
        p.setIndentationRight(395);

        return p;
    }

    public PdfPCell matchImageToCell(Image image, float width, float height) {
        image.scaleAbsolute(width, height);
        PdfPCell cell = new PdfPCell(image);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.NO_BORDER);

        return cell;
    }

    public PdfPCell matchParagraphToCell(Paragraph p) {
        PdfPCell cell = new PdfPCell(p);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBorder(Rectangle.NO_BORDER);

        return cell;
    }

    public PdfPCell createNormalCellForLeftSection(String text) {
        PdfPCell cell = new PdfPCell(createParagraphForLeftSection(text, Fonts.CALIBRI_NORMAL, 14));
        //cell.setBorder(Rectangle.NO_BORDER);

        return cell;
    }

    public PdfPCell createHeaderCellForLeftSection(String text) {
        PdfPCell cell = new PdfPCell(createHeaderParagraphForLeftSection(text));
        //cell.setBorder(Rectangle.NO_BORDER);
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
            if (itemChars / 23 > 1 && itemChars / 23 < 3) {
                linesForRecord = 2;
            } else if (itemChars / 23 > 3) {
                linesForRecord = 3;
            }
            totalLinesForSection += linesForRecord;
        }
        return totalLinesForSection;
    }

    public int validateString(String s) {
        return (s.length() / 23) + 1;
    }
}

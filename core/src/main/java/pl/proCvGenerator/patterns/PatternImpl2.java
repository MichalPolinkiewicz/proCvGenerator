package pl.proCvGenerator.patterns;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.fonts.Fonts;

import java.io.IOException;

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
//        createHeader(document);
//        createMainTable(document);
        test(document);
        createGeneralInfoSection(document);
    }

    public void createHeader(Document document) {
        Element element = new Rectangle(10, 660, 585, 832);
        ((Rectangle) element).setBackgroundColor(BaseColor.BLUE);

        try {
            document.add(element);
            Paragraph paragraph = createParagraph("Jan Norlak", Fonts.UBUNTU_BOLD, 28);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingBefore(10);
            paragraph.setSpacingAfter(30);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(3);
            table.setWidths(new int[]{1, 1, 1});
            table.setWidthPercentage(100);

            PdfPCell cell = new PdfPCell();
            cell.setFixedHeight(60);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);

            Image image = Image.getInstance("/home/michal/IdeaProjects/proCvGenerator/core/src/main/resources/images/phone.png");
            image.scalePercent(2.5f);

            paragraph = new Paragraph(new Chunk(image, 0, -20));
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.add(createChunk("michal.polinkiewicz@gmail.com", Fonts.UBUNTU_NORMAL, 12));

            cell.addElement(paragraph);
            //cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            table.addCell(cell);
            table.addCell(cell);
            table.addCell(cell);
            table.setSpacingAfter(50);
            document.add(table);


        } catch (DocumentException e) {

        } catch (IOException e) {
            System.out.println(e);

        }
    }

    public void createMainTable(Document document) {
        String methodName = "createMainTable()";

        try {
            Paragraph paragraph;
            PdfPTable table = new PdfPTable(2);
            table.setWidths(new int[]{1, 2});
            table.setWidthPercentage(100);

            PdfPCell cell = new PdfPCell();
            paragraph = createParagraph("this is header", Fonts.UBUNTU_BOLD, 18);
            cell.addElement(paragraph);
            cell.setFixedHeight(300);

            for (int i = 1; i <= 45; i++) {
                paragraph = new Paragraph("bbb" + i);
                cell.addElement(paragraph);
            }

            table.addCell(cell);
            table.addCell("ff");
            document.add(table);


        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + "." + methodName + " - ERROR: " + e);
        }

    }

    public void test(Document document) {
        try {
            Rectangle rectangle = new Rectangle(0, 0, 200, 842);
            rectangle.setBackgroundColor(BaseColor.CYAN);
            document.add(rectangle);

            rectangle = new Rectangle(201, 0, 595, 842);
            rectangle.setBackgroundColor(BaseColor.DARK_GRAY);
            document.add(rectangle);

            rectangle = new Rectangle(0, 670, 595, 842);
            rectangle.setBackgroundColor(BaseColor.ORANGE);

            document.add(rectangle);
        } catch (DocumentException e) {

        }

    }

    public void createGeneralInfoSection(Document document) {
        try {
            Paragraph p = createParagraph("Jan Nowak", Fonts.CALIBRI_BOLD, 36);
            p.setSpacingBefore(-10);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            PdfPTable table = new PdfPTable(6);
            table.setWidths(new float[]{0.2f, 1, 0.2f, 1, 0.2f, 1});
            Image image = Image.getInstance("C:/Users/MPl/IdeaProjects/proCvGenerator/core/src/main/resources/images/phone.png");
            image.scalePercent(2);

            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(image);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(createParagraph("656-987-345", Fonts.UBUNTU_NORMAL, 16));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(image);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(createParagraph("michal.polinkiewicz@gmail.com", Fonts.UBUNTU_NORMAL, 16));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            table.addCell("3");
            table.addCell("4");
            table.addCell("5");
            table.addCell("6");

            table.setSpacingBefore(30);
            document.add(table);

        } catch (IOException e) {

        } catch (DocumentException e){

        }
    }

    public Paragraph createParagraph(String text, Font font, int fontSize) {
        font.setSize(fontSize);
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingBefore(10);
        paragraph.setSpacingAfter(10);

        return paragraph;
    }

    public Paragraph createHeaderParagraph(String text, Font font, int fontSize) {
        Paragraph p = createParagraph(text, font, fontSize);
        p.setSpacingBefore(10);
        p.setSpacingAfter(10);

        return p;
    }

    public Chunk createChunk(String text, Font font, int fontSize) {
        font.setSize(fontSize);

        return new Chunk(text, font);
    }


}

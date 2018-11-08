package pl.proCvGenerator.patterns;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.fonts.Fonts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.MalformedInputException;

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
        createHeader(document);
        createMainTable(document);
    }

    public void createHeader(Document document) {
        Element element = new Rectangle(10, 680, 585, 832);
        ((Rectangle) element).setBackgroundColor(BaseColor.BLUE);

        try {
            document.add(element);
            Paragraph paragraph = createHeaderPharagraph("Jan Norlak", Fonts.UBUNTU_BOLD, 28);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingBefore(10);
            paragraph.setSpacingAfter(20);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(4);
            table.setWidths(new int[]{1, 1, 1,1});
            table.setWidthPercentage(100);

            PdfPCell cell = new PdfPCell(createHeaderPharagraph("xx", Fonts.UBUNTU_NORMAL, 16));
            cell.setFixedHeight(50);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            Image image = Image.getInstance("/home/michal/IdeaProjects/proCvGenerator/core/src/main/resources/images/phone.png");
            image.scalePercent(1.5f);
            Paragraph paragraph1 = new Paragraph("xx");
            paragraph1.setAlignment(Element.ALIGN_RIGHT);
            cell.addElement(paragraph1);
            image.setAlignment(Element.ALIGN_LEFT);
            cell.addElement(image);



            //cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            table.addCell(cell);
            table.addCell(cell);
            table.addCell(cell);
            table.setSpacingAfter(50);
            document.add(table);


        } catch (DocumentException e) {

        }
        catch (IOException e){
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
            paragraph = createHeaderPharagraph("this is header", Fonts.UBUNTU_BOLD, 18);
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

    public Paragraph createHeaderPharagraph(String text, Font font, int fontSize) {
        font.setSize(fontSize);
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingBefore(10);
        paragraph.setSpacingAfter(10);

        return paragraph;
    }

}

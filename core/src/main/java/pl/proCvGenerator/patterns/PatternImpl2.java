package pl.proCvGenerator.patterns;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.fonts.Fonts;

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

    public void createHeader(Document document){
        Element element = new Rectangle(0,600,500,500);
        ((Rectangle) element).setBackgroundColor(BaseColor.BLACK);
        try {
            document.add(element);
        } catch (DocumentException e){

        }
    }

    public void createMainTable(Document document) {
        String methodName = "createMainTable()";

        PdfPTable table = new PdfPTable(2);

        try {
            table.setWidths(new int[]{1, 2});
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell();

            Paragraph paragraph = createHeaderPharagraph("this is header", Fonts.UBUNTU_BOLD, 18);
            cell.addElement(paragraph);
            cell.setFixedHeight(100);

            for (int i = 1; i <= 45; i++){
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

    public Paragraph createHeaderPharagraph(String text, Font font, int fontSize){
        font.setSize(fontSize);
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingBefore(10);
        paragraph.setSpacingAfter(10);

        return paragraph;
    }

}

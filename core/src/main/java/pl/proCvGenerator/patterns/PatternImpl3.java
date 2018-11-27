package pl.proCvGenerator.patterns;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.dto.PersonalInfo;
import pl.proCvGenerator.fonts.Fonts;
import pl.proCvGenerator.patterns.helpers.PatternHelper;

public class PatternImpl3 implements Pattern {

    public static final Logger LOGGER = LoggerFactory.getLogger(PatternImpl3.class);
    private static final String CLASS_NAME = PatternImpl3.class.getSimpleName();

    @Override
    public Document prepareDocument() {
        Document document = new Document();
        Rectangle pageSize = new Rectangle(PageSize.A4);
        document.setPageSize(pageSize);
        document.setMargins(20, 20, 15, 15);

        return document;
    }

    @Override
    public void generateCv(Document document, CvContent cvContent) {
        createCvStructure(document);
        createCvBody(document, cvContent);
    }

    public void createCvStructure(Document document) {
        try {
            Rectangle rectangle = new Rectangle(0, 0, 194, 842);
            rectangle.setBackgroundColor(BaseColor.PINK);
            document.add(rectangle);

            rectangle = new Rectangle(197, 0, 595, 842);
            rectangle.setBackgroundColor(BaseColor.CYAN);
            document.add(rectangle);

            rectangle = new Rectangle(0, 680, 595, 842);
            rectangle.setBackgroundColor(BaseColor.WHITE);
            document.add(rectangle);

        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + " - createCvStructure() - ERROR: " + e);
        }
    }

    public void createCvBody(Document document, CvContent cvContent) {
        generateHeader(document, cvContent.getPersonalInfo());

        try {
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(105);
           table.setSpacingBefore(35);
            table.setWidths(new float[]{13, 2, 28});

            PdfPCell leftCell = generateLeftSection(document, cvContent);
            PdfPCell space = new PdfPCell(new Phrase(" "));

            PdfPCell rightCell = new PdfPCell(new Phrase("bbb"));

            table.addCell(leftCell);
            table.addCell(space);
            table.addCell(rightCell);


            document.add(table);
        } catch (DocumentException e) {

        }

    }

    public void generateHeader(Document document, PersonalInfo personalInfo) {

        try {
            Font font = Fonts.ANTONIO_BOLD;
            font.setColor(60, 93, 93);

            Paragraph p = PatternHelper.createSimpleParagraph(
                    (personalInfo.getName() + " " + personalInfo.getSurname()).toUpperCase(),
                    font,
                    44
            );
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            font = Fonts.ANTONIO_NORMAL;
            font.setColor(80, 124, 124);
            p = PatternHelper.createSimpleParagraph(
                    personalInfo.getPosition().toUpperCase(),
                    font,
                    24
            );
            p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
            p.setIndentationRight(150);
            p.setIndentationLeft(150);
            p.setSpacingAfter(15);
            document.add(p);

        } catch (DocumentException e) {
            LOGGER.error(CLASS_NAME + "- generateHeader() - " + "ERROR: " + e);
        }

    }

    public PdfPCell generateLeftSection(Document document, CvContent cvContent) {
        PdfPCell cell = new PdfPCell();

        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 5});
            table.addCell("img");
            table.addCell(cvContent.getPersonalInfo().getEmail());
            table.addCell("img");
            table.addCell("text");

            cell.addElement(table);


        } catch (DocumentException e) {

        }

        return cell;
    }

    public PdfPCell generateRightSection() {
        return new PdfPCell();
    }


}

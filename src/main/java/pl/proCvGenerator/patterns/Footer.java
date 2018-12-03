package pl.proCvGenerator.patterns;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class Footer extends PdfPageEventHelper {

    private Paragraph paragraph;

    public Footer(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        Rectangle rect = new Rectangle(580, 5, 5,50);
        ColumnText ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(rect);
        ct.addElement(paragraph);

        try {
            ct.go();
        } catch (DocumentException e){

        }
    }
}

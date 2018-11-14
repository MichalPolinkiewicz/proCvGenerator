package pl.proCvGenerator.patterns;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import pl.proCvGenerator.dto.CvContent;

public class Pattern3Impl implements Pattern {

    @Override
    public Document prepareDocument() {
        Document document = new Document();
        Rectangle pageSize = new Rectangle(PageSize.A4);
        document.setPageSize(pageSize);
        document.setMargins(15, 15, 15, 15);

        return document;
    }

    @Override
    public void generateCv(Document document, CvContent cvContent) {
        try {
            PdfPTable pdfPTable = new PdfPTable(2);
            PdfPCell leftCell = new PdfPCell(new Paragraph("1"));
            PdfPCell rightCell = new PdfPCell(new Paragraph("2"));

            leftCell.addElement(new Paragraph("33"));
            leftCell.addElement(new Paragraph("44"));
            leftCell.addElement(new Paragraph("55"));
            pdfPTable.addCell(leftCell);

            rightCell.addElement(new Paragraph("right"));
            rightCell.addElement(new Paragraph("fgh"));
            pdfPTable.addCell(rightCell);


            document.add(pdfPTable);
        } catch (DocumentException e){

        }


    }
}

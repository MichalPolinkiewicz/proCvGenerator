package pl.proCvGenerator.patterns;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import pl.proCvGenerator.dao.CvContent;
import pl.proCvGenerator.exception.PdfException;
import pl.proCvGenerator.exception.TooMuchCharsException;

public interface Pattern {

    void validate(CvContent cvContent) throws TooMuchCharsException;

    Document prepareDocument();

    void generateCv(Document document, CvContent cvContent) throws PdfException;

    Paragraph createClauseParagraph(String clause);
}

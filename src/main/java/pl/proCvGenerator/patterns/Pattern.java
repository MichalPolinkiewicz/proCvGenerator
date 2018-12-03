package pl.proCvGenerator.patterns;

import com.itextpdf.text.Document;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.exception.TooMuchCharsException;

public interface Pattern {

    Document prepareDocument();

    void generateCv(Document document, CvContent cvContent) throws TooMuchCharsException;
}

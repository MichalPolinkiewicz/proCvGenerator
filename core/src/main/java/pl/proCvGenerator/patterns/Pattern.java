package pl.proCvGenerator.patterns;

import com.itextpdf.text.Document;
import pl.proCvGenerator.dto.CvContent;

public interface Pattern {

    Document prepareDocument();

    void generateCv(Document document, CvContent cvContent);
}

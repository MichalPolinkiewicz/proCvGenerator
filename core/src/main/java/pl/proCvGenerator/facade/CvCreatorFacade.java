package pl.proCvGenerator.facade;

import pl.proCvGenerator.dto.User;
import pl.proCvGenerator.patterns.Pattern;
import pl.proCvGenerator.pdf.PdfCreator;

public class CvCreatorFacade {

    PdfCreator pdfCreator;

    public CvCreatorFacade(PdfCreator pdfCreator) {
        this.pdfCreator = pdfCreator;
    }

    public void createCv(User user, Pattern pattern) {
        pdfCreator.generate(user, pattern);
    }
}

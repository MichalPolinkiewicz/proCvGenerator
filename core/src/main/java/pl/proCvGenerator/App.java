package pl.proCvGenerator;

import pl.proCvGenerator.dto.User;
import pl.proCvGenerator.facade.CvCreatorFacade;
import pl.proCvGenerator.patterns.Pattern;
import pl.proCvGenerator.patterns.SimplePattern;
import pl.proCvGenerator.pdf.PdfCreator;

public class App {

    public static void main(String[] args) {

        CvCreatorFacade cvCreatorFacade = new CvCreatorFacade(new PdfCreator());
        User user = PdfCreator.createUser();
        Pattern pattern = new SimplePattern();

        cvCreatorFacade.createCv(user, pattern);
    }
}

package pl.proCvGenerator.facade;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import pl.proCvGenerator.converter.CvContentConverter;
import pl.proCvGenerator.dao.CvContent;
import pl.proCvGenerator.dao.User;
import pl.proCvGenerator.exception.PdfException;
import pl.proCvGenerator.exception.TooMuchCharsException;
import pl.proCvGenerator.exception.UserNotFoundException;
import pl.proCvGenerator.model.CvContentDto;
import pl.proCvGenerator.patterns.Footer;
import pl.proCvGenerator.patterns.Pattern;
import pl.proCvGenerator.pdf.PdfCreator;
import pl.proCvGenerator.service.DbService;
import pl.proCvGenerator.validator.CvContentValidator;
import pl.proCvGenerator.validator.PatternValidator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

public class WebFacade {

    @Autowired
    private CvContentConverter cvContentConverter;
    @Autowired
    private PatternValidator patternValidator;
    @Autowired
    private CvContentValidator cvContentValidator;
    @Autowired
    private DbService dbService;

    public void generatePdf(String patternId, CvContentDto cvContentDto, HttpServletResponse response) throws TooMuchCharsException, PdfException {
        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        Pattern pattern = patternValidator.choosePattern(patternId);
        Document document = pattern.prepareDocument();
        PdfWriter writer = null;

        try {
            PdfWriter.getInstance(document, baosPDF);

        } catch (DocumentException e) {

        }
       // "Wyrażam zgodę na przetwarzanie moich danych osobowych zawartych w dokumentach aplikacyjnych przez IFIRMA SA, z siedzibą we Wrocławiu, ul. Grabiszyńska 241B, 53-234 Wrocław, do celów niezbędnych do realizacji danego i przyszłych procesów rekrutacji, aż do momentu pisemnego odwołania zgody. Ponadto zgadzam się na udostępnienie moich danych osobowych do celów związanych z realizacją procesów rekrutacyjnych innym administratorom danych osobowych - klientom, dla których IFIRMA SA świadczy usługi rekrutacyjne. Mam świadomość, iż przysługuje mi prawo dostępu do swoich danych osobowych, ich sprostowania, przenoszenia i usunięcia lub ograniczenia przetwarzania, a także prawo do wniesienia skargi do organu nadzorczego.")));
        fillPattern(pattern, document, cvContentDto);

        try {
            response.setContentType("application/pdf");
            ServletOutputStream sos = response.getOutputStream();
            baosPDF.writeTo(sos);
            sos.flush();
            baosPDF.close();
        } catch (IOException e) {

        }
    }

//    private static void createClauseParagraph(){
//
//    }

    private void fillPattern(Pattern pattern, Document document, CvContentDto cvContentDto) throws TooMuchCharsException, PdfException {
        CvContent cvContent = cvContentConverter.convertToContent(cvContentDto);
        document.open();
        pattern.validate(cvContent);
        pattern.generateCv(document, cvContent);
        document.close();
    }

    public CvContentDto init() throws UserNotFoundException {
//        PersonalInfo personalInfo = PersonalInfo.builder().build();
//
////        CvContent cvContent = CvContent.builder()
////                .personalInfo(personalInfo)
////                .employments(new ArrayList<>())
////                .educationList(new ArrayList<>())
////                .hobbies(new ArrayList<>())
////                .skills(new ArrayList<>())
////                .build();
////
////        if auth != null{uzupelnia model danymi z bazy}
////        User user = dbService.getUserByLogin("login").orElseThrow(() -> new UserNotFoundException(""));
////        if (user.getCvContent() != null) {
////            cvContent = user.getCvContent();
////        }

        return cvContentConverter.convertToDto(cvContentValidator.validateCvContent(PdfCreator.createUser().getCvContent()));
    }

    @Transactional
    public void persist(CvContentDto cvContentDto) {
        //save content to db
        Optional user = dbService.getUserByLogin("login");

        if (user.isPresent()) {
            User validUser = (User) user.get();
            CvContent content = cvContentConverter.convertToContent(cvContentDto);
            if (!content.equals(validUser.getCvContent())) {
                dbService.deleteUser(validUser);
                validUser.setCvContent(content);
                dbService.saveUser(validUser);
            }
        }

    }
}


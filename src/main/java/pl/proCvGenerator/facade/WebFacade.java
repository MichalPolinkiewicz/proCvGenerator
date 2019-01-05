package pl.proCvGenerator.facade;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.proCvGenerator.converter.CvContentConverter;
import pl.proCvGenerator.dao.CvContent;
import pl.proCvGenerator.dao.PersonalInfo;
import pl.proCvGenerator.dao.User;
import pl.proCvGenerator.exception.PdfException;
import pl.proCvGenerator.exception.TooMuchCharsException;
import pl.proCvGenerator.exception.UserNotFoundException;
import pl.proCvGenerator.model.CvContentDto;
import pl.proCvGenerator.patterns.Pattern;
import pl.proCvGenerator.service.DbService;
import pl.proCvGenerator.validator.CvContentValidator;
import pl.proCvGenerator.validator.PatternValidator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class WebFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebFacade.class);

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

        try {
            PdfWriter.getInstance(document, baosPDF);
        } catch (DocumentException e) {

        }
        //writer.setPageEvent(new Footer(PatternImpl1Helper.createClauseParagraph(cvContentDto.getClause(), Fonts.UBUNTU_NORMAL)));
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

    private void fillPattern(Pattern pattern, Document document, CvContentDto cvContentDto) throws TooMuchCharsException, PdfException {
        CvContent cvContent = cvContentConverter.convertToContent(cvContentDto);
        document.open();
        pattern.validate(cvContent);
        pattern.generateCv(document, cvContent);
        document.close();
    }

    public CvContentDto init() throws UserNotFoundException {

        CvContent cvContent = new CvContent(new PersonalInfo(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        //if auth != null{uzupelnia model danymi z bazy}
        User user = dbService.getUserByLogin("janusz123").orElseThrow(() -> new UserNotFoundException(""));
        if (user.getCvContent() != null) {
            cvContent = user.getCvContent();
        }

        return cvContentConverter.convertToDto(cvContentValidator.validateCvContent(cvContent));
    }

    @Transactional
    public void persist(CvContentDto cvContentDto) {
        //save content to db
        User user = dbService.getUserByLogin("janusz123").get();
        CvContent content = cvContentConverter.convertToContent(cvContentDto);

        if (!content.equals(user.getCvContent())) {
            dbService.deleteUser(user);
            user.setCvContent(content);
            dbService.saveUser(user);
            LOGGER.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX saving()...");
        }
    }
}


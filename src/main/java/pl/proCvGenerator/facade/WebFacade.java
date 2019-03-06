package pl.proCvGenerator.facade;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.proCvGenerator.converter.CvContentConverter;
import pl.proCvGenerator.dao.CvContent;
import pl.proCvGenerator.dao.Education;
import pl.proCvGenerator.dao.Employment;
import pl.proCvGenerator.dao.PersonalInfo;
import pl.proCvGenerator.exception.PdfException;
import pl.proCvGenerator.exception.TooMuchCharsException;
import pl.proCvGenerator.model.CvContentDto;
import pl.proCvGenerator.patterns.Pattern;
import pl.proCvGenerator.validator.PatternValidator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WebFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebFacade.class);

    @Autowired
    private CvContentConverter cvContentConverter;
    @Autowired
    private PatternValidator patternValidator;

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
        String methodName = "fillPattern()...";
        log.info(methodName + "Pattern = {}", pattern);
        log.info(methodName + "CvContent = {}", cvContentDto);

        CvContent cvContent = cvContentConverter.convertToContent(cvContentDto);
        document.open();
        pattern.validate(cvContent);
        pattern.generateCv(document, cvContent);
        document.close();
    }

    public CvContentDto init() {
        //if auth != null{uzupelnia model danymi z bazy}
        //else

//        EducationListWrapperDto educationListWrapperDto = new EducationListWrapperDto();
//        educationListWrapperDto.addEducation(new Education());
//        EmploymentListWrapperDto employmentListWrapperDto = new EmploymentListWrapperDto();
//        employmentListWrapperDto.addEmployment(new Employment());
//        HobbiesListWrapperDto hobbies = new HobbiesListWrapperDto();
//        hobbies.addHobby("");
//        hobbies.addHobby("");
//
//        return new CvContentDto(new PersonalInfoDto(), educationListWrapperDto, employmentListWrapperDto, hobbies, "");

        CvContent cvContent = new CvContent();
        Education urBialystok = new Education();
        urBialystok.setSchoolName("Ut enim ad minima veniam");
        urBialystok.setSubject("quis nostrum");
        urBialystok.setDegree("exercitationem");
        urBialystok.setStartDate("2010");
        urBialystok.setEndDate("2014");

        Education loBialystok = new Education();
        loBialystok.setSchoolName("ullam corporis suscipit ");
        loBialystok.setSubject("laboriosam");
        loBialystok.setDegree(" nisi ut aliquid");
        loBialystok.setStartDate("2007");
        loBialystok.setEndDate("2010");
        List<Education> educationList = new ArrayList<>();
        educationList.add(loBialystok);
        educationList.add(urBialystok);
        cvContent.setEducationList(educationList);

        Employment xyz = new Employment();
        xyz.setCompany("ex ea commodi");
        xyz.setPosition("consequatur");
        xyz.setJobDescription("Quis autem vel eum iure reprehenderit");
        xyz.setStartDate("1988");
        xyz.setEndDate("2006");

        Employment zzz = new Employment();
        zzz.setCompany("qui in ea voluptate");
        zzz.setPosition("velit esse quam");
        zzz.setJobDescription("nihil molestiae consequatur, vel illum qui dolorem");
        zzz.setStartDate("2007");
        zzz.setEndDate("2016");

        Employment xxx = new Employment("eum fugiat quo voluptas", "nulla pariatur", "Sed ut perspiciatis unde omnis iste natus error sit voluptatem",
                "2016", "2019");

        List<Employment> employments = new ArrayList<>();
        employments.add(xyz);
        employments.add(zzz);
        employments.add(xxx);

        cvContent.setEmployments(employments);

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("John");
        personalInfo.setSurname("Smith");
        personalInfo.setCity("Los Angeles");
        personalInfo.setDescription("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo");
        personalInfo.setEmail("john.smith@gmail.com");
        personalInfo.setPage("www.johnsmith.com");
        personalInfo.setPhone("678-341-098");
        personalInfo.setPosition("Production Manager");

        cvContent.setPersonalInfo(personalInfo);
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Nemo enim");
        hobbies.add("ipsam voluptatem");
        hobbies.add("quia voluptas sit");
        hobbies.add("aspernatur aut");
        hobbies.add("et quasi architecto beatae");
        cvContent.setHobbies(hobbies);

        List<String> skills = new ArrayList<>();
        skills.add("odit aut fugit");
        skills.add("sed quia consequuntur magni");
        skills.add("dolores eos qui ratione");
        skills.add("voluptatem sequi nesciunt");
        skills.add("Neque porro quisquam est");
        skills.add("qui dolorem ipsum quia dolor sit amet");
        skills.add("dolore magnam aliquam quaerat");
        cvContent.setSkills(skills);

        return cvContentConverter.convertToDto(cvContent);
    }

    public void persist(CvContentDto cvContentDto) {
        CvContent content = cvContentConverter.convertToContent(cvContentDto);
        //save content to db
    }
}

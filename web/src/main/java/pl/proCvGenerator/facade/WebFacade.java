package pl.proCvGenerator.facade;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import org.springframework.beans.factory.annotation.Autowired;
import pl.proCvGenerator.converter.CvContentConverter;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;
import pl.proCvGenerator.model.CvContentDto;
import pl.proCvGenerator.patterns.Pattern;
import pl.proCvGenerator.validator.PatternValidator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebFacade {

    @Autowired
    private CvContentConverter cvContentConverter;
    @Autowired
    private PatternValidator patternValidator;

    public void generatePdf(String patternId, CvContentDto cvContentDto, HttpServletResponse response) {
        ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(baosPDF);
        Document document = new Document(new PdfDocument(pdfWriter), PageSize.A4);
        fillPattern(patternId, document, cvContentDto);
        document.close();
        response.setContentType("application/pdf");

        try {
            ServletOutputStream sos = response.getOutputStream();
            baosPDF.writeTo(sos);
            sos.flush();
            baosPDF.close();
        } catch (IOException e) {

        }
    }

    private void fillPattern(String patternId, Document document, CvContentDto cvContentDto) {
        Pattern pattern = patternValidator.choosePattern(patternId);
        CvContent cvContent = cvContentConverter.convertToContent(cvContentDto);
        pattern.generateHeader(document);
        pattern.generatePersonalInfoSection(document, cvContent.getPersonalInfo());
        pattern.generateEducationSection(document, cvContent.getEducationList());
        pattern.generateEmploymentSection(document, cvContent.getEmployments());
        pattern.generateSkillsSection(document, cvContent.getSkills());
        pattern.generateHobbySection(document, cvContent.getHobbies());
        pattern.generateClause(document, cvContent.getClause());
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
        urBialystok.setSchoolName("Uniwersytet Rolniczy w Białymstoku");
        urBialystok.setSubject("Rozród trzody chlewnej");
        urBialystok.setDegree("inżynier");
        urBialystok.setStartDate("2010");
        urBialystok.setEndDate("2014");
        Education loBialystok = new Education();
        loBialystok.setSchoolName("II LO w Białymstoku");
        loBialystok.setSubject("ogólny");
        loBialystok.setDegree("wyksztalcenie srednie");
        loBialystok.setStartDate("2007");
        loBialystok.setEndDate("2010");
        List<Education> educationList = new ArrayList<>();
        educationList.add(loBialystok);
        educationList.add(urBialystok);
        cvContent.setEducationList(educationList);
        Employment xyz = new Employment();
        xyz.setCompany("XYZ sp.z o.o");
        xyz.setPosition("murarz");
        xyz.setJobDescription("murowanie i picie");
        xyz.setStartDate("1988");
        xyz.setEndDate("2006");
        Employment zzz = new Employment();
        zzz.setCompany("zzz sp.z o.o");
        zzz.setPosition("murarz");
        zzz.setJobDescription("murowanie i picie");
        zzz.setStartDate("2007");
        zzz.setEndDate("2016");
        Employment xxx = new Employment("Firma x", "specjalista ds. bhp", "nadzór nad przestrzeganiem ogolnych zasad bhp",
                "2016", "nadal");

        List<Employment> employments = new ArrayList<>();
        employments.add(xyz);
        employments.add(zzz);
        employments.add(xxx);

        cvContent.setEmployments(employments);

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("Bogusław");
        personalInfo.setSurname("Norlak");
        personalInfo.setCity("Bialystok");
        personalInfo.setDescription("Jestem dobrym murarzem. Lubie pic i murowac");
        personalInfo.setEmail("zlotoreki69@gmail.com");
        personalInfo.setPhone("678-341-098");

        cvContent.setPersonalInfo(personalInfo);
        List<String> hobbies = new ArrayList<>();
        hobbies.add("mechanika");
        hobbies.add("sport");
        hobbies.add("muzyka");
        cvContent.setHobbies(hobbies);
        cvContent.setClause("Wyrażam zgodę na przetwarzanie danych osobowych przez firmę " +
                "......................................................................" +
                " w celu i zakresie niezbędnym w procesie rekrutacyjnym.");

        List<String> skills = new ArrayList<>();
        skills.add("umiejętność obsługi komputera");
        skills.add("prawo jazdy kat b");
        skills.add("znajomość języka angielskiego - poziom b2");
        skills.add("znajomsc jezyka niemieckigo - poziom a2");
        skills.add("znajomosc prawa pracy");
        cvContent.setSkills(skills);

        return cvContentConverter.convertToDto(cvContent);
    }

    public void persist() {

    }
}

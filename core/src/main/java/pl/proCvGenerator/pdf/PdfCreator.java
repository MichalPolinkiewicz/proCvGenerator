package pl.proCvGenerator.pdf;

import pl.proCvGenerator.dto.*;
import pl.proCvGenerator.patterns.Pattern;

import java.util.ArrayList;
import java.util.List;

public class PdfCreator {

    public static final String PATH_TO_FILE = "/home/michal/IdeaProjects/proCvGenerator/";

    public void generate(User user, Pattern pattern) {

//        try {
//            File file = new File(PATH_TO_FILE + "aa2" + "cv.pdf");
//            PdfWriter pdfWriter = new PdfWriter(file);
//            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
//            pdfDocument.setDefaultPageSize(PageSize.A4);
//
//            Document document = new Document(pdfDocument);
//
//            pattern.generateHeader(document);
//            pattern.generatePersonalInfoSection(document, user.getCvContent().getPersonalInfo());
//            pattern.generateEducationSection(document, user.getCvContent().getEducationList());
//            pattern.generateEmploymentSection(document, user.getCvContent().getEmployments());
//            pattern.generateHobbySection(document, user.getCvContent().getHobbies());
//            pattern.generateClause(document, user.getCvContent().getClause());
//
//
//            document.close();
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }

    }

    public static User createUser() {
        User user = new User();
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
        xyz.setEndDate("2018");

        Employment zzz = new Employment();
        zzz.setCompany("zzz sp.z o.o");
        zzz.setPosition("murarz");
        zzz.setJobDescription("murowanie i picie");
        zzz.setStartDate("2010");
        zzz.setEndDate("nadal");

        List<Employment> employments = new ArrayList<>();
        employments.add(xyz);
        employments.add(zzz);

        cvContent.setEmployments(employments);

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("Bogusław");
        personalInfo.setSurname("Norlak");
        personalInfo.setCity("Bialystok");
        personalInfo.setDescription("Jestem dobrym murarzem. Lubie pic i murowac");
        personalInfo.setEmail("zlotoreki69@gmail.com");
        personalInfo.setPhone("678-341-098");

        cvContent.setPersonalInfo(personalInfo);

        List<String> hobbyList = new ArrayList<>();
        hobbyList.add("muzyka");
        hobbyList.add("sport");
        hobbyList.add("turystyka");
        hobbyList.add("mocny alkohol");
        cvContent.setHobbies(hobbyList);
        user.setCvContent(cvContent);

        String clause = "Wyrażam zgodę na przetwarzanie danych osobowych przez firmę " +
                "......................................................................" +
                " w celu i zakresie niezbędnym w procesie rekrutacyjnym.";
        cvContent.setClause(clause);

        return user;
    }

}

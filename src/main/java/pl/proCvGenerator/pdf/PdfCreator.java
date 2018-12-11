package pl.proCvGenerator.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import pl.proCvGenerator.dto.*;
import pl.proCvGenerator.patterns.Pattern;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfCreator {

    public static final String PATH_TO_FILE = "/home/michal/IdeaProjects/proCvGenerator/";
    public static final String PATH_TO_FILE_WINDOWS = "C:/Users/MPl/IdeaProjects/proCvGenerator/";


    public void generate(Pattern pattern) {
        CvContent content = createUser().getCvContent();

        try {
            Document document = pattern.prepareDocument();
            PdfWriter.getInstance(document, new FileOutputStream(new File(PATH_TO_FILE + "11.pdf")));
            document.open();
            pattern.validate(content);
            pattern.generateCv(document, content);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User createUser() {
        User user = new User();
        CvContent cvContent = new CvContent();

        Education urBialystok = new Education();
        urBialystok.setSchoolName("PWSZ Legnica");
        urBialystok.setSubject("Zarządanie kadrami");
        urBialystok.setDegree("studia licencjackie");
        urBialystok.setStartDate("2011");
        urBialystok.setEndDate("2014");

        Education loBialystok = new Education();
        loBialystok.setSchoolName("I LO w Jaworze");
        loBialystok.setSubject("profil ogólny");
        loBialystok.setDegree("wykształcenie średnie");
        loBialystok.setStartDate("2007");
        loBialystok.setEndDate("2010");

        List<Education> educationList = new ArrayList<>();
        educationList.add(loBialystok);
        educationList.add(urBialystok);

        cvContent.setEducationList(educationList);

        Employment xyz = new Employment();
        xyz.setCompany("Empirica S.A");
        xyz.setPosition("Junior Java Developer");
        xyz.setJobDescription("Praca przy utrzymaniu i rozwoju aplikacji biznesowych dla jednego z największych banków w Polsce. Implementacja zmian zgodnie ze specyfikacją, przeprowadzanie testów jednostkowych, tworzenie dokumentacji, code-review. Główne technologie: Java, Spring, Spock, Struts, Jenkins, WebSphere, DB2");
        xyz.setStartDate("2018-03");
        xyz.setEndDate("nadal");

        Employment zzz = new Employment();
        zzz.setCompany("Trenkwalder & Partner sp. z o.o");
        zzz.setPosition("Specjalista ds. kadr");
        zzz.setJobDescription("Kompleksowa obsługa płacowo-kadrowa (szkolenia BHP, wynagrodzenia, urlopy) pracowników, udział w procesach rekrutacyjnych na stanowiska różnego szczebla, przeprowadzanie szkoleń z zakresu użytkowania programu TETA-2000");
        zzz.setStartDate("2014-10");
        zzz.setEndDate("2016-10");

        Employment aaa = new Employment();
        aaa.setCompany("Biznes Partner sp. z o.o");
        aaa.setPosition("Koordynator projektów");
        aaa.setJobDescription("Koordynacja w zakresie obsługi płacowej projektów oraz rozliczania pracowników, nadzór nad dokumentacją");
        aaa.setStartDate("2016-11");
        aaa.setEndDate("2018-03");


        List<Employment> employments = new ArrayList<>();
        employments.add(xyz);
        employments.add(aaa);
        employments.add(zzz);

        cvContent.setEmployments(employments);

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("Michał");
        personalInfo.setSurname("Polinkiewicz");
        personalInfo.setCity("Wrocław"); //max 19
        String text = "Programowaniem na poważnie zajmuję się od połowy 2017 roku. Moimi wiodącymi technologiami są Java i Spring, aczkolwiek nie obcy jest mi również front-end z JavaScript i HTML. " +
                "Moje wcześniejsze doświadczenia zawodowe są związane z szeroko rozumianym HR w rolach specjalisty kadrowego oraz koordynatora projektów.";
        personalInfo.setDescription(text);
        personalInfo.setEmail("michal.polinkiewicz@gmail.com"); //max 30 znakow
        personalInfo.setPhone("570-740-169"); //max 9
        personalInfo.setPosition("Java Developer");
        personalInfo.setPage("github.com/MichalPolinkiewicz");

        cvContent.setPersonalInfo(personalInfo);

        List<String> hobbyList = new ArrayList<>();
        hobbyList.add("programowanie");
        hobbyList.add("sport: boks, żużel, piłka nożna");
        hobbyList.add("psy");
        hobbyList.add("astronomia");
        hobbyList.add("muzyka");


        cvContent.setHobbies(hobbyList);
        user.setCvContent(cvContent);

        List<String> skills = new ArrayList<>();
        skills.add("programowanie w języku Java");
        skills.add("znajmość frameworka Spring oraz jego modułów (Spring MVC, Spring JPA, Spring Security oraz SpringBoot)");
        skills.add("testowanie oprogramowania z wykorzystaniem Spock Framework, języka Groovy oraz JUnit");
        skills.add("korzystanie z narzędzi ORM takich jak Hibernate oraz znajomość języka SQL");
        skills.add("praca z narzędziami Maven i Gradle");
        skills.add("znajomość wzorców projektowych");
        skills.add("znajomość HTML i JavaScript");
        skills.add("praca z system kontroli wersji GIT");
        skills.add("korzystanie z JIRA");
        skills.add("praca w metodologii SCRUM");
        skills.add("obsługa programów: InteliJ Idea, Eclipse, STS, Visual Studio Code");
        skills.add("znajomość języka angielskiego - poziom B1");

        cvContent.setSkills(skills);
        return user;
    }

}

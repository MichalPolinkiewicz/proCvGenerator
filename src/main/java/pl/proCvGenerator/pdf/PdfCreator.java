package pl.proCvGenerator.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import pl.proCvGenerator.dao.*;
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
        CvContent cvContent = CvContent.builder().build();

        User user = User.builder()
                .login("login")
                .password("pass")
                .build();

        Education edu1 = Education.builder()
                .schoolName("PWSZ Legnica")
                .subject("Zarządanie kadrami")
                .degree("studia licencjackie")
                .startDate("2011")
                .endDate("2014")
                .build();

        Education edu2 = Education.builder()
                .schoolName("I LO w Jaworze")
                .subject("I LO w Jaworze")
                .degree("wykształcenie średnie")
                .startDate("2007")
                .endDate("2010")
                .build();

        List<Education> educationList = new ArrayList<>();
        educationList.add(edu2);
        educationList.add(edu1);

        cvContent.setEducationList(educationList);

        Employment emp1 = Employment.builder()
                .company("Empirica S.A")
                .position("Junior Java Developer")
                .jobDescription("Praca przy utrzymaniu i rozwoju aplikacji biznesowych dla jednego z największych banków w Polsce. Implementacja zmian zgodnie ze specyfikacją, przeprowadzanie testów jednostkowych, tworzenie dokumentacji, code-review. Główne technologie: Java, Spring, Spock, Struts, Jenkins, WebSphere, DB2")
                .startDate("2018-03")
                .endDate("nadal")
                .build();


        Employment emp2 = Employment.builder()
                .company("Trenkwalder & Partner sp. z o.o")
                .position("Specjalista ds. kadr")
                .jobDescription("Kompleksowa obsługa płacowo-kadrowa (szkolenia BHP, wynagrodzenia, urlopy) pracowników, udział w procesach rekrutacyjnych na stanowiska różnego szczebla, przeprowadzanie szkoleń z zakresu użytkowania programu TETA-2000")
                .startDate("2014-10")
                .endDate("2016-10")
                .build();


        Employment emp3 = Employment.builder()
                .company("Biznes Partner sp. z o.o")
                .position("Koordynator projektów")
                .jobDescription("Koordynacja w zakresie obsługi płacowej projektów oraz rozliczania pracowników, nadzór nad dokumentacją")
                .startDate("2016-11")
                .endDate("2018-03")
                .build();

        List<Employment> employments = new ArrayList<>();
        employments.add(emp1);
        employments.add(emp3);
        employments.add(emp2);

        cvContent.setEmployments(employments);

        PersonalInfo personalInfo = PersonalInfo.builder()
                .name("Michał")
                .surname("Polinkiewicz")
                .city("Wrocław")
                .description("Programowaniem na poważnie zajmuję się od połowy 2017 roku. Moimi wiodącymi technologiami są Java i Spring, aczkolwiek nie obcy jest mi również front-end z JavaScript i HTML. " +
                        "Moje wcześniejsze doświadczenia zawodowe są związane z szeroko rozumianym HR w rolach specjalisty kadrowego oraz koordynatora projektów.")
                .email("michal.polinkiewicz@gmail.com")
                .phone("570-740-169")
                .page("github.com/MichalPolinkiewicz")
                .build();

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

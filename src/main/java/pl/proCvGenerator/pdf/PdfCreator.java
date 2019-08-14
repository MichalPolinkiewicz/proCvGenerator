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
                .startDate("2010")
                .endDate("2013")
                .build();

        Education edu2 = Education.builder()
                .schoolName("I LO w Jaworze")
                .subject("ogólny")
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
                .position("MŁODSZY PROGRAMISTA JAVA")
                .jobDescription("Utrzymywanie oraz tworzenie oprogramowania dla Klienta" +
                        "będącego jednym z największych dostawcą usług leasingowych w Polsce. Praca" +
                        "zarówno z warstwą serwerową aplikacji jak i frontową. Tworzenie dokumentacji," +
                        "code review. Główne technologie: Java(1.5 – 1.8), Spring, Spock, Hibernate," +
                        "Thymeleaf, JSP, Struts, DB2")
                .startDate("2018-03")
                .endDate("nadal")
                .build();


        Employment emp2 = Employment.builder()
                .company("Trenkwalder & Partner sp. z o.o")
                .position("Specjalista ds. kadr")
                .jobDescription("Kompleksowa obsługa płacowo-kadrowa (badania, szkolenia BHP, wynagrodzenia, urlopy) pracowników, udział w procesach rekrutacyjnych na stanowiska różnego szczebla, przeprowadzanie szkoleń z zakresu użytkowania programu TETA-2000")
                .startDate("2014-10")
                .endDate("2016-10")
                .build();


        Employment emp3 = Employment.builder()
                .company("Biznes Partner sp. z o.o")
                .position("Koordynator projektów")
                .jobDescription("Koordynacja procesów kadrowo-płacowych w firmie z" +
                        "branży HR, utrzymywanie kontaktów z Klientami, nadzorowanie i rekrutacja" +
                        "pracowników, wykonywanie rozliczeń, kontakty z UW i UP")
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
                .description("Programowaniem zajmuję się " +
                        "zawodowo od 1.5 roku, wcześniej " +
                        "pracowałem w branży HR w " +
                        "charakterze specjalisty ds. " +
                        "personalnych oraz koordynatora " +
                        "projektów. Poszukuję pracy jako " +
                        "programista, najchętniej Java lub " +
                        "Go która umożliwi mi rozwijanie " +
                        "posiadanych już umiejętności oraz " +
                        "pozwoli zdobyć nowe.")
                .email("michal.polinkiewicz@gmail.com")
                .phone("570-740-169")
                .page("github.com/MichalPolinkiewicz")
                .position("SOFTWARE DEVELOPER")
                .build();

        cvContent.setPersonalInfo(personalInfo);

        List<String> hobbyList = new ArrayList<>();
        hobbyList.add("programowanie");
        hobbyList.add("sport: boks, piłka nożna");
        hobbyList.add("astronomia");
        hobbyList.add("muzyka");

        cvContent.setHobbies(hobbyList);
        user.setCvContent(cvContent);

        List<String> skills = new ArrayList<>();
        skills.add("znajomość języka Java i frameworków: Spring, Spring Boot, Hibernate");
        skills.add("testowanie kodu Java z wykorzystaniem m.in. JUnit i Spock Framework");
        skills.add("znajomość języka Go oraz frameworków: Gin, GORM");
        skills.add("front-end: JS, HTML, CSS");
        skills.add("znajomość języka SQL oraz baz danych: MySQL, MSSQL");
        skills.add("NOSQL: Redis, MongoDB");
        skills.add("praca z serwerami Tomcat i WAS");
        skills.add("inne : Docker, Jenkins, Sonar, Maven, Gradle, Git, Jira");
        skills.add("języka angielski: B1");

        cvContent.setSkills(skills);

        return user;
    }

}

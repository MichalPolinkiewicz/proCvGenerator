package pl.proCvGenerator.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import pl.proCvGenerator.dto.*;
import pl.proCvGenerator.patterns.Pattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfCreator {

    public static final String PATH_TO_FILE = "/home/michal/IdeaProjects/proCvGenerator/";
    public static final String PATH_TO_FILE_WINDOWS = "C:/Users/MPl/IdeaProjects/proCvGenerator/";


    public void generate(Pattern pattern) {

        try {
            Document document = pattern.prepareDocument();
            PdfWriter.getInstance(document, new FileOutputStream(new File(PATH_TO_FILE + "11.pdf")));
            document.open();
            pattern.generateCv(document, createUser().getCvContent());
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        xyz.setJobDescription("Praca przy budowie nowoczesnej fabryki mokrej karmy. Nadzór nad pracownikami, wykonywanie prac związanych z wykończeniem wnętrz, spozywanie alkoholu w duzych ilościach. Kradzież materiałów budowlanych i sprzedaż na czarnym rynku");
        xyz.setStartDate("1988");
        xyz.setEndDate("2018");

        Employment zzz = new Employment();
        zzz.setCompany("zzz sp.z o.o");
        zzz.setPosition("murarz");
        zzz.setJobDescription("Praca przy budowie nowoczesnej fabryki mokrej karmy. Nadzór nad pracownikami, wykonywanie prac związanych z wykończeniem wnętrz, spozywanie alkoholu w duzych ilościach. Kradzież materiałów budowlanych i sprzedaż na czarnym rynku");
        zzz.setStartDate("2010");
        zzz.setEndDate("nadal");

        List<Employment> employments = new ArrayList<>();
        employments.add(xyz);
        employments.add(zzz);

        cvContent.setEmployments(employments);

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("Bogusław");
        personalInfo.setSurname("Norlak");
        personalInfo.setCity("Bialystok i okolice"); //max 19
        //System.out.println(personalInfo.getCity().length());
        personalInfo.setDescription("Jestem dobrym murarzem. Lubie pic i murowac. Robie to od dziecka i jestem niesamowitym fachowcem. Ponadto jestem Andrzejem i bogdanem. Jestem dobrym murarzem. Lubie pic i murowac. Robie to od dziecka i jestem niesamowitym fachowcem. Ponadto jestem Andrzejem i bogdanem.");
        personalInfo.setEmail("michal.polinkiewicz@gmail.com"); //max 30 znakow
        //System.out.println(personalInfo.getEmail().length());
        personalInfo.setPhone("678341098"); //max 9
        cvContent.setPersonalInfo(personalInfo);

        List<String> hobbyList = new ArrayList<>();
        hobbyList.add("muzyka");
        hobbyList.add("sport");
        hobbyList.add("turystyka");
        hobbyList.add("mocny alkohol");
        hobbyList.add("inne");
        hobbyList.add("inne");
        hobbyList.add("inne");


        cvContent.setHobbies(hobbyList);
        user.setCvContent(cvContent);

        List<String> skills = new ArrayList<>();
        skills.add("prawo jazdy kat. B");
        skills.add("umiejętność obsługi komputera");
        skills.add("znajomość języka angielskiego - poziom B2");
        skills.add("znajomość języka rosyjskiego - poziom B2");
        skills.add("umiejetnośc pracy w grupie");


        cvContent.setSkills(skills);

        String clause = "Wyrażam zgodę na przetwarzanie danych osobowych przez firmę " +
                "......................................................................" +
                " w celu i zakresie niezbędnym w procesie rekrutacyjnym.";
        cvContent.setClause(clause);
        System.out.println(personalInfo.getDescription().length());
        return user;
    }

}

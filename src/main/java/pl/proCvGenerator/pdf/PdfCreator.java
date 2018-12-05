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
        urBialystok.setSchoolName("Uniwersytet Rolniczy w Białym stokuw i ww okolicach");
        urBialystok.setSubject("Rozród trzody chlewnej parx xxx xxxxx");
        urBialystok.setDegree("inżynier i magister");
        urBialystok.setStartDate("2010");
        urBialystok.setEndDate("2014");

        Education loBialystok = new Education();
        loBialystok.setSchoolName("II LO w Białymstoku");
        loBialystok.setSubject("ogólny");
        loBialystok.setDegree("wyksztalcenie srednie xxxxxx xxx xx xxx xx xxx srednie xxx xxx xxxxx xx xxx xxx xx");
        loBialystok.setStartDate("2007");
        loBialystok.setEndDate("2010");

        List<Education> educationList = new ArrayList<>();
        educationList.add(loBialystok);
        educationList.add(urBialystok);

        cvContent.setEducationList(educationList);

//        Employment xyz = new Employment();
//        xyz.setCompany("XYZ sp.z o.o");
//        xyz.setPosition("murarz");
//        xyz.setJobDescription("Praca przy xxxx xxx xxxx xxxx xxx xxxx xx xxxxxxxx xxxxxxxx xxxx xxxxx xxx x budowie nowoczesnej fabryki mokrej karmy. Nadzór nad pracownikami, wykonywanie prac związanych z wykończeniem wnętrz, spozywanie alkoholu w duzych ilościach. Kradzież materiałów budowlanych i sprzedaż na czarnym rynku xxxxxxxxxxx xxxxxxx");
//        xyz.setStartDate("1988");
//        xyz.setEndDate("2018");

        Employment zzz = new Employment();
        zzz.setCompany("zzz sp.z o.o");
        zzz.setPosition("murarz");
        zzz.setJobDescription("Praca przy budowie nowoczesnej fabryki mokrej karmy. Nadzór nad pracownikami, wykonywanie prac związanych z wykończeniem wnętrz, spozywanie alkoholu w duzych ilościach. Kradzież materiałów budowlanych i sprzedaż na czarnym rynku");
        zzz.setStartDate("2010");
        zzz.setEndDate("2017");

        Employment aaa = new Employment();
        aaa.setCompany("zzz sp.z o.o");
        aaa.setPosition("murarz");
        aaa.setJobDescription("Nadzór nad pracownikami. xx x xxxxx xxx xx xx xx xxxx xxx xx xx xx xx xx");
        aaa.setStartDate("2000");
        aaa.setEndDate("2003");


        List<Employment> employments = new ArrayList<>();
       // employments.add(xyz);
        employments.add(aaa);
        employments.add(zzz);

        cvContent.setEmployments(employments);

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("Bogusław");
        personalInfo.setSurname("Norlak");
        personalInfo.setCity("Jawor"); //max 19
        String text = "abcde fgh ijkl opr stuxy abc defgh, abcde fgh ijkl opr stuxy abc defgh, abcde fgh ijkl opr stuxy abc defgh";
        personalInfo.setDescription(text);
        personalInfo.setEmail("ada.nowak@wp.pl"); //max 30 znakow
        personalInfo.setPhone("657-908-482"); //max 9
        personalInfo.setPosition("stróż i alkoholik");
        personalInfo.setPage("github.com/SnorliTheDog");

        cvContent.setPersonalInfo(personalInfo);

        List<String> hobbyList = new ArrayList<>();
        for (int i = 0; i < 11; i++){
            hobbyList.add("abcde fgh ijkl opr stuxy abc efg");
        }

        cvContent.setHobbies(hobbyList);
        user.setCvContent(cvContent);

        List<String> skills = new ArrayList<>();
        skills.add("prawo jazdy kat. B");
        String a = "umiejętność obsługi komp utera xxxx xxxx xxx xxxxx xxxx xxxx xx xxx xxx xx xxx xx";
        System.out.println(a.length());
        skills.add(a);
        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxx xxx xxxxxxxx xxxx xxxxxxxxxxxx");
        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
//        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
//        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
//        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
//        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
//        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");


        cvContent.setSkills(skills);

        String clause = "Wyrażam zgodę na przetwarzanie danych osobowych przez firmę " +
                "......................................................................" +
                " w celu i zakresie niezbędnym w procesie rekrutacyjnym.";
        cvContent.setClause(clause);
        return user;
    }

}

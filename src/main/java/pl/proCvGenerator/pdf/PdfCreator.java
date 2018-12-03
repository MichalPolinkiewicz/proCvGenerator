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

        try {
            Document document = pattern.prepareDocument();
            PdfWriter.getInstance(document, new FileOutputStream(new File(PATH_TO_FILE_WINDOWS + "11.pdf")));
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
        urBialystok.setSchoolName("Uniwersytet Rolniczy w Białymstoku i okolicach");
        urBialystok.setSubject("Rozród trzody chlewnej");
        urBialystok.setDegree("inżynier i magister");
        urBialystok.setStartDate("2010");
        urBialystok.setEndDate("2014");

        Education loBialystok = new Education();
        loBialystok.setSchoolName("II LO w Białymstoku");
        loBialystok.setSubject("ogólny");
        loBialystok.setDegree("wyksztalcenie srednie xxxxxxxxx xxxxxxxx");
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
        zzz.setJobDescription("Praca przy budowien nnvnvnb nowoczesnej fabryki mokrej karmy. Nadzór nad pracownikami, wykonywanie prac związanych z wykończeniem wnętrz, spozywanie alkoholu w duzych ilościach. Kradzież materiałów budowlanych i sprzedaż na czarnym rynku");
        zzz.setStartDate("2010");
        zzz.setEndDate("2017");

        Employment aaa = new Employment();
        aaa.setCompany("zzz sp.z o.o");
        aaa.setPosition("murarz");
        aaa.setJobDescription(" Nadzór nad pracownikami. xxxxxxxxx  xxxxxxxxxxxx");
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
        String text = "x xxxxxxxx xxxxxxxx xxxxxxxx xxxxxxxx xxxxxxxx xxxxxx xxxxxx xxxxx";
        personalInfo.setDescription(text);
        personalInfo.setEmail("ada.nowak@wp.pl"); //max 30 znakow
        personalInfo.setPhone("657-908-482"); //max 9
        personalInfo.setPosition("stróż i alkoholik");
        personalInfo.setPage("github.com/SnorliTheDog");

        cvContent.setPersonalInfo(personalInfo);

        List<String> hobbyList = new ArrayList<>();
        hobbyList.add("muzyka xxxxxxxxx xxxxxxxxxxxx ");
        hobbyList.add("sport xxxxxxxxxx xxxxxxxxxxx");
        hobbyList.add("inne xxxxxxxxxxxxxx xxxxxxxxx");
        // hobbyList.add("inne xxxxxxxxxxxxxx xxxxxxxxx");


        cvContent.setHobbies(hobbyList);
        user.setCvContent(cvContent);

        List<String> skills = new ArrayList<>();
        skills.add("prawo jazdy kat. B");
        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
//        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
//        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
//        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx xxxxxxxxxxxxxxxx");
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

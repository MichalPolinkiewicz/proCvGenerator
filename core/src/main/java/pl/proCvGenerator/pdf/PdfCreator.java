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
        xyz.setJobDescription("Praca przy xxxx xxx xxxx xxxx xxx xxxx xx xxxxxxxx xxxxxxxx xxxx xxxxx xxx x budowie nowoczesnej fabryki mokrej karmy. Nadzór nad pracownikami, wykonywanie prac związanych z wykończeniem wnętrz, spozywanie alkoholu w duzych ilościach. Kradzież materiałów budowlanych i sprzedaż na czarnym rynku xxxxxxxxxxx xxxxxxx");
        xyz.setStartDate("1988");
        xyz.setEndDate("2018");

        Employment zzz = new Employment();
        zzz.setCompany("zzz sp.z o.o");
        zzz.setPosition("murarz");
        zzz.setJobDescription("Praca przy budowien nnvnvnb nowoczesnej fabryki mokrej karmy. Nadzór nad pracownikami, wykonywanie prac związanych z wykończeniem wnętrz, spozywanie alkoholu w duzych ilościach. Kradzież materiałów budowlanych i sprzedaż na czarnym rynku");
        zzz.setStartDate("2010");
        zzz.setEndDate("2017");

        List<Employment> employments = new ArrayList<>();
        employments.add(xyz);
        employments.add(zzz);

        cvContent.setEmployments(employments);

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("Bogusław");
        personalInfo.setSurname("Norlak");
        personalInfo.setCity("Jawor"); //max 19
        personalInfo.setDescription("Jestem dobrym murarzem. xxxxxxxxxx. Lubie pic. Robie to od dziecka i jestem niesamowitym fachowcem. Ponadto jestem Andrzejem i bogdanem. Jestem dobrym murarzem. Lubie pic i murowac. Robie to od dziecka i jestem niesamowitym fachowcem. Ponadto jestem Andrzejem i bogdanem.");
        personalInfo.setEmail("jan.kowalski@gmail.com"); //max 30 znakow
        personalInfo.setPhone("645-093-582"); //max 9
        cvContent.setPersonalInfo(personalInfo);

        List<String> hobbyList = new ArrayList<>();
        hobbyList.add("muzyka");
        hobbyList.add("sport");
        hobbyList.add("turystyka");
        hobbyList.add("mocny alkohol tttt");
        hobbyList.add("inne");
        hobbyList.add("inne");


        cvContent.setHobbies(hobbyList);
        user.setCvContent(cvContent);

        List<String> skills = new ArrayList<>();
//        skills.add("prawo jazdy kat. B");
//        skills.add("umiejętność obsługi komputera xxxx xxxx xxxxxxxx xxxxxxxx");
//        skills.add("umiejętność obsługi komputera xxxxxxxx xxx");
//        skills.add("umiejętność obsługi komputera xxxxxxxx xxx");
//        skills.add("umiejętność obsługi komputera xxxxxxxx xxx");
//        skills.add("umiejętność obsługi komputera xxxxxxxxxx xxxxxxxxx");
//        skills.add("umiejętność obsługi komputera xxxxxxxx xxxx");
//        skills.add("umiejętność obsługi komputera xxx xxxxx xxxxxx xxxxxx");
//        skills.add("umiejętność obsługi komputera xxx xxxxx xxxxxx xxxxxx");
//        skills.add("umiejętność obsługi komputera xxx xxxxx xxxxxx xxxxxx");

        for (int i = 0; i < 13 ; i++){
            skills.add("skill " + i);
        }


        cvContent.setSkills(skills);

        String clause = "Wyrażam zgodę na przetwarzanie danych osobowych przez firmę " +
                "......................................................................" +
                " w celu i zakresie niezbędnym w procesie rekrutacyjnym.";
        cvContent.setClause(clause);
        return user;
    }

}

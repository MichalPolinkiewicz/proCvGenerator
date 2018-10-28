package pl.proCvGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.proCvGenerator.converter.CvContentConverter;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.dto.PersonalInfo;
import pl.proCvGenerator.model.CvContentDto;
import pl.proCvGenerator.model.EducationListWrapperDto;
import pl.proCvGenerator.model.EmploymentListWrapperDto;
import pl.proCvGenerator.model.PersonalInfoDto;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CvContentConverter cvContentConverter;

    @GetMapping(path = "/")
    public String index() {

        return "index";
    }

    @GetMapping(path = "/pattern")
    public String pattern(@RequestParam(name = "pattern") String pattern, Model model) {
        System.out.println("Pattern from index.html= " + pattern);
        model.addAttribute("pattern", pattern);

        return "content";
    }

    @PostMapping(path = "/generate")
    public String generate(String pattern, CvContentDto cvContentDto) {
        System.out.println(cvContentDto.getPersonalInfoDto().getName());
        System.out.println(cvContentDto.getEducationListWrapperDto().getEducationList().get(0).getSchoolName());
        System.out.println(cvContentDto.getEmploymentListWrapperDto().getEmploymentList().get(0).getCompany());
        System.out.println("Generate: " + pattern);

        return "ok";
    }

    @ModelAttribute(name = "cvContentDto")
    public CvContentDto init() {
//        CvContent cvContent = new CvContent();
//        Education urBialystok = new Education();
//        urBialystok.setSchoolName("Uniwersytet Rolniczy w Białymstoku");
//        urBialystok.setSubject("Rozród trzody chlewnej");
//        urBialystok.setDegree("inżynier");
//        urBialystok.setStartDate("2010");
//        urBialystok.setEndDate("2014");
//        Education loBialystok = new Education();
//        loBialystok.setSchoolName("II LO w Białymstoku");
//        loBialystok.setSubject("ogólny");
//        loBialystok.setDegree("wyksztalcenie srednie");
//        loBialystok.setStartDate("2007");
//        loBialystok.setEndDate("2010");
//        List<Education> educationList = new ArrayList<>();
//        educationList.add(loBialystok);
//        educationList.add(urBialystok);
//        cvContent.setEducationList(educationList);
//        Employment xyz = new Employment();
//        xyz.setCompany("XYZ sp.z o.o");
//        xyz.setPosition("murarz");
//        xyz.setJobDescription("murowanie i picie");
//        xyz.setStartDate("1988");
//        xyz.setEndDate("2018");
//        Employment zzz = new Employment();
//        zzz.setCompany("zzz sp.z o.o");
//        zzz.setPosition("murarz");
//        zzz.setJobDescription("murowanie i picie");
//        zzz.setStartDate("2010");
//        zzz.setEndDate("nadal");
//
//        List<Employment> employments = new ArrayList<>();
//        employments.add(xyz);
//        employments.add(zzz);
//
//        cvContent.setEmployments(employments);
//
//        PersonalInfo personalInfo = new PersonalInfo();
//        personalInfo.setName("Bogusław");
//        personalInfo.setSurname("Norlak");
//        personalInfo.setCity("Bialystok");
//        personalInfo.setDescription("Jestem dobrym murarzem. Lubie pic i murowac");
//        personalInfo.setEmail("zlotoreki69@gmail.com");
//        personalInfo.setPhone("678-341-098");
//
//        cvContent.setPersonalInfo(personalInfo);
//
//        return cvContentConverter.convertToDto(cvContent);
//

        EducationListWrapperDto educationListWrapperDto = new EducationListWrapperDto();
        List<Education> educations = new ArrayList<>();
        educations.add(new Education());
        educationListWrapperDto.setEducationList(educations);

        EmploymentListWrapperDto employmentListWrapperDto = new EmploymentListWrapperDto();
        List<Employment> employmentsList = new ArrayList<>();
        employmentsList.add(new Employment("", "", "", "", ""));
        employmentListWrapperDto.setEmploymentList(employmentsList);

        return new CvContentDto(new PersonalInfoDto(), educationListWrapperDto, employmentListWrapperDto);
    }
}

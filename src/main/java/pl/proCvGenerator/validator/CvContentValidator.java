package pl.proCvGenerator.validator;

import pl.proCvGenerator.dao.CvContent;
import pl.proCvGenerator.dao.Education;
import pl.proCvGenerator.dao.Employment;
import pl.proCvGenerator.dao.PersonalInfo;

import java.util.List;

public class CvContentValidator {

    public CvContent validateCvContent(CvContent cvContent) {
        return CvContent.builder()
                .personalInfo(validatePersonalInfo(cvContent.getPersonalInfo()))
                .educationList(validateEducationList(cvContent.getEducationList()))
                .employments(validateEmploymentList(cvContent.getEmployments()))
                .skills(validateStringList(cvContent.getSkills()))
                .hobbies(validateStringList(cvContent.getHobbies()))
                .build();
    }

    private PersonalInfo validatePersonalInfo(PersonalInfo personalInfo) {
        PersonalInfo personal = personalInfo;

        if (personal.getName() == null) {
            personal.setName("");
        }
        if (personal.getSurname() == null) {
            personal.setSurname("");
        }
        if (personal.getEmail() == null) {
            personal.setEmail("");
        }
        if (personal.getPhone() == null) {
            personal.setPhone("");
        }
        if (personal.getCity() == null) {
            personal.setCity("");
        }
        return personal;
    }

    private List<Education> validateEducationList(List<Education> educationList) {
        if (educationList.size() == 0) {
            educationList.add(Education.builder().build());
        }
        return educationList;
    }

    private List<Employment> validateEmploymentList(List<Employment> employmentList) {
        if (employmentList.size() == 0) {
            employmentList.add(Employment.builder().build());
        }
        return employmentList;
    }

    private List<String> validateStringList(List<String> strings) {
        if (strings.size() == 0) {
            strings.add("");
        }
        return strings;
    }
}

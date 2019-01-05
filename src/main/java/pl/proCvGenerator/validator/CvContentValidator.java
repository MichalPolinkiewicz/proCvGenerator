package pl.proCvGenerator.validator;

import pl.proCvGenerator.dao.CvContent;
import pl.proCvGenerator.dao.Education;
import pl.proCvGenerator.dao.Employment;
import pl.proCvGenerator.dao.PersonalInfo;

import java.util.List;

public class CvContentValidator {

    public CvContent validateCvContent(CvContent cvContent) {
        return new CvContent(
                validatePersonalInfo(cvContent.getPersonalInfo()),
                validateEducationList(cvContent.getEducationList()),
                validateEmploymentList(cvContent.getEmployments()),
                validateStringList(cvContent.getSkills()),
                validateStringList(cvContent.getHobbies())
        );
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
        List<Education> educations = educationList;
        if (educations.size() == 0) {
            educations.add(new Education("", "", "", "", ""));
        }
        return educations;
    }

    private List<Employment> validateEmploymentList(List<Employment> employmentList) {
        List<Employment> employments = employmentList;
        if (employments.size() == 0) {
            employments.add(new Employment("", "", "", "", ""));
        }
        return employments;
    }

    private List<String> validateStringList(List<String> stringList) {
        List<String> strings = stringList;
        if (strings.size() == 0) {
            strings.add("");
        }
        return strings;
    }
}

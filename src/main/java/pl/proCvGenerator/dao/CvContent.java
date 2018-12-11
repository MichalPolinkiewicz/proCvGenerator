package pl.proCvGenerator.dao;

import java.util.List;

public class CvContent {

    private long id;
    private PersonalInfo personalInfo;
    private List<Education> educationList;
    private List<Employment> employments;
    private List<String> skills;
    private List<String> hobbies;

    public CvContent() {
    }

    public CvContent(PersonalInfo personalInfo, List<Education> educationList, List<Employment> employments, List<String> skills, List<String> hobbies) {
        this.personalInfo = personalInfo;
        this.educationList = educationList;
        this.employments = employments;
        this.skills = skills;
        this.hobbies = hobbies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }

    public List<Employment> getEmployments() {
        return employments;
    }

    public void setEmployments(List<Employment> employments) {
        this.employments = employments;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }
}

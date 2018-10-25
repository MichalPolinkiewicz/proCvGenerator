package pl.proCvGenerator.dto;

import java.util.List;

public class CvContent {

    private long id;
    private PersonalInfo personalInfo;
    private List<Education> educationList;
    private List<Employment> employments;

    public CvContent() {
    }

    public CvContent(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public CvContent(PersonalInfo personalInfo, List<Education> educationList, List<Employment> employments) {
        this.personalInfo = personalInfo;
        this.educationList = educationList;
        this.employments = employments;
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
}

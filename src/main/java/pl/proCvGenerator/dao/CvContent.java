package pl.proCvGenerator.dao;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class CvContent {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "cvContentId")
    private PersonalInfo personalInfo;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "cvContentId")
    private List<Education> educationList;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "cvContentId")
    private List<Employment> employments;
    @ElementCollection()
    @CollectionTable()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> skills;
    @ElementCollection()
    @CollectionTable()
    @LazyCollection(LazyCollectionOption.FALSE)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CvContent cvContent = (CvContent) o;
        return Objects.equals(personalInfo, cvContent.personalInfo) &&
                Objects.equals(educationList, cvContent.educationList) &&
                Objects.equals(employments, cvContent.employments) &&
                Objects.equals(skills, cvContent.skills) &&
                Objects.equals(hobbies, cvContent.hobbies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalInfo, educationList, employments, skills, hobbies);
    }
}

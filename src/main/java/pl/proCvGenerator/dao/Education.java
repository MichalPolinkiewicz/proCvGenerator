package pl.proCvGenerator.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Education {

    @Id
    @GeneratedValue
    private long id;
    private String schoolName;
    private String subject;
    private String degree;
    private String startDate;
    private String endDate;

    public Education() {
    }

    public Education(String schoolName, String subject, String degree, String startDate, String endDate) {
        this.schoolName = schoolName;
        this.subject = subject;
        this.degree = degree;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(schoolName, education.schoolName) &&
                Objects.equals(subject, education.subject) &&
                Objects.equals(degree, education.degree) &&
                Objects.equals(startDate, education.startDate) &&
                Objects.equals(endDate, education.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolName, subject, degree, startDate, endDate);
    }
}

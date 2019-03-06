package pl.proCvGenerator.dao;

import lombok.ToString;

@ToString
public class Education {

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
}

package pl.proCvGenerator.dto;

public class Employment {

    private long id;
    private String company;
    private String position;
    private String jobDescription;
    private String startDate;
    private String endDate;

    public Employment() {
    }

    public Employment(String company, String position, String jobDescription, String startDate, String endDate) {
        this.company = company;
        this.position = position;
        this.jobDescription = jobDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
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

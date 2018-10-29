package pl.proCvGenerator.model;

public class CvContentDto {

    private PersonalInfoDto personalInfoDto;
    private EducationListWrapperDto educationListWrapperDto;
    private EmploymentListWrapperDto employmentListWrapperDto;
    private HobbyListWrapperDto hobbyListWrapperDto;
    private String clause;

    public CvContentDto(PersonalInfoDto personalInfoDto, EducationListWrapperDto educationListWrapperDto, EmploymentListWrapperDto employmentListWrapperDto, HobbyListWrapperDto hobbyListWrapperDto, String clause) {
        this.personalInfoDto = personalInfoDto;
        this.educationListWrapperDto = educationListWrapperDto;
        this.employmentListWrapperDto = employmentListWrapperDto;
        this.hobbyListWrapperDto = hobbyListWrapperDto;
        this.clause = clause;
    }

    public PersonalInfoDto getPersonalInfoDto() {
        return personalInfoDto;
    }

    public void setPersonalInfoDto(PersonalInfoDto personalInfoDto) {
        this.personalInfoDto = personalInfoDto;
    }

    public EducationListWrapperDto getEducationListWrapperDto() {
        return educationListWrapperDto;
    }

    public void setEducationListWrapperDto(EducationListWrapperDto educationListWrapperDto) {
        this.educationListWrapperDto = educationListWrapperDto;
    }

    public EmploymentListWrapperDto getEmploymentListWrapperDto() {
        return employmentListWrapperDto;
    }

    public void setEmploymentListWrapperDto(EmploymentListWrapperDto employmentListWrapperDto) {
        this.employmentListWrapperDto = employmentListWrapperDto;
    }

    public HobbyListWrapperDto getHobbyListWrapperDto() {
        return hobbyListWrapperDto;
    }

    public void setHobbyListWrapperDto(HobbyListWrapperDto hobbyListWrapperDto) {
        this.hobbyListWrapperDto = hobbyListWrapperDto;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }
}
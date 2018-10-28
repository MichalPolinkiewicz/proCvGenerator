package pl.proCvGenerator.model;

public class CvContentDto {

    private PersonalInfoDto personalInfoDto;
    private EducationListWrapperDto educationListWrapperDto;
    private EmploymentListWrapperDto employmentListWrapperDto;

    public CvContentDto(PersonalInfoDto personalInfoDto, EducationListWrapperDto educationListWrapperDto, EmploymentListWrapperDto employmentListWrapperDto) {
        this.personalInfoDto = personalInfoDto;
        this.educationListWrapperDto = educationListWrapperDto;
        this.employmentListWrapperDto = employmentListWrapperDto;
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
}

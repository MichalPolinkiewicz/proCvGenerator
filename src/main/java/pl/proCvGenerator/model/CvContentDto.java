package pl.proCvGenerator.model;

public class CvContentDto {

    private PersonalInfoDto personalInfoDto;
    private EducationListWrapperDto educationListWrapperDto;
    private EmploymentListWrapperDto employmentListWrapperDto;
    private SkillsListWrapperDto skillsListWrapperDto;
    private HobbiesListWrapperDto hobbiesListWrapperDto;
    private String clause;

    public CvContentDto() {
    }

    public CvContentDto(PersonalInfoDto personalInfoDto, EducationListWrapperDto educationListWrapperDto, EmploymentListWrapperDto employmentListWrapperDto, SkillsListWrapperDto skillsListWrapperDto, HobbiesListWrapperDto hobbiesListWrapperDto, String clause) {
        this.personalInfoDto = personalInfoDto;
        this.educationListWrapperDto = educationListWrapperDto;
        this.employmentListWrapperDto = employmentListWrapperDto;
        this.skillsListWrapperDto = skillsListWrapperDto;
        this.hobbiesListWrapperDto = hobbiesListWrapperDto;
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

    public SkillsListWrapperDto getSkillsListWrapperDto() {
        return skillsListWrapperDto;
    }

    public void setSkillsListWrapperDto(SkillsListWrapperDto skillsListWrapperDto) {
        this.skillsListWrapperDto = skillsListWrapperDto;
    }

    public HobbiesListWrapperDto getHobbiesListWrapperDto() {
        return hobbiesListWrapperDto;
    }

    public void setHobbiesListWrapperDto(HobbiesListWrapperDto hobbiesListWrapperDto) {
        this.hobbiesListWrapperDto = hobbiesListWrapperDto;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }
}
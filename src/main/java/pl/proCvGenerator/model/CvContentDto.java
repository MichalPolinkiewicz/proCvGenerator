package pl.proCvGenerator.model;

import lombok.ToString;

@ToString
public class CvContentDto {

    private PersonalInfoDto personalInfoDto;
    private EducationListWrapperDto educationListWrapperDto;
    private EmploymentListWrapperDto employmentListWrapperDto;
    private SkillsListWrapperDto skillsListWrapperDto;
    private HobbiesListWrapperDto hobbiesListWrapperDto;

    public CvContentDto() {
    }

    public CvContentDto(PersonalInfoDto personalInfoDto, EducationListWrapperDto educationListWrapperDto, EmploymentListWrapperDto employmentListWrapperDto, SkillsListWrapperDto skillsListWrapperDto, HobbiesListWrapperDto hobbiesListWrapperDto) {
        this.personalInfoDto = personalInfoDto;
        this.educationListWrapperDto = educationListWrapperDto;
        this.employmentListWrapperDto = employmentListWrapperDto;
        this.skillsListWrapperDto = skillsListWrapperDto;
        this.hobbiesListWrapperDto = hobbiesListWrapperDto;
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
}
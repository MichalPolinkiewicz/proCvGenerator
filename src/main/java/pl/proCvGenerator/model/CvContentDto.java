package pl.proCvGenerator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CvContentDto {

    private PersonalInfoDto personalInfoDto;
    private EducationListWrapperDto educationListWrapperDto;
    private EmploymentListWrapperDto employmentListWrapperDto;
    private SkillsListWrapperDto skillsListWrapperDto;
    private HobbiesListWrapperDto hobbiesListWrapperDto;

}
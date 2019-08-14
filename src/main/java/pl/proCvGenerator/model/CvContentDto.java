package pl.proCvGenerator.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CvContentDto {

    private PersonalInfoDto personalInfoDto;
    private EducationListWrapperDto educationListWrapperDto;
    private EmploymentListWrapperDto employmentListWrapperDto;
    private SkillsListWrapperDto skillsListWrapperDto;
    private HobbiesListWrapperDto hobbiesListWrapperDto;

}
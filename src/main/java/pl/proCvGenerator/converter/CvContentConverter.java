package pl.proCvGenerator.converter;

import org.springframework.beans.factory.annotation.Autowired;
import pl.proCvGenerator.dao.CvContent;
import pl.proCvGenerator.model.CvContentDto;

public class CvContentConverter {

    @Autowired
    private PersonalInfoConverter personalInfoConverter;
    @Autowired
    private EducationConverter educationConverter;
    @Autowired
    private EmploymentConverter employmentConverter;
    @Autowired
    private SkillsConverter skillsConverter;
    @Autowired
    private HobbyConverter hobbyConverter;

    public CvContent convertToContent(CvContentDto cvContentDto) {
        return CvContent.builder()
                .personalInfo(personalInfoConverter.convertToPersonalInfo(cvContentDto.getPersonalInfoDto()))
                .educationList(educationConverter.convertToList(cvContentDto.getEducationListWrapperDto()))
                .employments(employmentConverter.convertToList(cvContentDto.getEmploymentListWrapperDto()))
                .skills(skillsConverter.convertToList(cvContentDto.getSkillsListWrapperDto()))
                .hobbies(hobbyConverter.convertToList(cvContentDto.getHobbiesListWrapperDto()))
                .build();
    }

    public CvContentDto convertToDto(CvContent cvContent) {
        return CvContentDto.builder()
                .personalInfoDto(personalInfoConverter.convertToDto(cvContent.getPersonalInfo()))
                .educationListWrapperDto(educationConverter.convertToWrapper(cvContent.getEducationList()))
                .employmentListWrapperDto(employmentConverter.convertToWrapper(cvContent.getEmployments()))
                .skillsListWrapperDto(skillsConverter.convertToWrapper(cvContent.getSkills()))
                .hobbiesListWrapperDto(hobbyConverter.convertToWrapper(cvContent.getHobbies()))
                .build();
    }

}

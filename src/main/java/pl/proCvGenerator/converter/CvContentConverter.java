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
        return new CvContent(
                personalInfoConverter.convertToPersonalInfo(cvContentDto.getPersonalInfoDto()),
                educationConverter.convertToList(cvContentDto.getEducationListWrapperDto()),
                employmentConverter.convertToList(cvContentDto.getEmploymentListWrapperDto()),
                skillsConverter.convertToList(cvContentDto.getSkillsListWrapperDto()),
                hobbyConverter.convertToList(cvContentDto.getHobbiesListWrapperDto())
        );
    }

    public CvContentDto convertToDto(CvContent cvContent) {
        return new CvContentDto(
                personalInfoConverter.convertToDto(cvContent.getPersonalInfo()),
                educationConverter.convertToWrapper(cvContent.getEducationList()),
                employmentConverter.convertToWrapper(cvContent.getEmployments()),
                skillsConverter.convertToWrapper(cvContent.getSkills()),
                hobbyConverter.convertToWrapper(cvContent.getHobbies())
        );
    }
}

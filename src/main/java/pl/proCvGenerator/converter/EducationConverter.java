package pl.proCvGenerator.converter;

import lombok.extern.slf4j.Slf4j;
import pl.proCvGenerator.dao.Education;
import pl.proCvGenerator.model.EducationListWrapperDto;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EducationConverter {

    public List<Education> convertToList(EducationListWrapperDto educationListWrapperDto) {
        String methodName = "convertToList()...";
        log.info(methodName + "Input = {}", educationListWrapperDto);
        List<Education> educations = removeIncompleteEducation(educationListWrapperDto.getEducationList());
        log.info(methodName + "Output = {}", educations);

        return educations;
    }

    public EducationListWrapperDto convertToWrapper(List<Education> educationList) {
        String methodName = "convertToWrapper()...";
        log.info(methodName + "Input = {}", educationList);
        EducationListWrapperDto wrapper = new EducationListWrapperDto();
        wrapper.setEducationList(educationList);
        log.info(methodName + "Output = {}", wrapper);

        return wrapper;
    }

    public List<Education> removeIncompleteEducation(List<Education> educations){
        String methodName = " removeIncompleteEducation()...";
        log.info(methodName + "Input = {}", educations);

        List<Education> validated = new ArrayList<>();
        for (Education e : educations){
            if (e.getSchoolName() != null && e.getSchoolName().length() > 0 &&
                    e.getSubject() != null && e.getSubject().length() > 0 &&
                    e.getDegree() != null && e.getDegree().length() > 0 &&
                    e.getStartDate() != null && e.getStartDate().length() > 0 &&
                    e.getEndDate() != null && e.getEndDate().length() > 0
            ){
                validated.add(e);
            }
        }
        log.info(methodName + "Output = {}", validated);

        return validated;
    }
}

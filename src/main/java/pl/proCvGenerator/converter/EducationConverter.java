package pl.proCvGenerator.converter;

import pl.proCvGenerator.dao.Education;
import pl.proCvGenerator.model.EducationListWrapperDto;

import java.util.ArrayList;
import java.util.List;

public class EducationConverter {

    public List<Education> convertToList(EducationListWrapperDto educationListWrapperDto) {
        return new ArrayList<>(educationListWrapperDto.getEducationList());
    }

    public EducationListWrapperDto convertToWrapper(List<Education> educationList) {
        return new EducationListWrapperDto(educationList);
    }
}

package pl.proCvGenerator.converter;

import pl.proCvGenerator.dto.Employment;
import pl.proCvGenerator.model.EmploymentListWrapperDto;

import java.util.ArrayList;
import java.util.List;

public class EmploymentConverter {

    public List<Employment> convertToList(EmploymentListWrapperDto employmentListWrapperDto){
        return new ArrayList<>(employmentListWrapperDto.getEmployments());
    }

    public EmploymentListWrapperDto convertToWrapper(List<Employment> employmentList){
        EmploymentListWrapperDto wrapper = new EmploymentListWrapperDto();
        wrapper.setEmployments(employmentList);

        return wrapper;
    }
}

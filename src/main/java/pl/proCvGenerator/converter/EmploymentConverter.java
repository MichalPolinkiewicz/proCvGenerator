package pl.proCvGenerator.converter;

import lombok.extern.slf4j.Slf4j;
import pl.proCvGenerator.dao.Employment;
import pl.proCvGenerator.model.EmploymentListWrapperDto;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EmploymentConverter {

    public List<Employment> convertToList(EmploymentListWrapperDto employmentListWrapperDto) {
        String methodName = "convertToList()...";
        log.info(methodName + "Input = {}", employmentListWrapperDto);
        List<Employment> employments = removeIncompleteEmployment(employmentListWrapperDto.getEmploymentList());
        log.info(methodName + "Output = {}", employments);

        return employments;
    }

    public EmploymentListWrapperDto convertToWrapper(List<Employment> employmentList) {
        String methodName = "convertToWrapper()...";
        log.info(methodName + "Input = {}", employmentList);
        EmploymentListWrapperDto wrapper = new EmploymentListWrapperDto();
        wrapper.setEmploymentList(removeIncompleteEmployment(employmentList));
        log.info(methodName + "Output = {}", wrapper);

        return wrapper;
    }

    private List<Employment> removeIncompleteEmployment(List<Employment> employments) {
        String methodName = "removeIncompleteEmployment()...";
        log.info(methodName + "Input = {}", employments);

        List<Employment> validated = new ArrayList<>();
        for (Employment e : employments) {
            if (
                    e.getCompany() != null &&
                    e.getCompany().length() > 0 &&
                    e.getPosition() != null &&
                    e.getPosition().length() > 0 &&
                    e.getJobDescription() != null &&
                    e.getJobDescription().length() > 0 &&
                    e.getStartDate().length() > 0 &&
                    e.getStartDate().length() > 0 &&
                    e.getEndDate() != null &&
                    e.getEndDate().length() > 0
            ) {
                validated.add(e);
            }
        }
        log.info(methodName + "Output = {}", validated);

        return validated;
    }
}

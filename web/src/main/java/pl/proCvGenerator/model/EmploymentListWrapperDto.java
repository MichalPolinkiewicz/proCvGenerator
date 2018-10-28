package pl.proCvGenerator.model;

import pl.proCvGenerator.dto.Employment;

import java.util.ArrayList;
import java.util.List;

public class EmploymentListWrapperDto {

    private List<Employment> employments;

    public EmploymentListWrapperDto() {
        this.employments = new ArrayList<>();
    }

    public void addEmployment(Employment employment){
        employments.add(employment);
    }

    public List<Employment> getEmployments() {
        return employments;
    }

    public void setEmployments(List<Employment> employments) {
        this.employments = employments;
    }
}

package pl.proCvGenerator.model;

import lombok.ToString;
import pl.proCvGenerator.dao.Employment;

import java.util.ArrayList;
import java.util.List;

@ToString
public class EmploymentListWrapperDto {

    private List<Employment> employmentList;

    public EmploymentListWrapperDto() {
        this.employmentList = new ArrayList<>();
    }

    public void addEmployment(Employment employment){
        employmentList.add(employment);
    }

    public List<Employment> getEmploymentList() {
        return employmentList;
    }

    public void setEmploymentList(List<Employment> employmentList) {
        this.employmentList = employmentList;
    }
}

package pl.proCvGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.proCvGenerator.dao.Employment;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EmploymentListWrapperDto {

    private List<Employment> employmentList;

    public EmploymentListWrapperDto() {
        this.employmentList = new ArrayList<>();
    }

    public void addEmployment(Employment employment){
        employmentList.add(employment);
    }

}

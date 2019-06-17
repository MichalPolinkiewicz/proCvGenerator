package pl.proCvGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.proCvGenerator.dao.Education;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EducationListWrapperDto {

    private List<Education> educationList;

    public EducationListWrapperDto() {
        this.educationList = new ArrayList<>();
    }

    public void addEducation(Education education){
        educationList.add(education);
    }

}

package pl.proCvGenerator.model;

import lombok.ToString;
import pl.proCvGenerator.dao.Education;

import java.util.ArrayList;
import java.util.List;

@ToString
public class EducationListWrapperDto {

    private List<Education> educationList;

    public EducationListWrapperDto() {
        this.educationList = new ArrayList<>();
    }

    public void addEducation(Education education){
        educationList.add(education);
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }
}

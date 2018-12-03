package pl.proCvGenerator.model;

import pl.proCvGenerator.dto.Education;

import java.util.ArrayList;
import java.util.List;

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

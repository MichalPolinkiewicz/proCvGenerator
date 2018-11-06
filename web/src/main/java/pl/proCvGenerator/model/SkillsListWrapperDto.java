package pl.proCvGenerator.model;

import java.util.ArrayList;
import java.util.List;

public class SkillsListWrapperDto {

    private List<String> skills;

    public SkillsListWrapperDto() {
        this.skills = new ArrayList<>();
    }

    public void addSkill(String skill){
        skills.add(skill);
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}

package pl.proCvGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SkillsListWrapperDto {

    private List<String> skills;

    public SkillsListWrapperDto() {
        this.skills = new ArrayList<>();
    }

    public void addSkill(String skill){
        skills.add(skill);
    }

}

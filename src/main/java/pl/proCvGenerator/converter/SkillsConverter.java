package pl.proCvGenerator.converter;

import pl.proCvGenerator.model.SkillsListWrapperDto;

import java.util.ArrayList;
import java.util.List;

public class SkillsConverter {

    public List<String> convertToList(SkillsListWrapperDto wrapperDto){
        return new ArrayList<>(wrapperDto.getSkills());
    }

    public SkillsListWrapperDto convertToWrapper(List<String> skills){
        return new SkillsListWrapperDto(skills);
    }
}

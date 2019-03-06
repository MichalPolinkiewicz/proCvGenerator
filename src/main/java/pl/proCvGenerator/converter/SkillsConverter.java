package pl.proCvGenerator.converter;

import lombok.extern.slf4j.Slf4j;
import pl.proCvGenerator.model.SkillsListWrapperDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class SkillsConverter {

    public List<String> convertToList(SkillsListWrapperDto wrapperDto) {
        String methodName = "convertToList()...";
        log.info(methodName + "Input = {}", wrapperDto);

        List<String> skills = wrapperDto.getSkills()
                .stream()
                .filter(Objects::nonNull)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toList());

        log.info(methodName + "Output = {}", skills);

        return skills;
    }

    public SkillsListWrapperDto convertToWrapper(List<String> skills) {
        String methodName = "convertToWrapper()...";
        log.info(methodName + "Input = {}", skills);

        SkillsListWrapperDto wrapperDto = new SkillsListWrapperDto();
        wrapperDto.setSkills(
                skills.stream()
                        .filter(Objects::nonNull)
                        .filter(s -> s.length() > 0)
                        .collect(Collectors.toList()));

        log.info(methodName + "Output = {}", wrapperDto);

        return wrapperDto;
    }
}

package pl.proCvGenerator.converter;

import lombok.extern.slf4j.Slf4j;
import pl.proCvGenerator.model.HobbiesListWrapperDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class HobbyConverter {

    public List<String> convertToList(HobbiesListWrapperDto hobbiesListWrapperDto){
        String methodName = "convertToList()...";
        log.info(methodName + "Input = {}", hobbiesListWrapperDto);

        List<String> hobbies = hobbiesListWrapperDto.getHobbyList()
                .stream()
                .filter(Objects::nonNull)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toList());

        log.info(methodName + "Output = {}", hobbies);

        return hobbies;
    }

    public HobbiesListWrapperDto convertToWrapper(List<String> hobbies){
        String methodName = "convertToWrapper()...";
        log.info(methodName + "Input = {}", hobbies);

        HobbiesListWrapperDto hobbiesListWrapperDto = new HobbiesListWrapperDto();
        hobbiesListWrapperDto.setHobbyList(
                hobbies.stream()
                        .filter(Objects::nonNull)
                        .filter(s -> s.length() > 0)
                        .collect(Collectors.toList())
        );

        log.info(methodName + "Output = {}", hobbiesListWrapperDto);

        return hobbiesListWrapperDto;
    }
}

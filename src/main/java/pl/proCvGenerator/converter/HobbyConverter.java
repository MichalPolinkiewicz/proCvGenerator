package pl.proCvGenerator.converter;

import pl.proCvGenerator.model.HobbiesListWrapperDto;

import java.util.ArrayList;
import java.util.List;

public class HobbyConverter {

    public List<String> convertToList(HobbiesListWrapperDto hobbiesListWrapperDto){
        return new ArrayList<>(hobbiesListWrapperDto.getHobbyList());
    }

    public HobbiesListWrapperDto convertToWrapper(List<String> hobbies){
        HobbiesListWrapperDto hobbiesListWrapperDto = new HobbiesListWrapperDto();
        hobbiesListWrapperDto.setHobbyList(hobbies);

        return hobbiesListWrapperDto;
    }
}

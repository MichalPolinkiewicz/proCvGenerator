package pl.proCvGenerator.converter;

import pl.proCvGenerator.model.HobbyListWrapperDto;

import java.util.ArrayList;
import java.util.List;

public class HobbyConverter {

    public List<String> convertToList(HobbyListWrapperDto hobbyListWrapperDto){
        return new ArrayList<>(hobbyListWrapperDto.getHobbyList());
    }

    public HobbyListWrapperDto convertToWrapper(List<String> hobbies){
        HobbyListWrapperDto hobbyListWrapperDto = new HobbyListWrapperDto();
        hobbyListWrapperDto.setHobbyList(hobbies);

        return hobbyListWrapperDto;
    }
}

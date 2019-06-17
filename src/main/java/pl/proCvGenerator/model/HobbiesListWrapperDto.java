package pl.proCvGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class HobbiesListWrapperDto {

    private List<String> hobbyList;

    public HobbiesListWrapperDto() {
        this.hobbyList = new ArrayList<>();
    }

    public void addHobby(String hobby){
        hobbyList.add(hobby);
    }

}

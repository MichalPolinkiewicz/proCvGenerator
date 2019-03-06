package pl.proCvGenerator.model;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class HobbiesListWrapperDto {

    private List<String> hobbyList;

    public HobbiesListWrapperDto() {
        this.hobbyList = new ArrayList<>();
    }

    public void addHobby(String hobby){
        hobbyList.add(hobby);
    }

    public List<String> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(List<String> hobbies) {
        this.hobbyList = hobbies;
    }
}

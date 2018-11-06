package pl.proCvGenerator.model;

import java.util.ArrayList;
import java.util.List;

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

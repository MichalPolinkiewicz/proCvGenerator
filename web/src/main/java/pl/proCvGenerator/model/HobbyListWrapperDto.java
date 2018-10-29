package pl.proCvGenerator.model;

import java.util.ArrayList;
import java.util.List;

public class HobbyListWrapperDto {

    private List<String> hobbyList;

    public HobbyListWrapperDto() {
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

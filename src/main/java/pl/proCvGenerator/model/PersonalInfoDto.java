package pl.proCvGenerator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonalInfoDto {

    private String name;
    private String surname;
    private String position;
    private String email;
    private String page;
    private String phone;
    private String city;
    private String description;

}

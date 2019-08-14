package pl.proCvGenerator.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

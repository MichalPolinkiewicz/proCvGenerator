package pl.proCvGenerator.dao;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private long id;
    private String name;
    private String surname;
    private String position;
    private String email;
    private String page;
    private String phone;
    private String city;
    @Column(columnDefinition="TEXT")
    private String description;

}

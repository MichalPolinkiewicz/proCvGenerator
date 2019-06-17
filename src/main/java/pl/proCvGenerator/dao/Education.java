package pl.proCvGenerator.dao;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class Education {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private long id;
    private String schoolName;
    private String subject;
    private String degree;
    private String startDate;
    private String endDate;
}

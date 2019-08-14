package pl.proCvGenerator.dao;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Employment {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private long id;
    private String company;
    private String position;
    @Column(columnDefinition = "TEXT")
    private String jobDescription;
    private String startDate;
    private String endDate;

}

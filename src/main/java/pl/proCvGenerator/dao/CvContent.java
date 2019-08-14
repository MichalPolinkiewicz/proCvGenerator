package pl.proCvGenerator.dao;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CvContent {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "cvContentId")
    private PersonalInfo personalInfo;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "cvContentId")
    private List<Education> educationList;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "cvContentId")
    private List<Employment> employments;
    @ElementCollection()
    @CollectionTable()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> skills;
    @ElementCollection()
    @CollectionTable()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> hobbies;
}

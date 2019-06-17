package pl.proCvGenerator.dao;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String password;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cvContentId")
    private CvContent cvContent;

}

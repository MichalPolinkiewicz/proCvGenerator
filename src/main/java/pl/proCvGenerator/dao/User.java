package pl.proCvGenerator.dao;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String password;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cvContentId")
    private CvContent cvContent;

    public User() {
    }

    public User(String login, String password, CvContent cvContent) {
        this.login = login;
        this.password = password;
        this.cvContent = cvContent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CvContent getCvContent() {
        return cvContent;
    }

    public void setCvContent(CvContent cvContent) {
        this.cvContent = cvContent;
    }
}

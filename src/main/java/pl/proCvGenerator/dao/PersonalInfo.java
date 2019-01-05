package pl.proCvGenerator.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
public class PersonalInfo {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;
    private String position;
    private String email;
    private String page;
    private String phone;
    private String city;
    private String description;

    public PersonalInfo() {
    }

    public PersonalInfo(String name, String surname, String position, String email, String page, String phone, String city, String description) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.page = page;
        this.phone = phone;
        this.city = city;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalInfo that = (PersonalInfo) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(position, that.position) &&
                Objects.equals(email, that.email) &&
                Objects.equals(page, that.page) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(city, that.city) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, position, email, page, phone, city, description);
    }
}

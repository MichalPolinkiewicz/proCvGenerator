package pl.proCvGenerator.dto;

public class PersonalInfo {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String city;
    private String description;

    public PersonalInfo() {
    }

    public PersonalInfo(String name, String surname, String email, String phone, String city, String description) {
        this.name = name;
        this.surname = surname;
        this.email = email;
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
}

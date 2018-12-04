package pl.proCvGenerator.model;

public class PersonalInfoDto {

    private String name;
    private String surname;
    private String position;
    private String email;
    private String page;
    private String phone;
    private String city;
    private String description;

    public PersonalInfoDto() {
    }

    public PersonalInfoDto(String name, String surname, String position, String email, String page, String phone, String city, String description) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.page = page;
        this.phone = phone;
        this.city = city;
        this.description = description;
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
}

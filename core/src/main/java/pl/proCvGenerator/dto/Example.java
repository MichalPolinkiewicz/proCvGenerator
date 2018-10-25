package pl.proCvGenerator.dto;

public class Example {

    private String name;
    private String surname;
    private Inside inside;

    public Example() {
    }

    public Example(String name, String surname, Inside inside) {
        this.name = name;
        this.surname = surname;
        this.inside = inside;
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

    public Inside getInside() {
        return inside;
    }

    public void setInside(Inside inside) {
        this.inside = inside;
    }
}

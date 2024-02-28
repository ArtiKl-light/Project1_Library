package ru.artikl.projectlibrary.model;

public class Person {

    private int person_id;
    private String name;
    private int birthday;

    public Person(){

    }

    public Person(String name, int birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
}
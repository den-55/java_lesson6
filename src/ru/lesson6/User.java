package ru.lesson6;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private Integer age;
    private Gender gender; //private transient Gender gender чтоб не записывалось поле в Serializable

    public User(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
